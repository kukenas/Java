package com.dematic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import Exceptions.ItemNotFoundAdvice;

@SpringBootApplication
@EntityScan("com.dematic.model")
@EnableJpaRepositories("com.dematic.persistence")
@Import(ItemNotFoundAdvice.class)
public class BookStorageApplication {

	private static final Logger LOG = LogManager.getLogger(BookStorageApplication.class);

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(BookStorageApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		// Do not print Banner to Console
		if (LOG.isInfoEnabled()) {
			LOG.info("Banner mode: OFF");
		}	
		app.run(args);
	}
}
