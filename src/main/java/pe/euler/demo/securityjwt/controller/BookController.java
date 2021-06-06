package pe.euler.demo.securityjwt.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.euler.demo.securityjwt.model.Book;
import pe.euler.demo.securityjwt.service.BookService;

@RestController
@RequestMapping(value = "/book")
public class BookController {
	
	private final BookService bookService;

	public BookController(BookService bookService) {
		super();
		this.bookService = bookService;
	}

	@GetMapping
	public List<Book> getBooks() {
		return bookService.getBooks();
	}

}
