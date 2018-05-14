package com.cafe24.bitmall.service;

import com.cafe24.bitmall.dto.ImageDto;
import com.cafe24.bitmall.dto.OptionDto;
import com.cafe24.bitmall.dto.OrderDto;
import com.cafe24.bitmall.dto.ProductDto;
import com.cafe24.bitmall.repository.*;
import com.cafe24.bitmall.util.FileUpload;
import com.cafe24.bitmall.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FaqRepository faqRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OptionRepository optionRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderedProductRepository orderedProductRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private ProductOptionRepository productOptionRepository;

    /**
     * FAQ
     */
    public List<FaqVo> getFaqList(){
        return faqRepository.getList();
    }

    public FaqVo getFaqByNo(Long no){
        return faqRepository.getByNo(no);
    }

    public boolean addFaq(FaqVo vo){
        return faqRepository.insert(vo) > 0;
    }

    public boolean updateFaq(FaqVo vo){
        return faqRepository.update(vo) > 0;
    }

    public boolean deleteFaq(Long no){
        return faqRepository.delete(no) > 0;
    }

    /**
     * Order
     */

    public List<OrderDto> getOrderList(){
        List<OrderDto> result = new ArrayList<>();
        List<OrderVo> orderList = orderRepository.getList();
        OrderDto orderDto = new OrderDto();
        for(OrderVo vo : orderList){
            orderDto.setOrderVo(vo);
            orderDto.setOrderedProductList(orderedProductRepository.getList(vo.getNo()));
            result.add(orderDto);
        }
        return result;
    }

    public OrderDto getOrderByNo(Long no){
        OrderVo orderVo = orderRepository.getByNo(no);
        List<OrderedProductVo> orderedProductList = orderedProductRepository.getList(no);
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderVo(orderVo);
        orderDto.setOrderedProductList(orderedProductList);
        return orderDto;
    }

    public boolean updateOrderState(Long no, String state){
        OrderVo vo = new OrderVo();
        vo.setNo(no);
        vo.setState(state);
        return orderRepository.update(vo) > 0;
    }

    public boolean deleteOrder(Long no){
        return orderRepository.delete(no) > 0;
    }

    /**
     * User
     */

    public List<UserVo> getUserList(){
        return userRepository.getList();
    }

    public boolean deleteUser(Long no){
        return userRepository.delete(no) > 0;
    }

    public UserVo getUserByNo(Long no){return userRepository.getUserByNo(no);}

    public boolean modifyUser(UserVo vo){
        return userRepository.update(vo) > 0;
    }

    /**
     * Option
     */

    public List<OptionVo> getOptionList(){
        return optionRepository.getList();
    }

    public List<OptionVo> getOptionList(Long no){
        return optionRepository.getList(no);
    }

    public OptionVo getOptionByNo(Long no){
        return optionRepository.getByNo(no);
    }

    public boolean addOption(OptionVo vo){
        return optionRepository.insert(vo) > 0;
    }

    public OptionVo addOption(Long parentNo, String name){
        OptionVo vo = new OptionVo();
        vo.setParentNo(parentNo);
        vo.setName(name);
        optionRepository.insert(vo);
        return vo;
    }

    public boolean updateOption(OptionVo vo){
        return optionRepository.update(vo) > 0;
    }

    public boolean deleteOption(Long no){
        return optionRepository.delete(no) > 0;
    }

    /**
     * Product
     */

    public List<ProductVo> getProductList(){
        return productRepository.getList();
    }

    public ProductVo getProductByNo(Long no){
        return productRepository.getByNo(no);
    }

    public ProductDto getProductDtoByNo(Long no){
        ProductDto dto = new ProductDto();
        dto.setProductVo(getProductByNo(no));
        dto.setAllCategoryVos(categoryRepository.getList());
        dto.setAllOptionVos(optionRepository.getList());
        dto.setImageVos(imageRepository.getList(no));
        dto.setOptionVos(productOptionRepository.getList(no));

        return dto;
    }

    public ProductDto getNewProductDto(){
        ProductDto dto = new ProductDto();
        dto.setAllCategoryVos(categoryRepository.getList());
        dto.setAllOptionVos(optionRepository.getList());
        return dto;
    }

    public boolean addProduct(ProductVo productVo, ImageDto imageDto, OptionDto optionDto){
        boolean result = productRepository.insert(productVo) > 0;
        List<ImageDto> imageList = imageDto.getImageList();
        FileUpload upload = new FileUpload();

        String name;
        int size = imageList.size();
        ImageVo imageVo = new ImageVo();
        imageVo.setProductNo(productVo.getNo());
        for(int i = 0 ; i < size; i++){
            imageVo.setOrderNo(i);
            name = imageList.get(i).getImage().getOriginalFilename();
            if("".equals(name) == false){
                upload.restore(imageList.get(i).getImage(), imageVo);
                imageRepository.insert(imageVo);
            }
        }

        List<OptionDto> optionList = optionDto.getOptionDtoList();
        size = optionList.size();
        ProductOptionVo productOptionVo = new ProductOptionVo();
        productOptionVo.setProductNo(productVo.getNo());
        productOptionRepository.delete(productVo.getNo());
        for(int i = 0 ; i < size; i++){
            productOptionVo.setOptionNo(optionList.get(i).getNo());
            if(productOptionVo.getOptionNo() > 0){
                productOptionRepository.insert(productOptionVo);
            }
        }

        return result;
    }

    public boolean updateProduct(ProductVo productVo, ImageDto imageDto, OptionDto optionDto){
        boolean result = productRepository.update(productVo) > 0;
        List<ImageDto> imageList = imageDto.getImageList();
        FileUpload upload = new FileUpload();

        int size = imageList.size();
        ImageVo imageVo = new ImageVo();
        imageVo.setProductNo(productVo.getNo());
        for(int i = 0 ; i < size; i++){
            imageVo.setOrderNo(i);
            if("".equals(imageList.get(i).getImage().getOriginalFilename()) == false){
                upload.restore(imageList.get(i).getImage(), imageVo);
                imageRepository.insert(imageVo);
            }
        }

        List<OptionDto> optionList = optionDto.getOptionDtoList();
        size = optionList.size();
        ProductOptionVo productOptionVo = new ProductOptionVo();
        productOptionVo.setProductNo(productVo.getNo());
        productOptionRepository.delete(productVo.getNo());
        for(int i = 0 ; i < size; i++){
            productOptionVo.setOptionNo(optionList.get(i).getNo());
            if(productOptionVo.getOptionNo() > 0){
                productOptionRepository.insert(productOptionVo);
            }
        }

        return result;
    }

    public boolean deleteProduct(Long no){
        return productRepository.delete(no) > 0;
    }

    public boolean deleteImage(Long no){
        return imageRepository.deleteByNo(no) > 0;
    }
}
