package com.cafe24.bitmall.dto;

import com.cafe24.bitmall.vo.CategoryVo;
import com.cafe24.bitmall.vo.ImageVo;
import com.cafe24.bitmall.vo.OptionVo;
import com.cafe24.bitmall.vo.ProductVo;

import java.util.List;

public class ProductDto {
    private ProductVo productVo;
    private List<CategoryVo> allCategoryVos;
    private List<OptionVo> allOptionVos;
    private List<OptionVo> optionVos;
    private List<ImageVo> imageVos;

    public ProductVo getProductVo() {
        return productVo;
    }

    public void setProductVo(ProductVo productVo) {
        this.productVo = productVo;
    }

    public List<CategoryVo> getAllCategoryVos() {
        return allCategoryVos;
    }

    public void setAllCategoryVos(List<CategoryVo> allCategoryVos) {
        this.allCategoryVos = allCategoryVos;
    }

    public List<OptionVo> getAllOptionVos() {
        return allOptionVos;
    }

    public void setAllOptionVos(List<OptionVo> allOptionVos) {
        this.allOptionVos = allOptionVos;
    }

    public List<OptionVo> getOptionVos() {
        return optionVos;
    }

    public void setOptionVos(List<OptionVo> optionVos) {
        this.optionVos = optionVos;
    }

    public List<ImageVo> getImageVos() {
        return imageVos;
    }

    public void setImageVos(List<ImageVo> imageVos) {
        this.imageVos = imageVos;
    }

    @Override
    public String toString() {
        return "ProductDto{" +
                "productVo=" + productVo +
                ", allCategoryVos=" + allCategoryVos +
                ", allOptionVos=" + allOptionVos +
                ", optionVos=" + optionVos +
                ", imageVos=" + imageVos +
                '}';
    }
}
