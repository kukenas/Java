package com.dematic.service;

import java.util.List;
import java.util.Optional;

import com.dematic.model.AntiqueBook;
import com.dematic.model.Book;
import com.dematic.model.ScienceJournal;

public interface BookService {

	Book saveBook(Book book);

	double calculateTotalPrice(long barcode);

	Optional<Book> getBookByBarcode(long id);

	Optional<AntiqueBook> getAntiqueByBarcode(long id);

	List<Book> getBooks();

	List<AntiqueBook> getAntiqueBooks();

	List<ScienceJournal> getScienceJournals();

	Optional<AntiqueBook> findByBarcodeAntique(long barcode);

	Optional<ScienceJournal> findByBarcodeScience(long barcode);
}
