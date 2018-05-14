package com.cafe24.bitmall.dto;

import com.cafe24.bitmall.vo.OrderVo;
import com.cafe24.bitmall.vo.OrderedProductVo;

import java.util.List;

public class OrderDto {
    private OrderVo orderVo;
    private List<OrderedProductVo> orderedProductList;
    private Integer price;

    public OrderVo getOrderVo() {
        return orderVo;
    }

    public void setOrderVo(OrderVo orderVo) {
        this.orderVo = orderVo;
    }

    public List<OrderedProductVo> getOrderedProductList() {
        return orderedProductList;
    }

    public void setOrderedProductList(List<OrderedProductVo> orderedProductList) {
        this.orderedProductList = orderedProductList;
        setPrice();
    }

    public Integer getPrice() {
        return price;
    }

    private void setPrice() {
        if(orderedProductList == null){
            this.price = null;
            return;
        }

        Integer sumPrice = 0;

        for (OrderedProductVo vo : orderedProductList){
            sumPrice += (int)(vo.getPriceFixed() * vo.getQuantity() * (100 - vo.getDiscountRateFixed()) / 100);
        }

        this.price = sumPrice;
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "orderVo=" + orderVo +
                ", orderedProductList=" + orderedProductList +
                ", price=" + price +
                '}';
    }
}
