package com.cafe24.bitmall.repository;

import com.cafe24.bitmall.vo.ProductVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepository {
    @Autowired
    private SqlSession sqlSession;

    public List<ProductVo> getList(){
        return sqlSession.selectList("product.getList");
    }

    public List<ProductVo> getNewList(){
        return sqlSession.selectList("product.getNewList");
    }

    public List<ProductVo> getHitList(){
        return sqlSession.selectList("product.getHitList");
    }

    public List<ProductVo> getDiscountList(){
        return sqlSession.selectList("product.getDiscountList");
    }

    public List<ProductVo> getCategoryProductList(Long no){ return sqlSession.selectList("product.getCategoryProductList", no);}

    public ProductVo getByNo(Long no){
        return sqlSession.selectOne("product.getByNo", no);
    }

    public int insert(ProductVo vo){
        return sqlSession.insert("product.insert", vo);
    }

    public int update(ProductVo vo){
        return sqlSession.update("product.update", vo);
    }

    public int delete(Long no){
        return sqlSession.delete("product.delete", no);
    }

}
