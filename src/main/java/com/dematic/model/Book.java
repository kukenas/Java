package com.dematic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Main Book Class
 * @author Aurimas
 *
 */
@Entity(name = "book")
public class Book {

	@Id
	@Column(name = "barcode", nullable = false)
	private long barcode; // required
	@Column(name = "name")
	private String name; // optional
	@Column(name = "author")
	private String author; // optional
	@Column(name = "quantity")
	private int quantity; // optional
	@Column(name = "price")
	private double price; // optional
	
	// Default Constructor, required by Spring
	public Book () {
		
	}
	
	// Immutable
	Book(Builder builder) {
		this.barcode = builder.barcode;
		this.name = builder.name;
		this.author = builder.author;
		this.quantity = builder.quantity;
		this.price = builder.price;
	}

	/**
	 * Inner Builder Class
	 * @author Aurimas
	 *
	 */
	public static class Builder {

		private long barcode;
		private String name;
		private String author;
		private int quantity;
		private double price;

		public Builder(long barcode) {
			this.barcode = barcode;
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder author(String author) {
			this.author = author;
			return this;
		}

		public Builder quantity(int quantity) {
			this.quantity = quantity;
			return this;
		}

		public Builder price(double price) {
			this.price = price;
			return this;
		}

		// Return constructed Book
		public Book build() {
			Book book = new Book(this);
			validateBook(book);
			return book;
		}

		private void validateBook(Book book) {
			// Validate Book, if necessary
		}

	}

	public long getBarcode() {
		return barcode;
	}

	public String getName() {
		return name;
	}

	public String getAuthor() {
		return author;
	}

	public int getQuantity() {
		return quantity;
	}

	public double getPrice() {
		return price;
	}

	@Override
	public String toString() {
		return "Book{" + "barcode=" + this.barcode + ", name=" + this.name + ", author=" + this.author + ", quantity="
				+ this.quantity + ", price=" + this.price + "}";
	}

}
