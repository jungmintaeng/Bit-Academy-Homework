package com.cafe24.bitmall.vo;

public class OrderedProductVo {
    private Long no;
    private Long orderNo;
    private Long productNo;
    private String productName;
    private Long optionNo;
    private String optionName;
    private Long quantity;
    private Integer priceFixed;
    private Float discountRateFixed;

    public Long getNo() {
        return no;
    }

    public void setNo(Long no) {
        this.no = no;
    }

    public Long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

    public Long getProductNo() {
        return productNo;
    }

    public void setProductNo(Long productNo) {
        this.productNo = productNo;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Integer getPriceFixed() {
        return priceFixed;
    }

    public void setPriceFixed(Integer priceFixed) {
        this.priceFixed = priceFixed;
    }

    public Float getDiscountRateFixed() {
        return discountRateFixed;
    }

    public void setDiscountRateFixed(Float discountRateFixed) {
        this.discountRateFixed = discountRateFixed;
    }

    public Long getOptionNo() {
        return optionNo;
    }

    public void setOptionNo(Long optionNo) {
        this.optionNo = optionNo;
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public String toString() {
        return "OrderedProductVo{" +
                "orderNo=" + orderNo +
                ", productNo=" + productNo +
                ", productName='" + productName + '\'' +
                ", optionNo=" + optionNo +
                ", optionName='" + optionName + '\'' +
                ", quantity=" + quantity +
                ", priceFixed=" + priceFixed +
                ", discountRateFixed=" + discountRateFixed +
                '}';
    }
}
