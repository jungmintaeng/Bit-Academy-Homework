package com.cafe24.bitmall.vo;

public class CartVo {
    private Long no;
    private Long userNo;
    private Long productNo;
    private String productName;
    private Integer price;
    private Float discountRate;
    private Long quantity;
    private String saveName;

    public Long getNo() {
        return no;
    }

    public void setNo(Long no) {
        this.no = no;
    }

    public Long getUserNo() {
        return userNo;
    }

    public void setUserNo(Long userNo) {
        this.userNo = userNo;
    }

    public Long getProductNo() {
        return productNo;
    }

    public void setProductNo(Long productNo) {
        this.productNo = productNo;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getSaveName() {
        return saveName;
    }

    public void setSaveName(String saveName) {
        this.saveName = saveName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Float getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(Float discountRate) {
        this.discountRate = discountRate;
    }

    @Override
    public String toString() {
        return "CartVo{" +
                "no=" + no +
                ", userNo=" + userNo +
                ", productNo=" + productNo +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", discountRate=" + discountRate +
                ", quantity=" + quantity +
                ", saveName='" + saveName + '\'' +
                '}';
    }
}
