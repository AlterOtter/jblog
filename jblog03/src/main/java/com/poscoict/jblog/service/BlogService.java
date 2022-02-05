package com.poscoict.jblog.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.poscoict.jblog.repository.BlogRepository;
import com.poscoict.jblog.vo.BlogVo;
import com.poscoict.jblog.vo.CategoryVo;
import com.poscoict.jblog.vo.PostVo;
import com.poscoict.jblog.vo.UserVo;

@Service
public class BlogService {
	private String SAVE_PATH="/upload-images";
	private String URL_BASE="/images";
	
	@Autowired
	private BlogRepository brepository;
	
	@Autowired
	private CategoryService categoryservice;
	
	@Autowired
	private PostService postservice;
	
	public boolean insert(BlogVo vo) {
		
		return brepository.insert(vo);
	}

	public void getblogAllinfo(String user_id, Model model) {
		BlogVo bvo=brepository.getblog(BlogVo.builder().user_id(user_id).build());
		List<CategoryVo> clist= categoryservice.getCategory(CategoryVo.builder().user_id(user_id).build());
		List<PostVo> plist = postservice.getPost(clist);
		Map<String, Object> map = new HashMap<>();
		map.put("blog", bvo);
		map.put("category", clist);
		map.put("post",plist);
		model.addAttribute("blogInfo",map);
	}
	
	public BlogVo getblogMaininfo(String user_id) {
		return brepository.getblog(BlogVo.builder().user_id(user_id).build());
	}

	public boolean updateBlog(BlogVo vo,MultipartFile multipartFile) {
		if(multipartFile!=null && (multipartFile.getSize()!=0) ) {
			System.out.println("UpdateImg");
			String imgs=restore(multipartFile);
			vo.setImgs(imgs);
			brepository.updateImg(vo);
		}

		if(vo.getTitle()!=null) {
			brepository.updateInfo(vo);
		}
		
		return true;
	}
	
	
	
	
	//
	//파일 저장 함수
	//
	public String restore(MultipartFile multipartFile) {
		String url = null;
		
		try {
			if(multipartFile.isEmpty()) {
				return url;
			}
			
			String originFilename = multipartFile.getOriginalFilename();
			String extName = originFilename.substring(originFilename.lastIndexOf('.')+1);
			String saveFilename = genearteSaveFilename(extName);
			long fileSize = multipartFile.getSize();
			
			byte[] data = multipartFile.getBytes();
			OutputStream os = new FileOutputStream(SAVE_PATH + "/" + saveFilename);
			os.write(data);
			os.close();
			
			url = URL_BASE + "/" + saveFilename;
			
		} catch(IOException ex) {
			throw new RuntimeException("file upload error:" + ex);
		}
		
		return url;
	}
	
	//
	//파일 이름 생성 함수
	//
	private String genearteSaveFilename(String extName) {
		String filename = "";
		
		Calendar calendar = Calendar.getInstance();
		
		filename += calendar.get(Calendar.YEAR);
		filename += calendar.get(Calendar.MONTH);
		filename += calendar.get(Calendar.DATE);
		filename += calendar.get(Calendar.HOUR);
		filename += calendar.get(Calendar.MINUTE);
		filename += calendar.get(Calendar.SECOND);
		filename += calendar.get(Calendar.MILLISECOND);
		filename += ("." + extName);
		
		return filename;
	}

	
}
