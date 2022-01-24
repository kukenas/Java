package com.dematic.model;

/**
 * Enum to define different type of Books
 * @author Aurimas
 *
 */
public enum BookType {

	BOOK("Book") {
		@Override
		public String toString() {
			return "Book";
		}
	},

	ANTIQUE_BOOK("AntiqueBook") {
		@Override
		public String toString() {
			return "AntiqueBook";
		}
	},

	SCIENCE_JOURNAL("ScienceJournal") {
		@Override
		public String toString() {
			return "ScienceJournal";
		}
	};

	private String type;

	/**
	 * Set BookType from String
	 * @param type
	 */
	BookType(String type) {
		this.type = type;
	}

	/**
	 * 
	 * @return BookType as String
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * Use this method as it is not allowed to override valueOf
	 * @param type
	 * @return BookType
	 */
	public static BookType getEnum(String type) {
		for (BookType t : values())
			if (t.getType().equalsIgnoreCase(type))
				return t;
		throw new IllegalArgumentException();
	}

}
