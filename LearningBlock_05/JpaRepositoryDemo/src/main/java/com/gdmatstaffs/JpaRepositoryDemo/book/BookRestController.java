package com.gdmatstaffs.JpaRepositoryDemo.book;

import com.gdmatstaffs.JpaRepositoryDemo.dto.BookDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/book")
@AllArgsConstructor
public class BookRestController
{
    private final BookService bookService;

    @GetMapping
    public List<BookDTO> getSummaryOfAllBooks()
    {
        return bookService.getSummaryOfAllBooks();
    }

    @GetMapping(path = "/{id}")
    public BookDTO getBookDetails(@PathVariable("id") int bookId)
    {
        BookDTO book = bookService.getBookDetails(bookId);

        return book;
    }
}
