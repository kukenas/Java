package com.dematic.model;

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

	BookType(String type) {
		this.type = type;
	}

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
