package com.poscoict.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

	public boolean insert(PostVo postvo) {
		
		return prepository.insert(postvo);
	}

	public String readpost(Integer post) {
		PostVo vo=prepository.readpost(post);
		String result="false";
		
			if(vo!=null)
				result=getJSON(vo);
	
		return result;
	}
	
	
	public String getJSON(PostVo vo) {
		ObjectMapper objmapper= new ObjectMapper();
	
		try {
			return objmapper.writeValueAsString(vo);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return "false";
		}
		
	}

	public String getJSON(List<PostVo> vo) {
		ObjectMapper objmapper= new ObjectMapper();
	
		try {
			return objmapper.writeValueAsString(vo);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return "false";
		}
		
	}
	
	public String readpostlist(Integer category) {
		List<PostVo> vo=prepository.readpostlist(category);
		String result="false";
		
			if(vo!=null)
				result=getJSON(vo);
	
		return result;
	}
	
	
}
