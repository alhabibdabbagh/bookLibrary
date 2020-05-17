package com.spring.boot.application.service.model;

import java.io.Serializable;

public class BookContext implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -506069991482464252L;
	
	
	private String bookName;
	private String category;
	private String authorName;
	private Long price;
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	
}
