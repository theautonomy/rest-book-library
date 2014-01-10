package org.weili.rest.dao;

import java.util.ArrayList;

import org.weili.rest.domain.Book;

public interface IBookDAO {
	
	public ArrayList<Book> findAllBooks();

	public Book findBook(int index);

	public void addBook(Book book);

	public Book updateBook(Book book);

	public void removeBook(int index);

}
