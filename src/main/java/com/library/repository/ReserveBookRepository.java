package com.library.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.model.ReserveBook;

public interface ReserveBookRepository extends JpaRepository<ReserveBook, Long> {
	Optional<ReserveBook> findByBookId(Long bookId);

	Optional<ReserveBook> findByUserId(Long userId);

}
