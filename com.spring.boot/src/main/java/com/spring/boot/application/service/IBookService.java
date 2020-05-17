package com.spring.boot.application.service;

import java.util.List;

import com.spring.boot.application.dao.entity.MyBook;
import com.spring.boot.application.service.model.BookContext;

public interface IBookService {

	public List<MyBook> getAllMyBook();

	public MyBook findBookById(Long bookId);

	public Long save(BookContext bookContext);

	public List<MyBook> findAllByOrderByBookNameAsc();

	public void delete(Long bookId);

	public List<MyBook> searchBy(String theName);
}
