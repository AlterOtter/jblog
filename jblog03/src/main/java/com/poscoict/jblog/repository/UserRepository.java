package com.poscoict.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscoict.jblog.vo.UserVo;

@Repository
public class UserRepository {

	@Autowired
	private SqlSession sqlSession;
	
	public boolean user_join(UserVo vo) {
		
		boolean result =sqlSession.insert("user.insert",vo)==1;
		
		if(result==false) {
			throw new RuntimeException("유저 등록 실패 입력 : "+vo.toString());
		}
		
		return true;
	}

	public UserVo login(UserVo vo) {
		
		return sqlSession.selectOne("user.login",vo);
	}
	
	public boolean check_id(String user_id) {
		return sqlSession.selectOne("user.checkid",user_id)!=null;
	}
	
}
