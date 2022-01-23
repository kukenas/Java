package com.dematic.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.dematic.validators.Year;

@Entity(name = "antiqueBook")
public class AntiqueBook extends Book {

	@Column(name = "release")
	@Year(max = 1900)
	private int release; // required

	public AntiqueBook() {
	}

	AntiqueBook(Builder builder) {
		super(builder);
		this.release = builder.release;
	}

	public static class Builder extends Book.Builder {

		private int release;

		public Builder(long barcode, int release) {
			super(barcode);
			this.release = release;
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
