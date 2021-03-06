package com.dematic.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dematic.exceptions.BookNotFoundException;
import com.dematic.model.Book;
import com.dematic.service.BookService;
import com.dematic.utils.JsonUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

/**
 * Main Book RestController, handling defined points
 * 
 * @author Aurimas
 *
 */
@RestController
@RequestMapping("/books")
public class BookController {

	@Autowired
	private BookService bookService;

	@Operation(summary = "End Point to fetch all Books stored in H2 Database")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Retrieve all the Books stored in Database", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Book.class))),
			@ApiResponse(responseCode = "400", description = "Book cannot be found in H2 Database", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "404", description = "Bad Request", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "500", description = "Internal Error", content = @Content(mediaType = "application/json")) })
	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<Book>> getBooks() {
		List<Book> books = bookService.getBooks();
		if (books.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(books);
		}
		return ResponseEntity.ok(books);
	}

	@RequestMapping(params = "barcode", method = RequestMethod.GET)
	Book getByBarcode(@RequestParam(value = "barcode") long barcode) {
		return bookService.getBookByBarcode(barcode).orElseThrow(() -> new BookNotFoundException(barcode));
	}

	@RequestMapping(method = RequestMethod.POST)
	ResponseEntity<Book> createBook(@RequestParam(value = "name") String name,
			@RequestParam(value = "author") String author, @RequestParam(value = "barcode") long barcode,
			@RequestParam(value = "quantity") int quantity, @RequestParam(value = "price") double price) {
		return ResponseEntity.ok(bookService
				.saveBook(new Book.Builder(barcode).name(name).author(author).quantity(quantity).price(price).build()));
	}

	/*
	 * The method updates only the fields passed with the ResponseBody Due to
	 * privacy reasons fields are validated at JSONObject instead of copying all
	 * fields at once
	 */
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	@ResponseBody
	ResponseEntity<Book> updateBook(@RequestParam(value = "barcode") long barcode, @RequestBody String params)
			throws BookNotFoundException {
		Book thisBook = bookService.getBookByBarcode(barcode).orElseThrow(() -> new BookNotFoundException(barcode));
		return ResponseEntity.ok(bookService.saveBook(JsonUtil.getCopyOfBook(barcode, params, thisBook)));
	}

	/*
	 * Generic DELETE method applicable to all Books
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	@ResponseBody
	ResponseEntity<String> deleteBook(@RequestParam(value = "barcode") long barcode) throws BookNotFoundException {
		Book thisBook = bookService.getBookByBarcode(barcode).orElseThrow(() -> new BookNotFoundException(barcode));
		bookService.deleteBook(barcode);
		return ResponseEntity.ok("Book with Barcode " + thisBook.getBarcode() + " was deleted");
	}

	@RequestMapping(value = "/calculate", params = "barcode", method = RequestMethod.GET)
	ResponseEntity<String> calculateTotalPrice(@RequestParam(value = "barcode") long barcode) {
		return ResponseEntity
				.ok(new JSONObject().put("total_price", bookService.calculateTotalPrice(barcode)).toString());
	}

	@RequestMapping(value = "/barcodes", method = RequestMethod.GET)
	ResponseEntity<String> getBarcodesGrouped() {
		List<Book> books = bookService.getBooks();
		HashMap<Integer, JSONArray> barcodes = new HashMap<Integer, JSONArray>();
		for (Book b : books) {
			if (!barcodes.containsKey(b.getQuantity())) {
				barcodes.put(b.getQuantity(), new JSONArray());
			}
			barcodes.get(b.getQuantity()).put(b.getBarcode());
		}
		return ResponseEntity.ok(new JSONObject(barcodes).toString());
	}

	@RequestMapping(value = "/barcodes/sort", method = RequestMethod.GET)
	ResponseEntity<String> getGroupedAndSort() {
		List<Book> books = bookService.getBooks();
		Collections.sort(books, new Comparator<Book>() {
			@Override
			public int compare(Book book1, Book book2) {

				double totalPrice1 = bookService.calculateTotalPrice(book1.getBarcode());
				double totalPrice2 = bookService.calculateTotalPrice(book2.getBarcode());

				return Double.valueOf(totalPrice1).compareTo(Double.valueOf(totalPrice2));
			}
		});
		HashMap<Integer, JSONArray> barcodes = new HashMap<Integer, JSONArray>();
		for (Book b : books) {
			if (!barcodes.containsKey(b.getQuantity())) {
				barcodes.put(b.getQuantity(), new JSONArray());
			}
			barcodes.get(b.getQuantity()).put(b.getBarcode());
		}
		return ResponseEntity.ok(new JSONObject(barcodes).toString());
	}

}
