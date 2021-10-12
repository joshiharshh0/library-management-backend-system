package com.library.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.model.BorrowBook;

public interface BorrowBookRepository extends JpaRepository<BorrowBook, Long> {

	Optional<BorrowBook> findByBookId(Long bookId);

	Optional<BorrowBook> findByUserId(Long userId);

}
