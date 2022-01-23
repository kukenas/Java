package com.dematic.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.dematic.model.AntiqueBook;
import com.dematic.model.Book;
import com.dematic.model.ScienceJournal;

public interface BookRepository extends JpaRepository<Book, Long> {

	@Query("SELECT b FROM antiqueBook b")
	List<AntiqueBook> findAntiqueBooks();
	
	@Query("SELECT b FROM scienceJournal b")
	List<ScienceJournal> findScienceJournals();
	
	@Query("SELECT b FROM scienceJournal b WHERE b.barcode = ?1")
	Optional<ScienceJournal> findByBarcodeScience(@Param("barcode") long barcode);
	
	@Query("SELECT b FROM book b")
	List<Book> allBooks();

	@Query("SELECT b FROM book b WHERE REPLACE(b.name,' ','') = ?1")
	Optional<Book> findByName(@Param("name") String name);

	@Query("SELECT b FROM book b WHERE b.barcode = ?1")
	Optional<Book> findByBarcode(@Param("barcode") long barcode);
	
	@Query("SELECT b FROM antiqueBook b WHERE b.barcode = ?1")
	Optional<AntiqueBook> findByBarcodeAntique(@Param("barcode") long barcode);
	
	@Query("SELECT b FROM book b GROUP BY b.barcode, b.price ORDER BY price DESC")
	List<Book> getBarcodesGrouped();
}
