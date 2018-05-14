package com.cafe24.bitmall.vo;

public class FaqVo {
    private Long no;
    private Long writerNo;
    private String title;
    private String content;

    public Long getNo() {
        return no;
    }

    public void setNo(Long no) {
        this.no = no;
    }

    public Long getWriterNo() {
        return writerNo;
    }

    public void setWriterNo(Long writerNo) {
        this.writerNo = writerNo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "FaqVo{" +
                "no=" + no +
                ", writerNo=" + writerNo +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
