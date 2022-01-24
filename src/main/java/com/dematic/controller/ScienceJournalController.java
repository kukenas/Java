package com.dematic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dematic.exceptions.ScienceJournalNotFoundException;
import com.dematic.model.Book;
import com.dematic.model.ScienceJournal;
import com.dematic.service.BookService;
import com.dematic.utils.JsonUtil;

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

	@RequestMapping(method = RequestMethod.POST)
	ResponseEntity<Book> createAntiqueBook(@RequestParam(value = "name") String name,
			@RequestParam(value = "author") String author, @RequestParam(value = "barcode") long barcode,
			@RequestParam(value = "quantity") int quantity, @RequestParam(value = "price") double price,
			@RequestParam(value = "index") int index) {
		return ResponseEntity.ok(bookService.saveBook(new ScienceJournal.Builder(barcode).index(index).name(name)
				.author(author).quantity(quantity).price(price).build()));
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	@ResponseBody
	ResponseEntity<Book> updateAntiqueBook(@RequestParam(value = "barcode") long barcode, @RequestBody String params) {
		ScienceJournal thisBook = bookService.getScienceByBarcode(barcode)
				.orElseThrow(() -> new ScienceJournalNotFoundException(barcode));
		return ResponseEntity.ok(bookService.saveBook(JsonUtil.getCopyOfScienceJournal(barcode, params, thisBook)));
	}

	@RequestMapping(params = "barcode", method = RequestMethod.GET)
	ResponseEntity<ScienceJournal> getScienceByBarcode(@RequestParam(value = "barcode") long barcode) {
		return ResponseEntity.ok(bookService.getScienceByBarcode(barcode)
				.orElseThrow(() -> new ScienceJournalNotFoundException(barcode)));

	}

	@RequestMapping(params = "index", method = RequestMethod.GET)
	ResponseEntity<ScienceJournal> getScienceByIndex(@RequestParam(value = "index") int index) {
		return ResponseEntity
				.ok(bookService.getScienceByIndex(index).orElseThrow(() -> new ScienceJournalNotFoundException(index)));

	}

}
