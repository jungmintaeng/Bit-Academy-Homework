package com.cafe24.bitmall.dto;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class ImageDto {
    private String uploadName;
    private MultipartFile image;
    private List<ImageDto> imageList;

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public List<ImageDto> getImageList() {
        return imageList;
    }

    public void setImageList(List<ImageDto> imageList) {
        this.imageList = imageList;
    }

    public String getUploadName() {
        return uploadName;
    }

    public void setUploadName(String uploadName) {
        this.uploadName = uploadName;
    }

    @Override
    public String toString() {
        return "ImageDto{" +
                "uploadName='" + uploadName + '\'' +
                ", image=" + image +
                ", imageList=" + imageList +
                '}';
    }
}
