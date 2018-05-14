package com.cafe24.bitmall.vo;

public class ImageVo {
    private Long no;
    private Long productNo;
    private String uploadName;
    private String saveName;
    private Integer orderNo;

    public Long getNo() {
        return no;
    }

    public void setNo(Long no) {
        this.no = no;
    }

    public Long getProductNo() {
        return productNo;
    }

    public void setProductNo(Long productNo) {
        this.productNo = productNo;
    }

    public String getUploadName() {
        return uploadName;
    }

    public void setUploadName(String uploadName) {
        this.uploadName = uploadName;
    }

    public String getSaveName() {
        return saveName;
    }

    public void setSaveName(String saveName) {
        this.saveName = saveName;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    @Override
    public String toString() {
        return "ImageVo{" +
                "no=" + no +
                ", productNo=" + productNo +
                ", uploadName='" + uploadName + '\'' +
                ", saveName='" + saveName + '\'' +
                ", orderNo=" + orderNo +
                '}';
    }
}
