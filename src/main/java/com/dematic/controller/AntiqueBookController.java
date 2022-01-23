package com.dematic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dematic.model.AntiqueBook;
import com.dematic.service.BookService;

@RestController
@RequestMapping("/antiquebooks")
public class AntiqueBookController {

	@Autowired
	private BookService bookService;

	@RequestMapping(method = RequestMethod.GET)
	List<AntiqueBook> findAntiqueBooks() {
		return bookService.getAntiqueBooks();
	}

}
