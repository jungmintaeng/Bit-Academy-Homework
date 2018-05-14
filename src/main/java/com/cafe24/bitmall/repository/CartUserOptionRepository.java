package com.cafe24.bitmall.repository;

import com.cafe24.bitmall.vo.OptionVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CartUserOptionRepository {
    @Autowired
    private SqlSession sqlSession;

    public List<OptionVo> getList(Long no){
        return sqlSession.selectList("cartuseroption.getList", no);
    }

    public Long checkRedundancy(List<Long> optionList){
        Map<String, Object> map = new HashMap<>();
        map.put("listSize", optionList.size());
        map.put("optionList", optionList);
        return sqlSession.selectOne("cartuseroption.checkRedundancy", map);
    }

    public int insert(Long cartNo, Long optionNo){
        Map<String, Long> map = new HashMap<>();
        map.put("cartNo", cartNo);
        map.put("optionNo", optionNo);
        return sqlSession.insert("cartuseroption.insert", map);
    }
}
