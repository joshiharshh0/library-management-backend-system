package com.library.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.library.dto.BorrowBookDTO;
import com.library.model.BorrowBook;

public interface BorrowBookService {
	public List<BorrowBook> getAllBorrowBooks();

	public ResponseEntity<BorrowBook> getBorrowedBookById(long id);

	public ResponseEntity<Optional<BorrowBook>> getBorrowedForBook(long bookId);

	public ResponseEntity<BorrowBook> getAllBorrowedBooksForUser(long userId);

	String addBorrowBook(BorrowBookDTO borrow);
}
