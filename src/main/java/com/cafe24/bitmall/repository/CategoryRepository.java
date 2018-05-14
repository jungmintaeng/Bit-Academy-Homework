package com.cafe24.bitmall.repository;

import com.cafe24.bitmall.vo.CategoryVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryRepository {
    @Autowired
    private SqlSession sqlSession;

    public List<CategoryVo> getList(){
        return sqlSession.selectList("category.getList");
    }

    public CategoryVo getByNo(Long no) { return sqlSession.selectOne("category.getByNo", no); }

    public int insert(CategoryVo vo) {
        return sqlSession.insert("category.insert",vo);
    }

    public int update(CategoryVo vo){
        return sqlSession.update("category.update", vo);
    }

    public int delete(Long no) {
        return sqlSession.delete("category.delete", no);
    }
}
