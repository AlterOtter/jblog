package com.poscoict.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscoict.jblog.vo.CategoryVo;

@Repository
public class CategoryRepository {

	@Autowired
	private SqlSession sqlSession;
	
	public boolean insert(CategoryVo vo) {
		boolean result =sqlSession.insert("category.insert",vo)==1;
		if(result==false) {
			throw new RuntimeException("유저 등록 실패 입력 : "+vo.toString());
		}
		return true;
	}
	
	
	public List<CategoryVo> getCategory(CategoryVo vo) {
		
		return 	sqlSession.selectList("category.getcategory",vo);
	}
	
	public boolean deleteCategory(Integer no) {
		return 	sqlSession.delete("category.deleteCategory",no)==1;
	}
	
}
