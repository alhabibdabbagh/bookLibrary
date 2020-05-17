package com.spring.boot.test.mock.service;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.spring.boot.application.dao.entity.MyBook;
import com.spring.boot.application.dao.jpa.repository.BookRepository;
import com.spring.boot.application.service.BookService;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceMT {

	@Mock
	private BookRepository bookRepository;

	@InjectMocks
	private BookService bookService;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	// service katman test olarak kontrol et
	@Test
	public void queryAllBooks() {

		MyBook myBook = new MyBook();
		myBook.setAuthorName("habib dabbag");
		myBook.setBookId(1L);
		myBook.setBookName("Java 101");
		myBook.setCategory("education");
		myBook.setPrice(50L);

		MyBook myBook1 = new MyBook();
		myBook1.setAuthorName("mehmet yildiz");
		myBook1.setBookId(2L);
		myBook1.setBookName("C# 101");
		myBook1.setCategory("education");
		myBook1.setPrice(30L);

		List<MyBook> bookList = Arrays.asList(myBook1, myBook);// kutuphane util Arrays

		Mockito // veritabandan almis gibi listeye koy ve dikkat arryas.aslist kutuphanesi
				.when(bookRepository.getAllMyBook())
				// bu dondur
				.thenReturn(bookList);

		List<MyBook> books = bookService.getAllMyBook();

		// kontroller yapiyoruz
		Assert.assertNotNull(books);// kutuphanelerde dikkat
		Assert.assertTrue(books.size() > 0);
	}

	// id ile getirme test ile ediyoruz
	@Test
	public void queryById() {

		MyBook myBook1 = new MyBook();
		myBook1.setAuthorName("fatma yildiz");
		myBook1.setBookId(1L);
		myBook1.setBookName("C 102");
		myBook1.setCategory("education");
		myBook1.setPrice(60L);

		Mockito.when(bookRepository.findBookById(1L))
				// bunu dondur
				.thenReturn(myBook1);

		MyBook myBook = bookService.findBookById(1L);// bu asagada olmali yoksa hata verir

		Assert.assertNotNull(myBook);
		Assert.assertTrue(myBook.getBookId() > 0);// Equels kullandigimiz zaman her ikisi kesin olarak long tipinde
													// olmali

	}

}
