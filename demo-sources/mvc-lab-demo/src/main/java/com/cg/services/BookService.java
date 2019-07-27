package com.cg.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.daos.BookDAO;
import com.cg.entities.Book;
import com.cg.exceptions.ApplicationException;

@Service
public class BookService {
	
	@Autowired private BookDAO dao;
	
	public Book findById(int id) {
		Book book =	dao.findById(id);
		if(book==null) {
			throw new ApplicationException("Book not found!");
		}
		return book;
	}
	
	public void update(Book book) {
		Book temp = findById(book.getBookId());
		dao.update(book.getBookId(), book);
	}
	
	public void delete(int id) {
		Book book = findById(id);
		dao.delete(book);
	}
	
	public List<Book> getAll(){
		List<Book> books = dao.getAll();
		if(books == null || books.isEmpty()) {
			throw new ApplicationException("No books available yet!");
		}
		return books;
	}
	
	public void save(Book book) {
		Book temp = dao.findById(book.getBookId());
		if(temp==null) {
			dao.create(book);
		}
		else
			throw new ApplicationException("Book already exists!");
	}
}
