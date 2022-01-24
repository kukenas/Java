package com.dematic.utils;

import org.json.JSONObject;

import com.dematic.model.AntiqueBook;
import com.dematic.model.Book;
import com.dematic.model.ScienceJournal;

public class JsonUtil {

	// Utility class, no need to instantiate
	private JsonUtil() {

	}

	public static Book getCopyOfBook(long barcode, String params, Book book) {
		Book.Builder builder = new Book.Builder(barcode).name(book.getName()).author(book.getAuthor())
				.quantity(book.getQuantity()).price(book.getPrice());
		JSONObject jsonObject = new JSONObject(params);
		if (jsonObject.has("author") && book.getAuthor() != jsonObject.getString("author")) {
			builder.author(jsonObject.getString("author"));
		}
		if (jsonObject.has("name") && book.getName() != jsonObject.getString("name")) {
			builder.name(jsonObject.getString("name"));
		}
		if (jsonObject.has("quantity") && book.getQuantity() != jsonObject.getInt("quantity")) {
			builder.quantity(jsonObject.getInt("quantity"));
		}
		if (jsonObject.has("price") && book.getPrice() != jsonObject.getDouble("price")) {
			builder.price(jsonObject.getDouble("price"));
		}
		if (jsonObject.has("price") && book.getPrice() != jsonObject.getDouble("price")) {
			builder.price(jsonObject.getDouble("price"));
		}
		return builder.build();
	}

	public static Book getCopyOfAntiqueBook(long barcode, String params, AntiqueBook book) {
		AntiqueBook.Builder builder = (AntiqueBook.Builder) new AntiqueBook.Builder(barcode).release(book.getRelease())
				.name(book.getName()).author(book.getAuthor()).quantity(book.getQuantity()).price(book.getPrice());
		JSONObject jsonObject = new JSONObject(params);
		if (jsonObject.has("author") && book.getAuthor() != jsonObject.getString("author")) {
			builder.author(jsonObject.getString("author"));
		}
		if (jsonObject.has("name") && book.getName() != jsonObject.getString("name")) {
			builder.name(jsonObject.getString("name"));
		}
		if (jsonObject.has("quantity") && book.getQuantity() != jsonObject.getInt("quantity")) {
			builder.quantity(jsonObject.getInt("quantity"));
		}
		if (jsonObject.has("price") && book.getPrice() != jsonObject.getDouble("price")) {
			builder.price(jsonObject.getDouble("price"));
		}
		if (jsonObject.has("price") && book.getPrice() != jsonObject.getDouble("price")) {
			builder.price(jsonObject.getDouble("price"));
		}
		if (jsonObject.has("release") && book.getPrice() != jsonObject.getDouble("release")) {
			builder.release(jsonObject.getInt("release"));
		}
		return builder.build();
	}

	public static Book getCopyOfScienceJournal(long barcode, String params, ScienceJournal book) {
		ScienceJournal.Builder builder = (ScienceJournal.Builder) new ScienceJournal.Builder(barcode)
				.index(book.getIndex()).name(book.getName()).author(book.getAuthor()).quantity(book.getQuantity())
				.price(book.getPrice());
		JSONObject jsonObject = new JSONObject(params);
		if (jsonObject.has("author") && book.getAuthor() != jsonObject.getString("author")) {
			builder.author(jsonObject.getString("author"));
		}
		if (jsonObject.has("name") && book.getName() != jsonObject.getString("name")) {
			builder.name(jsonObject.getString("name"));
		}
		if (jsonObject.has("quantity") && book.getQuantity() != jsonObject.getInt("quantity")) {
			builder.quantity(jsonObject.getInt("quantity"));
		}
		if (jsonObject.has("price") && book.getPrice() != jsonObject.getDouble("price")) {
			builder.price(jsonObject.getDouble("price"));
		}
		if (jsonObject.has("index") && book.getIndex() != jsonObject.getInt("index")) {
			builder.index(jsonObject.getInt("index"));
		}
		return builder.build();
	}
}
