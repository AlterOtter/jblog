package com.poscoict.jblog.vo;

import com.poscoict.jblog.vo.CategoryVo.Builder;

public class BlogVo {
	private String title;
	private String imgs;
	private String user_id;
	
	public BlogVo() {
		// TODO Auto-generated constructor stub
	}
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImgs() {
		return imgs;
	}
	public void setImgs(String imgs) {
		this.imgs = imgs;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	private BlogVo(Builder builder) {
		this.title = builder.title;
		this.imgs=builder.imgs;
		this.user_id = builder.user_id;
    }

  
	public static Builder builder(){
        return new Builder();
    } 

	public static class Builder{
   
		private String title;
		private String imgs;
		private String user_id;
	    
        public Builder(){
          
        }
        
     
        
        public Builder title(String title){
            this.title = title;
            return this;
        }
        public Builder imgs(String imgs){
            this.imgs = imgs;
            return this;
        }
        public Builder user_id(String user_id){
            this.user_id = user_id;
            return this;
        }
     
      
 
        
        public BlogVo build(){
            return new BlogVo(this);
        }
    }
}
