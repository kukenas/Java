package com.dematic.service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dematic.model.AntiqueBook;
import com.dematic.model.Book;
import com.dematic.model.BookType;
import com.dematic.model.ScienceJournal;
import com.dematic.persistence.BookRepository;

@Service
public class BookServiceImpl implements BookService {

	// Repository used to store
	private final BookRepository repository;

	BookServiceImpl(BookRepository repository) {
		this.repository = repository;
	}

	/**
	 * Saves a given Book entity in H2 embedded database
	 * 
	 * @param Book
	 * @return the instance of Book for further operations
	 */
	@Override
	public Book saveBook(Book book) {
		return repository.save(book);
	}

	/**
	 * Finds all entities by a given barcode
	 * 
	 * @param barcode
	 * @return the instance of Book for further operations
	 */
	public Optional<Book> findByBarcode(long barcode) {
		return repository.findByBarcode(barcode);
	}

	@Override
	public List<Book> getBooks() {
		return repository.allBooks();
	}

	@Override
	public List<AntiqueBook> getAntiqueBooks() {
		return repository.findAntiqueBooks();
	}

	@Override
	public double calculateTotalPrice(long barcode) {
		switch (BookType.getEnum(repository.findByBarcode(barcode).get().getClass().getSimpleName())) {
		case BOOK:
			Book book = repository.findByBarcode(barcode).get();
			return book.getQuantity() * (double) book.getPrice();
		case ANTIQUE_BOOK:
			AntiqueBook bookAntique = repository.findByBarcodeAntique(barcode).get();
			return bookAntique.getQuantity() * bookAntique.getPrice()
					* ((Calendar.getInstance().get(Calendar.YEAR) - bookAntique.getRelease()) / 10);
		case SCIENCE_JOURNAL:
			ScienceJournal scienceJournal = repository.findByBarcodeScience(barcode).get();
			return scienceJournal.getQuantity() * scienceJournal.getPrice() * scienceJournal.getIndex();
		}
		return 0.0D;
	}

	@Override
	public Optional<AntiqueBook> findByBarcodeAntique(long barcode) {
		return repository.findByBarcodeAntique(barcode);
	}

	@Override
	public List<ScienceJournal> getScienceJournals() {
		return repository.findScienceJournals();
	}

	@Override
	public Optional<ScienceJournal> findByBarcodeScience(long barcode) {
		return repository.findByBarcodeScience(barcode);
	}

}
