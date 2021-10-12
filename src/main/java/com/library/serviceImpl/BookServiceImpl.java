package com.library.serviceImpl;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.library.exception.BookNotFoundException;
import com.library.model.Book;
import com.library.repository.BookRepository;
import com.library.service.BookService;

@Service
public class BookServiceImpl implements BookService {
	@Autowired
	private BookRepository bookRepository;

	private static final Logger LOG = Logger.getLogger(BookServiceImpl.class);

	@Override
	public List<Book> getAllBooks() {
		// TODO Auto-generated method stub
		return bookRepository.findAll();
	}

	@Override
	public List<Book> getAllBooks1(int offset, int offsetSize) {
		// TODO Auto-generated method stub
		Pageable pageable = PageRequest.of(offset, offsetSize);
		Page<Book> list = bookRepository.findAll(pageable);
		return list.toList();
	}

	@Override
	public Book addBook(Book book) {
		// TODO Auto-generated method stub
		bookRepository.save(book);
		return book;
	}

	@Override
	public ResponseEntity<HttpStatus> deleteBook(long id) {
		// TODO Auto-generated method stub

		@SuppressWarnings("unused")
		Book book = bookRepository.findById(id).orElseThrow(() -> {
			LOG.info("Exception Message.....");
			LOG.error("BookNotFoundException occurs....");
			return new BookNotFoundException(id);
		});

		bookRepository.deleteById(id);

		return new ResponseEntity<>(HttpStatus.OK);

	}

	@Override
	public ResponseEntity<Book> getBookById(long id) {
		// TODO Auto-generated method stub
		Book book = bookRepository.findById(id).orElseThrow(() -> {
			LOG.info("Exception Message.....");
			LOG.error("BookNotFoundException occurs....");
			return new BookNotFoundException(id);
		});
		return ResponseEntity.ok().body(book);
	}

}
