package com.cafe24.bitmall.repository;

import com.cafe24.bitmall.vo.OptionVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OptionRepository {
    @Autowired
    private SqlSession sqlSession;

    public List<OptionVo> getList() {
        return sqlSession.selectList("option.getBig");
    }

    public List<OptionVo> getList(Long no) {
        return sqlSession.selectList("option.getSmall", no);
    }

    public OptionVo getByNo(Long no) {
        return sqlSession.selectOne("option.getByNo", no);
    }

    public int insert(OptionVo vo) {
        return sqlSession.insert("option.insert", vo);
    }

    public int update(OptionVo vo) {
        return sqlSession.update("option.update", vo);
    }

    public int delete(Long no) {
        return sqlSession.delete("option.delete", no);
    }
}
