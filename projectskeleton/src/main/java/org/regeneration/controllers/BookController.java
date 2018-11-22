package org.regeneration.controllers;

import org.regeneration.exceptions.BookNotFoundException;
import org.regeneration.models.Book;
import org.regeneration.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;

@RestController
@Validated
public class BookController {

    private final BookRepository bookRepository;

    @Autowired
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/books")
    public List<Book> getBooks(@RequestParam(value = "title", required = false) @Size(min = 3) String title) {
        if (title == null) {
            return bookRepository.findAll();
        } else {
            return bookRepository.findByTitleContaining(title);
        }
    }

    @GetMapping("/books/{id}")
    public Book getBook(@PathVariable Long id) {
       return bookRepository.findById(id)
               .orElseThrow(() -> new BookNotFoundException(id));
    }

    @PostMapping("/books")
    public Book newBook(@RequestBody @Valid Book book) {
        return bookRepository.save(book);
    }

    @PutMapping("/books/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        return bookRepository.findById(id)
                .map(book -> {
                    book.setIsbn(updatedBook.getIsbn());
                    book.setTitle(updatedBook.getTitle());
                    return bookRepository.save(book);
                })
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    @DeleteMapping("/books/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable Long id) {
        getBook(id);
        bookRepository.deleteById(id);
    }

}
