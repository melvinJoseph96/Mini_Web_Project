package uk.ac.le.cs.CO3098.spring.domain;

public class JSONResponse {
	
	Object result;
	String messege;
	
	public JSONResponse(Object result, String messege) {
		super();
		this.result = result;
		this.messege = messege;
	}
	public JSONResponse(Object result) {
		this.result = result;
		
	}
	
	
	public JSONResponse() {
		super();
	}
	public Object getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getMessege() {
		return messege;
	}
	public void setMessege(String messege) {
		this.messege = messege;
	}
	
	

}
