package com.poscoict.jblog.vo;

import com.poscoict.jblog.vo.UserVo.Builder;

public class PostVo {
	private Integer no;
	private String title;
	private String contents;
	private String reg_date;
	private Integer category_no;
	
	public PostVo() {
		
	}
	
	public Integer getNo() {
		return no;
	}
	public void setNo(Integer no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public Integer getCategory_no() {
		return category_no;
	}
	public void setCategory_no(Integer category_no) {
		this.category_no = category_no;
	}
	
	
	private PostVo(Builder builder) {
		this.no = builder.no;
		this.title=builder.title;
		this.contents = builder.contents;
		this.reg_date =builder.reg_date;
		this.category_no = builder.category_no;
    }

  
	public static Builder builder(){
        return new Builder();
    } 

	public static class Builder{
   
		private Integer no;
		private String title;
		private String contents;
		private String reg_date;
		private Integer category_no;
	    
        public Builder(){
          
        }
        
     
        
        public Builder no(Integer no){
            this.no = no;
            return this;
        }
        public Builder category_no(Integer category_no){
            this.category_no = category_no;
            return this;
        }
        public Builder title(String title){
            this.title = title;
            return this;
        }
        public Builder contents(String contents){
            this.contents = contents;
            return this;
        }
        public Builder reg_date(String reg_date){
            this.reg_date = reg_date;
            return this;
        }
   
      
 
        
        public PostVo build(){
            return new PostVo(this);
        }
    }
	

}
