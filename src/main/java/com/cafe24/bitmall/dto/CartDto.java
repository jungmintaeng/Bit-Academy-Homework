package com.cafe24.bitmall.dto;

import com.cafe24.bitmall.vo.CartVo;
import com.cafe24.bitmall.vo.OptionVo;

import java.util.List;

public class CartDto {
    private CartVo cartVo;
    private List<OptionVo> optionList;

    public CartVo getCartVo() {
        return cartVo;
    }

    public void setCartVo(CartVo cartVo) {
        this.cartVo = cartVo;
    }

    public List<OptionVo> getOptionList() {
        return optionList;
    }

    public void setOptionList(List<OptionVo> optionList) {
        this.optionList = optionList;
    }

    @Override
    public String toString() {
        return "CartDto{" +
                "cartVo=" + cartVo +
                ", optionList=" + optionList +
                '}';
    }
}
