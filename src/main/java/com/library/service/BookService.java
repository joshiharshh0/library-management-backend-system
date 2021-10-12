package com.library.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.library.model.Book;

public interface BookService {
	public List<Book> getAllBooks();

	public Book addBook(Book book);

	public ResponseEntity<Book> getBookById(long id);

	public ResponseEntity<HttpStatus> deleteBook(long id);

	public List<Book> getAllBooks1(int offset, int offsetSize);
}
