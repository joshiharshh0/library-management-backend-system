package com.library.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.library.dto.ReserveBookDTO;
import com.library.model.ReserveBook;

public interface ReserveBookService {
	public List<ReserveBook> getAllReservedBooks();

	public ResponseEntity<ReserveBook> getReservedBookById(long id);

	String addReserveBook(ReserveBookDTO reserve);
}
