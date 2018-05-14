package com.cafe24.bitmall.vo;

public class ProductOptionVo {
    private Long productNo;
    private Long optionNo;

    public Long getProductNo() {
        return productNo;
    }

    public void setProductNo(Long productNo) {
        this.productNo = productNo;
    }

    public Long getOptionNo() {
        return optionNo;
    }

    public void setOptionNo(Long optionNo) {
        this.optionNo = optionNo;
    }

    @Override
    public String toString() {
        return "ProductOptionVo{" +
                "productNo=" + productNo +
                ", optionNo=" + optionNo +
                '}';
    }
}