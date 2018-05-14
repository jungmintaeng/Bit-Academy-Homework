package com.cafe24.bitmall.repository;

import com.cafe24.bitmall.vo.FaqVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FaqRepository {
    @Autowired
    private SqlSession sqlSession;

    public List<FaqVo> getList(){
        return sqlSession.selectList("faq.getList");
    }

    public FaqVo getByNo(Long no){
        return sqlSession.selectOne("faq.getByNo", no);
    }

    public int insert(FaqVo vo){
        return sqlSession.insert("faq.insert", vo);
    }

    public int update(FaqVo vo){
        return sqlSession.update("faq.update", vo);
    }

    public int delete(Long no){
        return sqlSession.delete("faq.delete", no);
    }
}
