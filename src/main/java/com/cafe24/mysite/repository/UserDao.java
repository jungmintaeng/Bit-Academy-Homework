package com.cafe24.mysite.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mysite.exception.UserDaoException;
import com.cafe24.mysite.vo.UserVo;

@Repository
public class UserDao {

	@Autowired
	private SqlSession sqlSession;
	/**
	 * 회원 정보 수정 시 미리 세팅할 유저 정보
	 * @param no
	 * @return
	 */
	public UserVo get(Long no) throws UserDaoException{
		return sqlSession.selectOne("user.getByNo", no);
	}
	
	/**
	 * Login시 가져올 유저 정보
	 * @param email - UserID
	 * @param password - UserPassword
	 * @return UserVo
	 */
	public UserVo get(UserVo vo) throws UserDaoException{
		return sqlSession.selectOne("user.getByEmailAndPassword", vo);
	}
	
	public boolean isEmailExists(String email) {
		return sqlSession.selectOne("user.isEmailExists", email);
	}
	
	/**
	 * 회원 가입 시 회원 정보를 DB에 넣는 메소드
	 * @param vo
	 * @return
	 */
	public boolean insert(UserVo vo) {
		return sqlSession.insert("user.insert", vo) > 0;
	}
	
	/**
	 * 회원의 정보를 수정하는 메소드
	 */
	public boolean update(UserVo vo) {
		return sqlSession.update("user.update", vo) > 0;
	}
}
