package com.cafe24.bitmall.service;

import com.cafe24.bitmall.dto.CartDto;
import com.cafe24.bitmall.repository.*;
import com.cafe24.bitmall.vo.CartVo;
import com.cafe24.bitmall.vo.ImageVo;
import com.cafe24.bitmall.vo.OptionVo;
import com.cafe24.bitmall.vo.ProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartUserOptionRepository cartUserOptionRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private OptionRepository optionRepository;

    public List<CartDto> getCartList(Long no){
        return convertToDto(cartRepository.getList(no));
    }

    public CartDto getByNo(Long no) { return convertToDto(cartRepository.getByNo(no)); }

    public Long getQuantity(CartVo vo){
        return cartRepository.getQuantity(vo);
    }

    @Transactional
    public Long addCart(Long userNo, Long productNo, List<Long> optionList, Long quantity){
        if(optionList == null || optionList.size() == 0){
            /**
             * 옵션이 선택되지 않은 상품의 경우
             */
            Long cartNo = cartRepository.checkRedundancy(userNo, productNo);
            if(cartNo != null && cartNo > 0){
                cartRepository.addQuantity(cartNo, quantity);
                return cartNo;
            } else {
                CartVo vo = new CartVo();
                vo.setUserNo(userNo);
                vo.setProductNo(productNo);
                vo.setQuantity(quantity);
                cartRepository.insert(vo);
                return vo.getNo();
            }
        } else {
            /**
             * 옵션이 선택된 경우 --> CartUserOptionRepository 도 같이 관리 해줘야 됨
             */
            Long cartNo = cartUserOptionRepository.checkRedundancy(optionList);
            if(cartNo != null && cartNo > 0){
                cartRepository.addQuantity(cartNo, quantity);
                return cartNo;
            } else {
                CartVo vo = new CartVo();
                vo.setUserNo(userNo);
                vo.setProductNo(productNo);
                vo.setQuantity(quantity);
                cartRepository.insert(vo);
                for(Long optionNo : optionList){
                    cartUserOptionRepository.insert(vo.getNo(), optionNo);
                }
                return vo.getNo();
            }
        }
    }

    public boolean updateCart(Long cartNo, Long quantity){
        return cartRepository.update(cartNo, quantity) > 0;
    }

    public boolean deleteCart(Long no){
        return cartRepository.delete(no) > 0;
    }

    public boolean deleteAllCart(Long no){
        return cartRepository.deleteAll(no) > 0;
    }

    private List<CartDto> convertToDto(List<CartVo> cartVoList){
        List<CartDto> dtoList = new ArrayList<>();
        CartDto dto;
        for(CartVo vo : cartVoList){
            dto = new CartDto();
            dto.setCartVo(vo);
            dto.setOptionList(cartUserOptionRepository.getList(vo.getNo()));
            dtoList.add(dto);
        }
        return dtoList;
    }

    private CartDto convertToDto(CartVo cartVo){
        CartDto dto = new CartDto();
        dto.setCartVo(cartVo);
        dto.setOptionList(cartUserOptionRepository.getList(cartVo.getNo()));
        return dto;
    }

    public List<CartDto> convertToDto(Long userNo, Long productNo, List<Long> optionList, Long quantity){
        CartDto dto = new CartDto();

        ProductVo productVo = productRepository.getByNo(productNo);
        List<ImageVo> imageVoList = imageRepository.getList(productNo);
        CartVo vo = new CartVo();
        vo.setUserNo(userNo);
        vo.setQuantity(quantity);
        vo.setProductNo(productNo);
        vo.setDiscountRate(productVo.getDiscountRate());
        vo.setPrice(productVo.getPrice());
        vo.setProductName(productVo.getName());
        for(ImageVo imageVo : imageVoList){
            if(imageVo.getOrderNo() == 0){
                vo.setSaveName(imageVo.getSaveName());
            }
        }
        dto.setCartVo(vo);

        List<OptionVo> optionVoList = new ArrayList<>();
        if(optionList != null){
            for(Long oNo : optionList){
                OptionVo optionVo = optionRepository.getByNo(oNo);
                optionVoList.add(optionVo);
            }
        }
        dto.setOptionList(optionVoList);

        List<CartDto> cartDtoList = new ArrayList<>();
        cartDtoList.add(dto);
        return cartDtoList;
    }
}
