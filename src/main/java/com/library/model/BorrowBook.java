package com.library.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "borrowed")
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class BorrowBook {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "books_id")
	private Book book;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "users_id")
	private User user;

	@Column(name = "due_date")
	private Date dueDate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public BorrowBook() {
		super();
	}

	public BorrowBook(Book book, User user, Date dueDate) {
		super();
		this.book = book;
		this.user = user;
		this.dueDate = dueDate;
	}
}