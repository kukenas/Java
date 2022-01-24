package com.dematic.persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dematic.model.AntiqueBook;
import com.dematic.model.Book;
import com.dematic.model.ScienceJournal;

@Configuration
public class DatabaseLoader {

	private static final Logger log = LoggerFactory.getLogger(DatabaseLoader.class);

	@Bean
	CommandLineRunner initDatabase(BookRepository repository) {
		return args -> {
			log.info("Preloading "
					+ repository.save(new Book.Builder(12345).name("Harry Potter").author("Lindgren").build()));
			log.info("Preloading " + repository.save(new Book.Builder(678910L).name("Book").build()));
			log.info("Preloading " + repository.save(new AntiqueBook.Builder(1777L).release(1770).name("Facts").quantity(7).price(7.17).build()));
			log.info("Preloading " + repository.save(new AntiqueBook.Builder(1715L).release(1790).name("Funny").quantity(7).price(10).build()));
			log.info("Preloading " + repository.save(new ScienceJournal.Builder(10L).index(7).name("Science").quantity(70).price(171.7).build()));
		};
	}

}
