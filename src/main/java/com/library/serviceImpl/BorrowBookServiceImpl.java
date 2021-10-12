package com.library.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.library.dto.BorrowBookDTO;
import com.library.exception.BookNotFoundException;
import com.library.exception.BorrowBookNotFoundException;
import com.library.exception.UserNotFoundException;
import com.library.model.Book;
import com.library.model.BorrowBook;
import com.library.model.User;
import com.library.repository.BookRepository;
import com.library.repository.BorrowBookRepository;
import com.library.repository.UserRepository;
import com.library.service.BorrowBookService;

@Service
public class BorrowBookServiceImpl implements BorrowBookService {
	@Autowired
	private BorrowBookRepository borrowBookRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BookRepository bookRepository;

	private static final Logger LOG = Logger.getLogger(BorrowBookServiceImpl.class);

	@Override
	public List<BorrowBook> getAllBorrowBooks() {
		// TODO Auto-generated method stub
		return this.borrowBookRepository.findAll();
	}

	@Override
	public String addBorrowBook(BorrowBookDTO borrow) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(borrow.getUserId()).orElseThrow(() -> {
			return new UserNotFoundException(borrow.getUserId());
		});
		Book book = bookRepository.findById(borrow.getBookId()).orElseThrow(() -> {
			return new BookNotFoundException(borrow.getBookId());
		});
		BorrowBook borrowBook = new BorrowBook(book, user, borrow.getDueDate());

		if (book.getStock() < 1) {
			return "Your requested book \" " + book.getTitle() + "\" is out of stock!";
		}
		book.borrowedOne();
		bookRepository.save(book);

		borrowBookRepository.save(borrowBook);
		return user.getUsername() + " has borrowed one copy of \" " + book.getId() + "\"!";
	}

	@Override
	public ResponseEntity<BorrowBook> getBorrowedBookById(long id) {
		// TODO Auto-generated method stub
		BorrowBook borrow = borrowBookRepository.findById(id).orElseThrow(() -> {
			LOG.info("Exception Message.....");
			LOG.error("BorrowBookNotFoundException occurs....");
			return new BorrowBookNotFoundException(id);
		});
		return ResponseEntity.ok().body(borrow);
	}

	@Override
	public ResponseEntity<Optional<BorrowBook>> getBorrowedForBook(long bookId) {
		// TODO Auto-generated method stub
		Optional.ofNullable(borrowBookRepository.findByBookId(bookId))
				.orElseThrow(() -> new BorrowBookNotFoundException("Book with an Id: " + bookId + " is not borrowed."));

		Optional<BorrowBook> borrow = borrowBookRepository.findByBookId(bookId);

		return ResponseEntity.ok().body(borrow);
	}

	@Override
	public ResponseEntity<BorrowBook> getAllBorrowedBooksForUser(long userId) {
		// TODO Auto-generated method stub
		BorrowBook borrow = borrowBookRepository.findByUserId(userId)
				.orElseThrow(() -> new BorrowBookNotFoundException(userId));

		return ResponseEntity.ok().body(borrow);
	}

}
