package com.cafe24.bitmall.repository;

import com.cafe24.bitmall.vo.UserVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {
    @Autowired
    private SqlSession sqlSession;

    public List<UserVo> getList(){
        return sqlSession.selectList("user.getList");
    }

    public UserVo getUser(UserVo vo) {
        return sqlSession.selectOne("user.getUser", vo);
    }

    public UserVo getUserByNo(Long no) {
        return sqlSession.selectOne("user.getUserByNo", no);
    }

    public int update(UserVo vo) {
        return sqlSession.update("user.update", vo);
    }

    public int insert(UserVo vo) {
        return sqlSession.insert("user.insert", vo);
    }

    public int delete(Long no){
        return sqlSession.delete("user.delete", no);
    }
}
