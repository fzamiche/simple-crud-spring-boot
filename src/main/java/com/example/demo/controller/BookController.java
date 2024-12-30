package com.example.demo.controller;


import com.example.demo.model.Book;
import com.example.demo.repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

/**
 * The BookController class provides REST endpoints to manage books within the application.
 * It handles all operations related to adding, retrieving, updating, and deleting books.
 */
@RestController
public class BookController {

    @Autowired
    private BookRepo bookRepo;

    /**
     * Retrieves all books available in the repository.
     *
     * @return a response entity containing a list of books and a
     * corresponding HTTP status code. If no books are found, it provides
     * a status of NO_CONTENT. On successful retrieval, it returns the list
     * of books and a status of OK. In case of an error, it returns a status
     * of INTERNAL_SERVER_ERROR.
     */
    @GetMapping("/getAllBooks")
    public ResponseEntity<List<Book>> getAllBooks() {
        try {
            List<Book> books = bookRepo.findAll();
            if (books.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(books, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getBookById/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Optional<Book> bookOptional = bookRepo.findById(Math.toIntExact(id));

        if (bookOptional.isPresent()) {
            return new ResponseEntity<>(bookOptional.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/addBook")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book bookObj =  bookRepo.save(book);
        return new ResponseEntity<>(bookObj, HttpStatus.OK);
    }
    @PostMapping("/testJson")
    public void testJson(@RequestBody Book book) {
        System.out.println("Received book: " + book);
    }


    @PostMapping("/updateBookById/{id}")
    public ResponseEntity<Book> updateBookById(@PathVariable Long id, @RequestBody Book newBook) {
        Optional<Book> oldBook = bookRepo.findById(Math.toIntExact(id));
        if(oldBook.isPresent()){
            Book updatedBook = oldBook.get();
            updatedBook.setAuthor(newBook.getAuthor());
            updatedBook.setTitle(newBook.getTitle());
            return new ResponseEntity<>(bookRepo.save(updatedBook), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/deleteBookById/{id}")
    public ResponseEntity<HttpStatus> deleteBookById(@PathVariable Long id) {
        bookRepo.deleteById(Math.toIntExact(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
