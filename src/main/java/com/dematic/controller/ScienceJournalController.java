package com.dematic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dematic.model.ScienceJournal;
import com.dematic.service.BookService;

@RestController
@RequestMapping("/sciencejournals")
public class ScienceJournalController {

	@Autowired
	private BookService bookService;

	@RequestMapping(method = RequestMethod.GET)
	List<ScienceJournal> findScienceJournals() {
		return bookService.getScienceJournals();
	}

}
