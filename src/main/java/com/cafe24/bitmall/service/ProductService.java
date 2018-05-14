package com.cafe24.bitmall.service;

import com.cafe24.bitmall.dto.ProductDto;
import com.cafe24.bitmall.repository.ImageRepository;
import com.cafe24.bitmall.repository.OptionRepository;
import com.cafe24.bitmall.repository.ProductRepository;
import com.cafe24.bitmall.vo.ProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private OptionRepository optionRepository;

    public List<ProductDto> getList(Long no) {
        return generateProductDto(productRepository.getCategoryProductList(no));
    }

    public ProductDto getByNo(Long no){
        return generateProductDto(productRepository.getByNo(no));
    }

    private List<ProductDto> generateProductDto(List<ProductVo> pList) {
        List<ProductDto> dtoList = new ArrayList<>();
        ProductDto dto;
        for (ProductVo pVo : pList) {
            dto = new ProductDto();
            dto.setProductVo(pVo);
            dto.setImageVos(imageRepository.getList(pVo.getNo()));
            dto.setOptionVos(optionRepository.getList(pVo.getNo()));
            dtoList.add(dto);
        }
        return dtoList;
    }

    private ProductDto generateProductDto(ProductVo pVo) {
        ProductDto dto;
        dto = new ProductDto();
        dto.setProductVo(pVo);
        dto.setImageVos(imageRepository.getList(pVo.getNo()));
        dto.setOptionVos(optionRepository.getList(pVo.getNo()));
        return dto;
    }
}
