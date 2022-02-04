package com.poscoict.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscoict.jblog.repository.PostRepository;
import com.poscoict.jblog.vo.CategoryVo;
import com.poscoict.jblog.vo.PostVo;

@Service
public class PostService {
	
	@Autowired
	private PostRepository prepository;
	
	public List<PostVo> getPost(List<CategoryVo> list){
		
		return prepository.getPost(list);
	}
}
