package com.poscoict.jblog.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.poscoict.jblog.repository.BlogRepository;
import com.poscoict.jblog.vo.BlogVo;
import com.poscoict.jblog.vo.CategoryVo;
import com.poscoict.jblog.vo.PostVo;

@Service
public class BlogService {
	
	@Autowired
	private BlogRepository brepository;
	
	@Autowired
	private CategoryService categoryservice;
	
	@Autowired
	private PostService postservice;
	
	public boolean insert(BlogVo vo) {
		
		return brepository.insert(vo);
	}

	public void getbloginfo(String user_id, Model model) {
		BlogVo bvo=brepository.getblog(BlogVo.builder().user_id(user_id).build());
		List<CategoryVo> clist= categoryservice.getCategory(CategoryVo.builder().user_id(user_id).build());
		List<PostVo> plist = postservice.getPost(clist);
		Map<String, Object> map = new HashMap<>();
		map.put("blog", bvo);
		map.put("category", clist);
		map.put("post",plist);
		model.addAttribute("blogInfo",map);
	}
	
}
