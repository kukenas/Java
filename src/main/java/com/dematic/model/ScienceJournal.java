package com.dematic.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.dematic.validators.IndexValue;

/**
 * ScienceJournal with Index Field
 * @author Aurimas
 *
 */
@Entity(name = "scienceJournal")
public class ScienceJournal extends Book {

	@Column(name = "index")
	@IndexValue(min = 1, max = 10)
	private int index;
	
	// Default Constructor, required by Spring
	public ScienceJournal() {
	}
	
	protected ScienceJournal(Builder builder) {
		super(builder);
		this.index = builder.index;
	}

	/**
	 * Inner Builder Class extending Book Builder
	 * @author Aurimas
	 *
	 */
	public static class Builder extends Book.Builder {

		private int index;

		public Builder(long barcode) {
			super(barcode);
		}

		public Builder index(int index) {
			this.index = index;
			return this;
		}

		public ScienceJournal build() {
			ScienceJournal scienceJournal = new ScienceJournal(this);
			validateScienceJournal(scienceJournal);
			return scienceJournal;
		}

		private void validateScienceJournal(ScienceJournal scienceJournal) {
			// Validate scienceJournal, if needed
		}

	}

	public int getIndex() {
		return index;
	}

	@Override
	public String toString() {
		return "ScienceJournal{" + "barcode=" + this.getBarcode() + ", name=" + this.getName() + ", author="
				+ this.getAuthor() + ", quantity=" + this.getQuantity() + ", price=" + this.getPrice() + ", index="
				+ this.index + "}";
	}
}
