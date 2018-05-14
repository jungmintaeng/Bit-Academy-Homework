package com.cafe24.bitmall.repository;

import com.cafe24.bitmall.vo.OptionVo;
import com.cafe24.bitmall.vo.ProductOptionVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductOptionRepository {
    @Autowired
    private SqlSession sqlSession;

    public List<OptionVo> getList(Long no){
        return sqlSession.selectList("productoption.getList", no);
    }

    public int insert(ProductOptionVo vo){
        return sqlSession.insert("productoption.insert", vo);
    }

    public int delete(Long no){
        return sqlSession.delete("productoption.delete", no);
    }
}
