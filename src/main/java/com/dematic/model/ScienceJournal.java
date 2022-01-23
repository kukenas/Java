package com.dematic.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.dematic.validators.IndexValue;

@Entity(name = "scienceJournal")
public class ScienceJournal extends Book {

	@Column(name = "index")
	@IndexValue(min = 1, max = 10)
	private int index;

	public ScienceJournal() {
	}

	protected ScienceJournal(Builder builder) {
		super(builder);
		this.index = builder.index;
	}

	public static class Builder extends Book.Builder {

		private int index;

		public Builder(long barcode, int index) {
			super(barcode);
			this.index = index;
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
