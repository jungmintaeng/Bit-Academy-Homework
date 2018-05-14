package com.cafe24.bitmall.repository;

import com.cafe24.bitmall.vo.CartVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CartRepository {
    @Autowired
    private SqlSession sqlSession;

    public Long checkRedundancy(Long userNo, Long productNo){
        Map<String, Long> map = new HashMap<>();
        map.put("userNo", userNo);
        map.put("productNo", productNo);
        return sqlSession.selectOne("cart.checkRedundancy", map);
    }

    public List<CartVo> getList(Long no){
        return sqlSession.selectList("cart.getList", no);
    }

    public CartVo getByNo(Long no){ return sqlSession.selectOne("cart.getByNo", no); }

    public Long getQuantity(CartVo vo){
        return sqlSession.selectOne("cart.getQuantity", vo);
    }

    public int insert(CartVo vo){
        return sqlSession.insert("cart.insert", vo);
    }

    public int update(Long cartNo, Long quantity){
        Map<String, Long> map = new HashMap<>();
        map.put("cartNo", cartNo);
        map.put("quantity", quantity);
        return sqlSession.update("cart.update", map);
    }

    public int addQuantity(Long cartNo, Long quantity){
        Map<String, Long> map = new HashMap<>();
        map.put("cartNo", cartNo);
        map.put("quantity", quantity);
        return sqlSession.update("cart.addQuantity", map);
    }

    public int delete(Long no){
        return sqlSession.delete("cart.delete", no);
    }

    public int deleteAll(Long no){
        return sqlSession.delete("cart.deleteAll", no);
    }
}
