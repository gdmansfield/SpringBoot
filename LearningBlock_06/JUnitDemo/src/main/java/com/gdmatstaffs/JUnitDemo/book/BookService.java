package com.gdmatstaffs.JUnitDemo.book;

import com.gdmatstaffs.JUnitDemo.dto.BookDTO;
import com.gdmatstaffs.JUnitDemo.dto.DTO_Factory;
import com.gdmatstaffs.JUnitDemo.entity.Book;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class BookService
{
    private final BookRepository bookRepository;
    private DTO_Factory dto_factory;

    public List<BookDTO> getSummaryOfAllBooks()
    {
        List<BookDTO> list = new ArrayList<>();
        List<Book> books = bookRepository.findAll();
        for (Book b : books)
        {
            BookDTO bookDTO = dto_factory.createSummaryDTO(b);

            list.add(bookDTO);
        }
        return list;
    }

    public BookDTO getBookDetails(int bookId)
    {
        Book book =
                bookRepository
                        .findById(bookId)
                        .orElse(null);
        if (book != null)
        {
            return dto_factory.createDTO(book);
        }
        return null;
    }
}
