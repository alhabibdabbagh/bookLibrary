package com.spring.boot.application.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.boot.annotation.MethodRunningTime;
import com.spring.boot.application.dao.entity.MyBook;
import com.spring.boot.application.dao.jpa.repository.BookRepository;
import com.spring.boot.application.service.model.BookContext;

@Service
public class BookService implements IBookService {

	@Autowired
	private BookRepository bookRepository;

	// butun kayitlari getiriyor
	@MethodRunningTime(timeCalculation = true)
	public List<MyBook> getAllMyBook() {

		return bookRepository.getAllMyBook();

	}

	@MethodRunningTime(timeCalculation = false) // zaman olcmeyi icin
	public MyBook findBookById(Long bookId) {
		return bookRepository.findBookById(bookId);
	}

	@MethodRunningTime(timeCalculation = true)
	// Transactional eger bu fonksyonda herhangi bir islem yanlis oldugunda eski
	// durumda kal
	@Transactional
	public Long save(BookContext bookContext) {// MyBook

		Long maxId = bookRepository.findMaxBookId() + 1;

		MyBook myBook = new MyBook();
		myBook.setBookId(maxId);
		myBook.setBookName(bookContext.getBookName());
		myBook.setAuthorName(bookContext.getAuthorName());
		myBook.setCategory(bookContext.getCategory());
		myBook.setPrice(bookContext.getPrice());
//		bookContext.setAuthorName("serdar");
//		bookContext.setBookName("dunya");
//		bookContext.setCategory("belgesel");
//		bookContext.setPrice(15L);
//		MyBook myBook=new MyBook();
//		myBook.setBookId(maxId);
//		myBook.setBookName(bookContext.getBookName());
//		myBook.setAuthorName(bookContext.getAuthorName());
//		myBook.setCategory(bookContext.getCategory());
//		myBook.setPrice(bookContext.getPrice());
		myBook = bookRepository.save(myBook);

		bookRepository.save(myBook);
		// RollBack calisiyor
//		if(myBook.getBookId() > 0) {
//			throw new RuntimeException("Book ERROR FOR ROLLBACK!");
//		
		return myBook.getBookId();

	}

//	public void save(MyBook myBook) {
//		bookRepository.save(myBook);
//	}
	public List<MyBook> findAllByOrderByBookNameAsc() {
		return bookRepository.findAllByOrderByBookNameAsc();
	}

	public void delete(Long bookId) {
		bookRepository.deleteById(bookId);

	}

	// search icin parametre bookName aliyor
	public List<MyBook> searchBy(String theName) {

		List<MyBook> results = null;
		// bookname varsa
		if (theName != null && (theName.trim().length() > 0)) {
			results = bookRepository.findByBookNameContainsAllIgnoreCase(theName);
		} else {// bookname null ise listeyi yazdir
			results = findAllByOrderByBookNameAsc();
		}

		return results;
	}

}
