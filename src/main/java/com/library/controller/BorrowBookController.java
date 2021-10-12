package com.library.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.dto.BorrowBookDTO;
import com.library.model.BorrowBook;
import com.library.service.BorrowBookService;

@RestController
@RequestMapping("library-spring/api/v2")
public class BorrowBookController {
	@Autowired
	private BorrowBookService borrowService;

	@GetMapping("/borrowed")
	public List<BorrowBook> getAllBorrowBooks() {
		return borrowService.getAllBorrowBooks();
	}

	@PostMapping("/borrowed")
	public String addBorrowBook(@RequestBody BorrowBookDTO borrow) {
		return this.borrowService.addBorrowBook(borrow);
	}

	@GetMapping("/borrowed/{id}")
	public ResponseEntity<BorrowBook> getBorrowedBookById(@PathVariable long id) {
		return this.borrowService.getBorrowedBookById(id);
	}

	@GetMapping(value = "/borrowed/users/{userId}")
	public ResponseEntity<BorrowBook> getAllBorrowedBooksForUser(@PathVariable long userId) {
		return this.borrowService.getAllBorrowedBooksForUser(userId);
	}

	@GetMapping("/borrowed/books/{bookId}")
	public ResponseEntity<Optional<BorrowBook>> getBorrowedForBook(@PathVariable long bookId) {
		return this.borrowService.getBorrowedForBook(bookId);
	}

}
