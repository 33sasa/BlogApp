package model;

import java.util.Date;

public class ArticleDTO {

	private int id;
	private String title;
	private String body;
	private String user_name;
	private Date date;
	
	public ArticleDTO(){}

	public ArticleDTO(int id, String title, String body, String user_name, Date date) {
		this.id = id;
		this.title = title;
		this.body = body;
		this.user_name = user_name;
		this.date = date;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getBody() {
		return body;
	}
	
	public void setBody(String body) {
		this.body = body;
	}
	
	public String getUser_name() {
		return user_name;
	}
	
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
}
