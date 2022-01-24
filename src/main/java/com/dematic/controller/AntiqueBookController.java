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

import com.dematic.exceptions.AntiqueBookNotFoundException;
import com.dematic.model.AntiqueBook;
import com.dematic.model.Book;
import com.dematic.service.BookService;
import com.dematic.utils.JsonUtil;

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

	@RequestMapping(method = RequestMethod.POST)
	ResponseEntity<Book> createAntiqueBook(@RequestParam(value = "name") String name,
			@RequestParam(value = "author") String author, @RequestParam(value = "barcode") long barcode,
			@RequestParam(value = "quantity") int quantity, @RequestParam(value = "price") double price,
			@RequestParam(value = "release") int release) {
		return ResponseEntity.ok(bookService.saveBook(new AntiqueBook.Builder(barcode).release(release).name(name)
				.author(author).quantity(quantity).price(price).build()));
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	@ResponseBody
	ResponseEntity<Book> updateAntiqueBook(@RequestParam(value = "barcode") long barcode, @RequestBody String params) {
		Book thisBook = bookService.getAntiqueByBarcode(barcode)
				.orElseThrow(() -> new AntiqueBookNotFoundException(barcode));
		return ResponseEntity
				.ok(bookService.saveBook(JsonUtil.getCopyOfAntiqueBook(barcode, params, (AntiqueBook) thisBook)));
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
