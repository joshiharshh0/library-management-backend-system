package com.library.serviceImpl;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.library.dto.ReserveBookDTO;
import com.library.exception.BookNotFoundException;
import com.library.exception.ReserveBookNotFoundException;
import com.library.exception.UserNotFoundException;
import com.library.model.Book;
import com.library.model.ReserveBook;
import com.library.model.User;
import com.library.repository.BookRepository;
import com.library.repository.ReserveBookRepository;
import com.library.repository.UserRepository;
import com.library.service.ReserveBookService;

@Service
public class ReserveBookServiceImpl implements ReserveBookService {
	@Autowired
	private ReserveBookRepository reserveRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BookRepository bookRepository;

	private static final Logger LOG = Logger.getLogger(ReserveBookServiceImpl.class);

	@Override
	public List<ReserveBook> getAllReservedBooks() {
		// TODO Auto-generated method stub
		return this.reserveRepository.findAll();
	}

	@Override
	public String addReserveBook(ReserveBookDTO reserve) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(reserve.getUserId()).orElseThrow(() -> {
			return new UserNotFoundException(reserve.getUserId());
		});
		Book book = bookRepository.findById(reserve.getBookId()).orElseThrow(() -> {
			return new BookNotFoundException(reserve.getBookId());
		});

		ReserveBook reserveBook = new ReserveBook(book, user, reserve.getDueDate());
		if (book.getStock() < 1) {
			return "Your requested book \" " + book.getTitle() + "\" is out of stock!";
		}
		book.borrowedOne();
		bookRepository.save(book);

		reserveRepository.save(reserveBook);
		return user.getUsername() + " has borrowed one copy of \" " + book.getId() + "\"!";
	}

	@Override
	public ResponseEntity<ReserveBook> getReservedBookById(long id) {
		// TODO Auto-generated method stub
		ReserveBook reserve = reserveRepository.findById(id).orElseThrow(() -> {
			LOG.info("Exception Message.....");
			LOG.error("ReserveBookNotFoundException occurs....");
			return new ReserveBookNotFoundException(id);
		});
		return ResponseEntity.ok().body(reserve);
	}

}
