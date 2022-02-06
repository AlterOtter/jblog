package com.poscoict.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscoict.jblog.vo.CategoryVo;
import com.poscoict.jblog.vo.PostVo;

@Repository
public class PostRepository {
	@Autowired
	private SqlSession sqlSession;
	
	public List<PostVo> getPost(List<CategoryVo> list){
		
		System.out.println(list.size());
		return sqlSession.selectList("post.getpost", list);
	}

	public boolean insert(PostVo postvo) {
		
		return sqlSession.insert("post.insert", postvo)==1;
	}

	public PostVo readpost(Integer post) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("post.readpost",post);
	}
}
