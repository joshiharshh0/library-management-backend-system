package com.library.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "books")
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "title")
	private String title;

	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinTable(name = "books_authors", joinColumns = @JoinColumn(name = "books_id"), inverseJoinColumns = @JoinColumn(name = "authors_id"))
	private List<Author> authorss;

	@Column(name = "publisher")
	private String publisher;

	@Column(name = "publsihed_date")
	private String publishedDate;

	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinTable(name = "books_bookcategory", joinColumns = @JoinColumn(name = "books_id"), inverseJoinColumns = @JoinColumn(name = "bookcategory_id"))
	private List<BookCategory> categories;

	@Column(name = "description")
	private String description;

	@Column(name = "stock")
	private int stock;

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public ReserveBook getReserved() {
		return reserved;
	}

	public void setReserved(ReserveBook reserved) {
		this.reserved = reserved;
	}

	@OneToOne(mappedBy = "book", fetch = FetchType.LAZY)
	@JsonIgnore
	private ReserveBook reserved;

	@OneToOne(mappedBy = "book", fetch = FetchType.LAZY)
	@JsonIgnore
	private BorrowBook borrow;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Author> getAuthorss() {
		return authorss;
	}

	public void setAuthorss(List<Author> authorss) {
		this.authorss = authorss;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(String publishedDate) {
		this.publishedDate = publishedDate;
	}

	public List<BookCategory> getCategories() {
		return categories;
	}

	public void setCategories(List<BookCategory> categories) {
		this.categories = categories;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BorrowBook getBorrow() {
		return borrow;
	}

	public void setBorrow(BorrowBook borrow) {
		this.borrow = borrow;
	}

	public void borrowedOne() {
		this.stock--;
	}

	public void returnedOne() {
		this.stock++;
	}

	public Book(long id, String title, List<Author> authorss, String publisher, String publishedDate,
			List<BookCategory> categories, String description, BorrowBook borrow) {
		super();
		this.id = id;
		this.title = title;
		this.authorss = authorss;
		this.publisher = publisher;
		this.publishedDate = publishedDate;
		this.categories = categories;
		this.description = description;
		this.borrow = borrow;

	}

	public Book(int stock, ReserveBook reserved) {
		super();
		this.stock = stock;
		this.reserved = reserved;
	}

	public Book() {
		super();
	}

}
