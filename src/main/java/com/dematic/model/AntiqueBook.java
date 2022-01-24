package com.dematic.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.dematic.validators.Year;

/**
 * AntiqueBook with Release Date Field
 * @author Aurimas
 *
 */
@Entity(name = "antiqueBook")
public class AntiqueBook extends Book {

	@Column(name = "release")
	@Year(max = 1900)
	private int release; // required
	
	// Default Constructor, required by Spring
	public AntiqueBook() {		
	}
	
	AntiqueBook(Builder builder) {
		super(builder);
		this.release = builder.release;
	}
	
	/**
	 * Inner Builder Class extending Book Builder
	 * @author Aurimas
	 *
	 */
	public static class Builder extends Book.Builder {

		private int release;

		public Builder(long barcode) {
			super(barcode);
		}

		public Builder release(int release) {
			this.release = release;
			return this;
		}

		public AntiqueBook build() {
			AntiqueBook antiqueBook = new AntiqueBook(this);
			validateAntiqueBook(antiqueBook);
			return antiqueBook;
		}

		private void validateAntiqueBook(AntiqueBook antiqueBook) {
			// Validate AntiqueBook
		}
	}

	public int getRelease() {
		return release;
	}

	@Override
	public String toString() {
		return "AntiqueBook{" + "barcode=" + this.getBarcode() + ", name=" + this.getName() + ", author="
				+ this.getAuthor() + ", quantity=" + this.getQuantity() + ", price=" + this.getPrice()
				+ ", releaseYear=" + this.release + "}";
	}
}
