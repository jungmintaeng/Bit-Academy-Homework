package com.cafe24.bitmall.service;

import com.cafe24.bitmall.dto.CartDto;
import com.cafe24.bitmall.repository.OrderRepository;
import com.cafe24.bitmall.repository.OrderUserOptionRepository;
import com.cafe24.bitmall.repository.OrderedProductRepository;
import com.cafe24.bitmall.vo.OptionVo;
import com.cafe24.bitmall.vo.OrderVo;
import com.cafe24.bitmall.vo.OrderedProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderedProductRepository orderedProductRepository;
    @Autowired
    private OrderUserOptionRepository orderUserOptionRepository;

    public void addOrder(OrderVo orderVo, List<CartDto> cartDtoList){
        orderRepository.insert(orderVo);
        for(CartDto dto : cartDtoList){
            OrderedProductVo vo = new OrderedProductVo();
            vo.setOrderNo(orderVo.getNo());
            vo.setProductNo(dto.getCartVo().getProductNo());
            vo.setQuantity(dto.getCartVo().getQuantity());
            orderedProductRepository.insert(vo);

            List<OptionVo> optionVos = dto.getOptionList();
            for(OptionVo o : optionVos){
                orderUserOptionRepository.insert(vo.getNo(), o.getNo());
            }
        }
    }
}
