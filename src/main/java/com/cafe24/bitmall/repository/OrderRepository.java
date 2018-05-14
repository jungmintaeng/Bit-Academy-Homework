package com.cafe24.bitmall.repository;

import com.cafe24.bitmall.vo.OrderVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderRepository {
    @Autowired
    private SqlSession sqlSession;

    public List<OrderVo> getList(){
        return sqlSession.selectList("order.getList");
    }

    public OrderVo getByNo(Long no){
        return sqlSession.selectOne("order.getByNo", no);
    }

    public int insert(OrderVo vo){
        return sqlSession.insert("order.insert", vo);
    }

    //state만
    public int update(OrderVo vo){
        return sqlSession.update("order.update", vo);
    }

    public int delete(Long no){
        return sqlSession.delete("order.delete", no);
    }
}
