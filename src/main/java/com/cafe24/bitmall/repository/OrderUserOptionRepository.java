package com.cafe24.bitmall.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class OrderUserOptionRepository {
    private SqlSession sqlSession;

    public int insert(Long orderedProductNo, Long optionNo) {
        Map<String, Long> map = new HashMap<>();
        map.put("orderedProductNo", orderedProductNo);
        map.put("optionNo", optionNo);
        return sqlSession.insert("orderuseroption.insert", map);
    }
}
