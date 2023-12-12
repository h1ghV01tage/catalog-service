package com.polarbookshop.catalogservice.domain;

public class BookAlreadyExistsException extends RuntimeException {

	public BookAlreadyExistsException(String isbn) {
		
		super("A book with this ISBN " + isbn + " already exists. " );
		
	}
	
}