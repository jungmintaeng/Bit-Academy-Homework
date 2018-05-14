package com.cafe24.bitmall.service;

import com.cafe24.bitmall.dto.ProductDto;
import com.cafe24.bitmall.repository.CategoryRepository;
import com.cafe24.bitmall.repository.ImageRepository;
import com.cafe24.bitmall.repository.ProductRepository;
import com.cafe24.bitmall.repository.UserRepository;
import com.cafe24.bitmall.vo.CategoryVo;
import com.cafe24.bitmall.vo.ImageVo;
import com.cafe24.bitmall.vo.ProductVo;
import com.cafe24.bitmall.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.net.URL;
import java.util.*;

@Service
public class MainService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public List<ProductDto> getNewProductList() {
        return generateProductDto(productRepository.getNewList());
    }

    public List<ProductDto> getHitProductList() {
        return generateProductDto(productRepository.getHitList());
    }

    public List<ProductDto> getDiscountList() {
        return generateProductDto(productRepository.getDiscountList());
    }

    private List<ProductDto> generateProductDto(List<ProductVo> pList) {
        List<ProductDto> dtoList = new ArrayList<>();
        ProductDto dto;
        for (ProductVo pVo : pList) {
            dto = new ProductDto();
            dto.setProductVo(pVo);
            dto.setImageVos(imageRepository.getList(pVo.getNo()));
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Transactional
    public void init() {
        /**
         * admin user 가 존재하면 init 이 됐다고 판단하고 return
         */
        List<UserVo> userList = userRepository.getList();
        if (userList != null && userRepository.getList().size() > 0) {
            return;
        }

        //admin 생성
        initAdmin();

        //category 생성
        HashMap<String, Long> categoryMap = initCategories();

        Random rand = new Random();
        ProductVo productVo = new ProductVo();
        ImageVo imageVo = new ImageVo();
        String categoryName, productName, fName, line;
        StringBuilder builder = new StringBuilder();
        try {
            /**
             * C:/uploads 디렉토리가 없으면 생성
             */
            File uploads = new File("C:/uploads");
            if (!uploads.exists()) {
                uploads.mkdir();
            }
            URL root = getClass().getClassLoader().getResource("init_product");
            File file = new File(root.getFile());
            File[] categories = file.listFiles();
            for (File f : categories) {
                categoryName = f.getName().replace("+", "/");
                File[] products = f.listFiles();
                for (File pFile : products) {
                    productName = pFile.getName();
                    File[] dataFiles = pFile.listFiles();
                    int size = dataFiles.length;

                    productVo.setName(productName);
                    productVo.setManufacturer("정민 엔터테인먼트");
                    productVo.setHit_(rand.nextBoolean());
                    productVo.setNew_(rand.nextBoolean());
                    productVo.setCategoryName(categoryName);
                    productVo.setCategoryNo(categoryMap.get(categoryName));
                    if(rand.nextBoolean()){
                        productVo.setDiscountRate((float) (int) (rand.nextFloat() * 40));
                    }else{
                        productVo.setDiscountRate(0F);
                    }
                    productVo.setState("판매중");
                    productVo.setCode(categoryName + generateSaveFileName());
                    productVo.setPrice((rand.nextInt(4) + 2) * 4000);
                    productVo.setDescription("");

                    productRepository.insert(productVo);

                    for (int i = 0; i < size; i++) {
                        fName = realFileName(dataFiles[i].getName());
                        if ("logo".equals(fName)) {
                            imageVo.setUploadName(dataFiles[i].getName());
                            imageVo.setSaveName(generateSaveFileName() +
                                    imageVo.getUploadName().substring(imageVo.getUploadName().lastIndexOf("."), imageVo.getUploadName().length()));
                            imageVo.setOrderNo(0);
                            imageVo.setProductNo(productVo.getNo());
                            copyFile(dataFiles[i], imageVo.getSaveName());
                            imageRepository.insert(imageVo);
                        } else if ("detail".equals(fName)) {
                            imageVo.setUploadName(dataFiles[i].getName());
                            imageVo.setSaveName(generateSaveFileName() +
                                    imageVo.getUploadName().substring(imageVo.getUploadName().lastIndexOf("."), imageVo.getUploadName().length()));
                            imageVo.setOrderNo(1);
                            imageVo.setProductNo(productVo.getNo());
                            copyFile(dataFiles[i], imageVo.getSaveName());
                            imageRepository.insert(imageVo);
                        } else if ("disc".equals(fName)) {
                            imageVo.setUploadName(dataFiles[i].getName());
                            imageVo.setSaveName(generateSaveFileName() +
                                    imageVo.getUploadName().substring(imageVo.getUploadName().lastIndexOf("."), imageVo.getUploadName().length()));
                            imageVo.setOrderNo(2);
                            imageVo.setProductNo(productVo.getNo());
                            copyFile(dataFiles[i], imageVo.getSaveName());
                            imageRepository.insert(imageVo);
                        } else if ("desc".equals(fName)) {
                            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(dataFiles[i]),"UTF-8"));
                            builder.setLength(0);
                            while (true) {
                                line = br.readLine();
                                if (line == null) {
                                    break;
                                }
                                builder.append(line);
                                builder.append("\n");
                            }
                            productVo.setDescription(builder.toString());
                            productRepository.update(productVo);
                            br.close();
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    public void initAdmin(){
        /**
         * admin user 생성
         */
        UserVo userVo = new UserVo();
        userVo.setRole("admin");
        userVo.setId("admin");
        userVo.setPassword("admin");
        userVo.setName("admin");
        userRepository.insert(userVo);
    }


    public HashMap<String, Long> initCategories() {
        /**
         * 카테고리들을 생성하고 각각의 Primary key 와 카테고리명을 Map 형태로 반환
         */
        HashMap<String, String> descMap = new HashMap<>();
        HashMap<String, Long> categoryNoMap = new HashMap<>();
        String[] categories = {
                "발라드",
                "댄스",
                "R&B/소울",
                "록/인디록/메탈",
                "힙합",
                "일렉트로니카/테크노",
                "포크",
                "재즈",
                "성인/트롯트",
                "OST",
                "클래식",
                "월드뮤직"
        };

        descMap.put("발라드",
                "계절에 상관없이 들을 수 있는 서정적인 발라드 뮤직 !"
        );
        descMap.put("댄스",
                "친구들과 함께 달리며 듣는 신나는 댄스 음악 !"
        );
        descMap.put("R&B/소울",
                "리듬타며 들을 수 있는 그루브 넘치는 뮤직 !"
        );
        descMap.put("록/인디록/메탈",
                "락앤롤 ! 모두가 함께 헤드뱅잉하는 그 날까지 !"
        );
        descMap.put("힙합",
                "트랩 붐뱁 클라우드 힙합을 사랑하는 사람이라면 !"
        );
        descMap.put("일렉트로니카/테크노",
                "모두 함께 흔들 수 있는 신나는 음악 !"
        );
        descMap.put("포크",
                "어쿠스틱한 사운드와 잔잔한 이야기를 함께 듣고 싶다면 ?"
        );
        descMap.put("재즈",
                "그루비한 음악의 선율을 느끼고 싶나요 ?"
        );
        descMap.put("성인/트롯트",
                "심금을 울리는 가사 ! 트로트의 매력에 빠져보세요 !"
        );
        descMap.put("OST",
                "드라마 / 영화 속에 나왔던 음악을 앨범으로 즐기세요 !"
        );
        descMap.put("클래식",
                "악기들의 조화로운 선율을 눈을 감고 감상해 보세요 !"
        );
        descMap.put("월드뮤직",
                "세계적인 음악들을 한 자리에 !"
        );
        CategoryVo vo = new CategoryVo();
        for (String categoryName : categories) {
            vo.setName(categoryName);
            vo.setDescription(descMap.get(categoryName));
            categoryRepository.insert(vo);
            categoryNoMap.put(vo.getName(), vo.getNo());
        }

        return categoryNoMap;
    }

    private String realFileName(String str) {
        if (str == null) return null;
        int pos = str.lastIndexOf(".");
        if (pos == -1) return str;
        return str.substring(0, pos);
    }

    private String generateSaveFileName() {
        Calendar c = Calendar.getInstance();
        StringBuilder fileNameBuilder = new StringBuilder();
        fileNameBuilder.append(c.get(Calendar.YEAR));
        fileNameBuilder.append(c.get(Calendar.MONTH));
        fileNameBuilder.append(c.get(Calendar.DATE));
        fileNameBuilder.append(c.get(Calendar.HOUR_OF_DAY));
        fileNameBuilder.append(c.get(Calendar.MINUTE));
        fileNameBuilder.append(c.get(Calendar.SECOND));
        fileNameBuilder.append(c.get(Calendar.MILLISECOND));
        return fileNameBuilder.toString();
    }

    private boolean copyFile(File from, String saveName) throws IOException {
        FileInputStream inputStream = new FileInputStream(from);
        FileOutputStream outputStream = new FileOutputStream("/uploads/" + saveName);

        int bytesRead = 0;
        byte[] buffer = new byte[1024];

        while ((bytesRead = inputStream.read(buffer, 0, 1024)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }

        outputStream.close();
        inputStream.close();

        return true;
    }
}
