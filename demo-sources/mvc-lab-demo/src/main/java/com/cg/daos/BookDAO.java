package com.cg.daos;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.cg.entities.Book;

@Repository
public class BookDAO {
private List<Book> books = new ArrayList<>();
	
	@PostConstruct
	public void init() {
		books.add(new Book(101, "Let Us C", "Kanetkar"));
		books.add(new Book(102,"OOPs using C++","Balaguruswami"));
	}
	
	public Book findById(int bookId) {
		for(Book b : books) {
			if(b.getBookId()==bookId) {
				return b;
			}
		}
		return null;
	}
	
	public List<Book> getAll(){
		return books;
	}
	
	public void update(int id,Book b) {
		Book temp = findById(id);
		temp.setAuthor(b.getAuthor());
		temp.setTitle(b.getTitle());
	}
	
	public void delete(Book book) {
		books.remove(book);
	}

	public void create(Book book) {
		books.add(book);
	}
}
