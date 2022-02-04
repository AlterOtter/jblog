package com.poscoict.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscoict.jblog.vo.BlogVo;

@Repository
public class BlogRepository {
	@Autowired
	private SqlSession sqlSession;
	
	public boolean insert(BlogVo vo) {
		
		boolean result =sqlSession.insert("blog.insert",vo)==1;
		
		if(result==false) {
			throw new RuntimeException("유저 등록 실패 입력 : "+vo.toString());
		}
		
		return true;
	}
	
	public BlogVo getblog(BlogVo vo) {
		
		return sqlSession.selectOne("blog.getblog",vo);
	}
}
