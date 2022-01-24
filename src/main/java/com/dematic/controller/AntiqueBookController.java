package com.dematic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dematic.model.AntiqueBook;
import com.dematic.model.Book;
import com.dematic.service.BookService;

import Exceptions.AntiqueBookNotFoundException;

/**
 * Separate Controller to handle AntiqueBook specific requests
 * 
 * @author Aurimas
 *
 */
@RestController
@RequestMapping("/antiquebooks")
public class AntiqueBookController {

	@Autowired
	private BookService bookService;

	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<AntiqueBook>> getAntiqueBooks() {
		return ResponseEntity.ok(bookService.getAntiqueBooks());
	}

	@RequestMapping(params = "barcode", method = RequestMethod.GET)
	ResponseEntity<Book> getByBarcode(@RequestParam(value = "barcode") long barcode) {
		return ResponseEntity.ok(
				bookService.getAntiqueByBarcode(barcode).orElseThrow(() -> new AntiqueBookNotFoundException(barcode)));

	}

	@RequestMapping(params = "release", method = RequestMethod.GET)
	ResponseEntity<Book> getByRelease(@RequestParam(value = "release") int release) {
		return ResponseEntity.ok(
				bookService.getAntiqueByRelease(release).orElseThrow(() -> new AntiqueBookNotFoundException(release)));

	}

}
