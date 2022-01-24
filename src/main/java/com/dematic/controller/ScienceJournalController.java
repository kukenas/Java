package com.dematic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dematic.model.Book;
import com.dematic.model.ScienceJournal;
import com.dematic.service.BookService;

import Exceptions.ScienceJournalNotFoundException;

/**
 * Separate Controller to handle ScienceJournal specific requests
 * 
 * @author Aurimas
 *
 */
@RestController
@RequestMapping("/sciencejournals")
public class ScienceJournalController {

	@Autowired
	private BookService bookService;

	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<ScienceJournal>> getScienceJournals() {
		return ResponseEntity.ok(bookService.getScienceJournals());
	}

	@RequestMapping(params = "barcode", method = RequestMethod.GET)
	ResponseEntity<Book> getScienceByBarcode(@RequestParam(value = "barcode") long barcode) {
		return ResponseEntity.ok(bookService.getScienceByBarcode(barcode)
				.orElseThrow(() -> new ScienceJournalNotFoundException(barcode)));

	}

	@RequestMapping(params = "index", method = RequestMethod.GET)
	ResponseEntity<Book> getScienceByIndex(@RequestParam(value = "index") int index) {
		return ResponseEntity
				.ok(bookService.getScienceByIndex(index).orElseThrow(() -> new ScienceJournalNotFoundException(index)));

	}

}
