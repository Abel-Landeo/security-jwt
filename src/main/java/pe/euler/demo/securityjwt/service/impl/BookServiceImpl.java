package pe.euler.demo.securityjwt.service.impl;

import java.util.Arrays;
import java.util.List;

import pe.euler.demo.securityjwt.model.Book;
import pe.euler.demo.securityjwt.service.BookService;

public class BookServiceImpl implements BookService {

	@Override
	public List<Book> getBooks() {
		return mockBooks();
	}

	private List<Book> mockBooks() {
		Book b1 = new Book();
		b1.setId("id1");
		b1.setTitle("El Aleph");
		b1.setAuthor("Jorge Luis Borges");
		
		Book b2 = new Book();
		b2.setId("id2");
		b2.setTitle("Brief history of time");
		b2.setAuthor("Stephen Hawking");
		
		Book b3 = new Book();
		b3.setId("id3");
		b3.setTitle("El Sexto");
		b3.setAuthor("Jose Maria Arguedas");
		
		return Arrays.asList(b1, b2, b3);
	}

}
