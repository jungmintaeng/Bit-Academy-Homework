package com.cafe24.bitmall.vo;

public class PaymentVo {
    private Long no;
    private Long orderNo;
    private String payerName;
    private String code;
    private Long paymentMethodNo;
    private String paymentMethodName;
    private Integer amount;

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

    public String getPayerName() {
        return payerName;
    }

    public void setPayerName(String payerName) {
        this.payerName = payerName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getPaymentMethodNo() {
        return paymentMethodNo;
    }

    public void setPaymentMethodNo(Long paymentMethodNo) {
        this.paymentMethodNo = paymentMethodNo;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getPaymentMethodName() {
        return paymentMethodName;
    }

    public void setPaymentMethodName(String paymentMethodName) {
        this.paymentMethodName = paymentMethodName;
    }

    @Override
    public String toString() {
        return "PaymentVo{" +
                "no=" + no +
                ", orderNo=" + orderNo +
                ", payerName='" + payerName + '\'' +
                ", code='" + code + '\'' +
                ", paymentMethodNo=" + paymentMethodNo +
                ", paymentMethodName='" + paymentMethodName + '\'' +
                ", amount=" + amount +
                '}';
    }
}
