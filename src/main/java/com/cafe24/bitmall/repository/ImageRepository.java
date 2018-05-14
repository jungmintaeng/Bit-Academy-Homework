package com.cafe24.bitmall.repository;

import com.cafe24.bitmall.vo.ImageVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ImageRepository {
    @Autowired
    private SqlSession sqlSession;

    public List<ImageVo> getList(Long no){
        return sqlSession.selectList("image.getList", no);
    }

    public int insert(ImageVo vo){
        return sqlSession.insert("image.insert", vo);
    }

    public int delete(Long no){
        return sqlSession.delete("image.delete", no);
    }

    public int deleteByNo(Long no){ return sqlSession.delete("image.deleteByNo", no); }
}
