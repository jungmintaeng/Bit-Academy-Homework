package com.cafe24.bitmall.dto;

import java.util.List;

public class OptionDto {
    private Long no;
    private String name;
    private Long parentNo;
    private List<OptionDto> optionDtoList;

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

    public List<OptionDto> getOptionDtoList() {
        return optionDtoList;
    }

    public void setOptionDtoList(List<OptionDto> optionDtoList) {
        this.optionDtoList = optionDtoList;
    }

    @Override
    public String toString() {
        return "OptionDto{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", parentNo=" + parentNo +
                ", optionDtoList=" + optionDtoList +
                '}';
    }
}
