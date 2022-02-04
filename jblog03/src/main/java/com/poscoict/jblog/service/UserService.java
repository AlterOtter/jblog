package com.poscoict.jblog.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscoict.jblog.repository.BlogRepository;
import com.poscoict.jblog.repository.CategoryRepository;
import com.poscoict.jblog.repository.UserRepository;
import com.poscoict.jblog.vo.BlogVo;
import com.poscoict.jblog.vo.CategoryVo;
import com.poscoict.jblog.vo.UserVo;


@Service
public class UserService {
	
	private static final Log LOG = LogFactory.getLog( UserService.class );
	
	@Autowired
	UserRepository urepository;
	
	@Autowired
	BlogService bservice;
	
	@Autowired 
	CategoryService cservice;
	
	public boolean joinService(UserVo vo) {
		try {
			urepository.user_join(vo);
			bservice.insert(new BlogVo().builder().title(vo.getName()+" 님의 블로그").imgs("/images/20221491915387.gif").user_id(vo.getUser_id()).build());
			cservice.insert(new CategoryVo().builder().name("기본 카테고리").description("설명을 입력해주세요").user_id(vo.getUser_id()).build());
		} catch (Exception e) {
			LOG.error("message: "+e.getMessage());
			LOG.info("input value : ");
			return false;
		}
		
		return true;
	}

	public UserVo login(UserVo vo) {
		
		return urepository.login(vo);
	}
}
