package controllers;


import entities.Book;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;

@RestController
@RequestMapping("/api/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Book>> findAllBook(){
        return ResponseEntity.ok(bookService.findAllBook());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getPlayerById(@PathVariable Integer id){
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book){
        Book savedBook;
        savedBook = bookService.addBook(book);

        URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/book/{id}")
                .buildAndExpand(savedBook.getId())
                .toUri();

        return ResponseEntity.created(location).body(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book>  updatedPlayer(@PathVariable Integer id, @RequestBody Book updates){
        return ResponseEntity.ok(bookService.updateBook (id, updates));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HashMap<String, Object>> deleteBook(@PathVariable Integer id){

        HashMap<String, Object> responseMap = bookService.deleteBook (id);

        if(responseMap.get("bookInfo") == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMap);
        }

        return ResponseEntity.ok(responseMap);
    }





}
