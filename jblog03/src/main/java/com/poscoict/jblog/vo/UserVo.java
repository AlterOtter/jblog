package com.poscoict.jblog.vo;

public class UserVo {
	private String user_id;
	private String name;
	private String password;
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		
		return "[UserVo] [ [user_id] : "+user_id+", [name] : "+name+" [password] : "+password+" ]";
	}

	public UserVo() {
		
	}
	
	private UserVo(Builder builder) {
        this.name=builder.name;
        this.password=builder.password;
        this.user_id=builder.user_id;
    }

  
	public static Builder builder(){
        return new Builder();
    } 

	public static class Builder{
   
    	String name;
    	String password;
    	String user_id;
	    
        public Builder(){
            this.name="없음";
            this.password="없음";
        }
        
     
        
        public Builder name(String name){
            this.name = name;
            return this;
        }
        public Builder password(String password){
            this.password = password;
            return this;
        }
        public Builder user_id(String user_id){
            this.user_id = user_id;
            return this;
        }
   
      
      
        
        public UserVo build(){
            return new UserVo(this);
        }
    }
	
	
}
