package com.poscoict.jblog.vo;

import com.poscoict.jblog.vo.PostVo.Builder;

public class CategoryVo {
	private Integer no;
	private String name;
	private String description;
	private String user_id;
	private	Integer postnum;
	
	public CategoryVo() {
		// TODO Auto-generated constructor stub
	}
	
	public Integer getPostnum() {
		return postnum;
	}

	public void setPostnum(Integer postnum) {
		this.postnum = postnum;
	}

	public Integer getNo() {
		return no;
	}
	public void setNo(Integer no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	@Override
	public String toString() {
		
		return "[CategoryVo] [ [no] : "+no+", [name] : "+name+" [description] : "+description+" [postnum] : "+postnum+" [user_id] "+user_id+" ]";
	}
	
	
	private CategoryVo(Builder builder) {
		this.no = builder.no;
		this.name=builder.name;
		this.description = builder.description;
		this.user_id =builder.user_id;
		this.postnum=builder.postnum;
    }

  
	public static Builder builder(){
        return new Builder();
    } 

	public static class Builder{
   
		private Integer no;
		private String name;
		private String description;
		private String user_id;
		private	Integer postnum;
        public Builder(){
          
        }
        
     
        
        public Builder no(Integer no){
            this.no = no;
            return this;
        }
        public Builder postnum(Integer postnum){
            this.postnum = postnum;
            return this;
        }
        public Builder name(String name){
            this.name = name;
            return this;
        }
        public Builder description(String description){
            this.description = description;
            return this;
        }
        public Builder user_id(String user_id){
            this.user_id = user_id;
            return this;
        }
     
      
 
        
        public CategoryVo build(){
            return new CategoryVo(this);
        }
    }
	
}
