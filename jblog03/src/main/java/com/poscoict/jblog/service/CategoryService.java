package com.poscoict.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.poscoict.jblog.repository.CategoryRepository;
import com.poscoict.jblog.vo.CategoryVo;
import com.poscoict.jblog.vo.UserVo;

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
	
	public boolean getcategory(UserVo vo, Model model) {
		List<CategoryVo> clist = getCategory(CategoryVo.builder().user_id(vo.getUser_id()).build());
		model.addAttribute("category",clist);
		return true;
	}

	public boolean check_before_delete(CategoryVo vo){
		if(vo.getUser_id()==null||vo.getUser_id().isEmpty()) {
			return false;
		}
		
		return crepository.getCategory(vo).size() >=2;
	}
	
	
	public boolean deleteone(CategoryVo vo) {
		if(!check_before_delete(vo)) {
			return false;
		}
		
		
		return 	crepository.deleteCategory(vo);
	}
}
