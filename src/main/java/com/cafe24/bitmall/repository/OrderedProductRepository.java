package com.cafe24.bitmall.repository;

import com.cafe24.bitmall.vo.OrderedProductVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderedProductRepository {
    @Autowired
    private SqlSession sqlSession;

    public List<OrderedProductVo> getList(Long no){
        return sqlSession.selectList("ordered_product.getList", no);
    }

    public int insert(OrderedProductVo vo){
        return sqlSession.insert("ordered_product.insert", vo);
    }
}
