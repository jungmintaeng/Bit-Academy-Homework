package com.cafe24.bitmall.vo;

public class OrderVo {
    private Long no;
    private String code;
    private Long oNo;
    private String oName;
    private String oTelNumber;
    private String oPhoneNumber;
    private String oEmail;
    private String oZipcode;
    private String oAddress;
    private String rName;
    private String rTelNumber;
    private String rPhoneNumber;
    private String rEmail;
    private String rZipcode;
    private String rAddress;
    private String requirement;
    private String orderDate;
    private String state;
    private Long paymentMethodNo;
    private String paymentMethodName;

    public Long getNo() {
        return no;
    }

    public void setNo(Long no) {
        this.no = no;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getoNo() {
        return oNo;
    }

    public void setoNo(Long oNo) {
        this.oNo = oNo;
    }

    public String getoName() {
        return oName;
    }

    public void setoName(String oName) {
        this.oName = oName;
    }

    public String getoTelNumber() {
        return oTelNumber;
    }

    public void setoTelNumber(String oTelNumber) {
        this.oTelNumber = oTelNumber;
    }

    public String getoPhoneNumber() {
        return oPhoneNumber;
    }

    public void setoPhoneNumber(String oPhoneNumber) {
        this.oPhoneNumber = oPhoneNumber;
    }

    public String getoEmail() {
        return oEmail;
    }

    public void setoEmail(String oEmail) {
        this.oEmail = oEmail;
    }

    public String getoZipcode() {
        return oZipcode;
    }

    public void setoZipcode(String oZipcode) {
        this.oZipcode = oZipcode;
    }

    public String getoAddress() {
        return oAddress;
    }

    public void setoAddress(String oAddress) {
        this.oAddress = oAddress;
    }

    public String getrName() {
        return rName;
    }

    public void setrName(String rName) {
        this.rName = rName;
    }

    public String getrTelNumber() {
        return rTelNumber;
    }

    public void setrTelNumber(String rTelNumber) {
        this.rTelNumber = rTelNumber;
    }

    public String getrPhoneNumber() {
        return rPhoneNumber;
    }

    public void setrPhoneNumber(String rPhoneNumber) {
        this.rPhoneNumber = rPhoneNumber;
    }

    public String getrEmail() {
        return rEmail;
    }

    public void setrEmail(String rEmail) {
        this.rEmail = rEmail;
    }

    public String getrZipcode() {
        return rZipcode;
    }

    public void setrZipcode(String rZipcode) {
        this.rZipcode = rZipcode;
    }

    public String getrAddress() {
        return rAddress;
    }

    public void setrAddress(String rAddress) {
        this.rAddress = rAddress;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Long getPaymentMethodNo() {
        return paymentMethodNo;
    }

    public void setPaymentMethodNo(Long paymentMethodNo) {
        this.paymentMethodNo = paymentMethodNo;
    }

    public String getPaymentMethodName() {
        return paymentMethodName;
    }

    public void setPaymentMethodName(String paymentMethodName) {
        this.paymentMethodName = paymentMethodName;
    }

    @Override
    public String toString() {
        return "OrderVo{" +
                "no=" + no +
                ", code='" + code + '\'' +
                ", oNo=" + oNo +
                ", oName='" + oName + '\'' +
                ", oTelNumber='" + oTelNumber + '\'' +
                ", oPhoneNumber='" + oPhoneNumber + '\'' +
                ", oEmail='" + oEmail + '\'' +
                ", oZipcode='" + oZipcode + '\'' +
                ", oAddress='" + oAddress + '\'' +
                ", rName='" + rName + '\'' +
                ", rTelNumber='" + rTelNumber + '\'' +
                ", rPhoneNumber='" + rPhoneNumber + '\'' +
                ", rEmail='" + rEmail + '\'' +
                ", rZipcode='" + rZipcode + '\'' +
                ", rAddress='" + rAddress + '\'' +
                ", requirement='" + requirement + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", state='" + state + '\'' +
                ", paymentMethodNo=" + paymentMethodNo +
                ", paymentMethodName='" + paymentMethodName + '\'' +
                '}';
    }
}
