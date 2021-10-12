package com.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.dto.ReserveBookDTO;
import com.library.model.ReserveBook;
import com.library.service.ReserveBookService;

@RestController
@RequestMapping("library-spring/api/v2")
public class ReserveBookController {
	@Autowired
	private ReserveBookService reserveService;

	@GetMapping("/reserve")
	public List<ReserveBook> getAllReservedBooks() {
		return this.reserveService.getAllReservedBooks();
	}

	@PostMapping("/reserve")
	public String addReserveBook(@RequestBody ReserveBookDTO reserve) {
		return this.reserveService.addReserveBook(reserve);
	}

	@GetMapping("/reserve/{id}")
	public ResponseEntity<ReserveBook> getReservedBookById(@PathVariable Long id) {
		return this.reserveService.getReservedBookById(id);
	}
}
