package com.spring.boot.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.application.dao.entity.MyBook;
import com.spring.boot.application.service.BookService;
import com.spring.boot.application.service.IBookService;
import com.spring.boot.application.service.model.BookContext;

// postman icin  
@RestController
@RequestMapping("/application")
public class BooksController {

	@Autowired
	private IBookService bookService;

	// /book/list
	@RequestMapping(value = "/books/list", method = RequestMethod.GET)
	// book listesi getiriyor
	public List<MyBook> getAllMyBook() {
		// service
		return bookService.getAllMyBook();
	}

	// get book by id
	@RequestMapping(value = "/book/{bookId}", method = RequestMethod.GET)
	// bir tane book nesnesi donduruyor parametre bookid aliyor
	public MyBook findBookById(@PathVariable("bookId") Long bookId) {// pathvariables
		return bookService.findBookById(bookId);
	}

	@RequestMapping(value = "/book/save", method = RequestMethod.POST)
	public void save(@RequestBody BookContext bookContext) {// MyBook
		bookService.save(bookContext);
	}

}
