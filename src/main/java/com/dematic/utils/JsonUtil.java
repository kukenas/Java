package com.dematic.utils;

import org.json.JSONObject;

import com.dematic.model.AntiqueBook;
import com.dematic.model.Book;
import com.dematic.model.Book.Builder;
import com.dematic.model.ScienceJournal;

public class JsonUtil {

	// Utility class, no need to instantiate
	private JsonUtil() {

	}

	public static Book getCopyOfBook(long barcode, String params, Book book) {
		Book.Builder builder = new Book.Builder(barcode).name(book.getName()).author(book.getAuthor())
				.quantity(book.getQuantity()).price(book.getPrice());
		JSONObject jsonObject = new JSONObject(params);
		updateCommon(builder, jsonObject, book);
		if (jsonObject.has("price") && book.getPrice() != jsonObject.getDouble("price")) {
			builder.price(jsonObject.getDouble("price"));
		}
		return builder.build();
	}

	public static Book getCopyOfAntiqueBook(long barcode, String params, AntiqueBook book) {
		AntiqueBook.Builder builder = (AntiqueBook.Builder) new AntiqueBook.Builder(barcode).release(book.getRelease())
				.name(book.getName()).author(book.getAuthor()).quantity(book.getQuantity()).price(book.getPrice());
		JSONObject jsonObject = new JSONObject(params);
		updateCommon(builder, jsonObject, book);
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
		updateCommon(builder, jsonObject, book);
		if (jsonObject.has("index") && book.getIndex() != jsonObject.getInt("index")) {
			builder.index(jsonObject.getInt("index"));
		}
		return builder.build();
	}

	private static void updateCommon(Builder builder, JSONObject jobj, Book book) {
		if (jobj.has("author") && book.getAuthor() != jobj.getString("author")) {
			builder.author(jobj.getString("author"));
		}
		if (jobj.has("name") && book.getName() != jobj.getString("name")) {
			builder.name(jobj.getString("name"));
		}
		if (jobj.has("quantity") && book.getQuantity() != jobj.getInt("quantity")) {
			builder.quantity(jobj.getInt("quantity"));
		}
		if (jobj.has("price") && book.getPrice() != jobj.getDouble("price")) {
			builder.price(jobj.getDouble("price"));
		}
	}

	public static void constructJson(JSONObject jobj, String string) {
		String[] s = string.split(",");
		for (String str : s) {
			String[] tmp = str.split(":");
			jobj.put(tmp[0], tmp[1]);
		}
	}

}
