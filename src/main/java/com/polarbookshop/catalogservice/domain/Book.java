package com.polarbookshop.catalogservice.domain;

//create a book record for the entity book because it has to be immutable
public record Book(String isbn, String title,String author,Double price) {

	

}
