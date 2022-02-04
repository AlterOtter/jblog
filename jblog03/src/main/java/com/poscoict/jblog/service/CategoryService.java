package com.poscoict.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscoict.jblog.repository.CategoryRepository;
import com.poscoict.jblog.vo.CategoryVo;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository crepository;
	
	public boolean insert(CategoryVo vo) {
		
		return crepository.insert(vo);
	}
	
	public List<CategoryVo> getCategory(CategoryVo vo){
		return crepository.getCategory(vo);
	}
}
