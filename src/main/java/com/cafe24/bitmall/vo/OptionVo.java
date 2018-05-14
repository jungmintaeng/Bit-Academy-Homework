package com.cafe24.bitmall.vo;

public class OptionVo {
    private Long no;
    private String name;
    private Long parentNo;

    public Long getNo() {
        return no;
    }

    public void setNo(Long no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentNo() {
        return parentNo;
    }

    public void setParentNo(Long parentNo) {
        this.parentNo = parentNo;
    }

    @Override
    public String toString() {
        return "OptionVo{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", parentNo=" + parentNo +
                '}';
    }
}
