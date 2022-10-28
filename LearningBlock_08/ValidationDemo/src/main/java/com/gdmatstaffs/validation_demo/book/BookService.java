package com.gdmatstaffs.validation_demo.book;

import com.gdmatstaffs.validation_demo.dto.BookDTO;
import com.gdmatstaffs.validation_demo.dto.DTO_Factory;
import com.gdmatstaffs.validation_demo.entity.Book;
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
        for (Book b : bookRepository.findAll())
        {
            BookDTO bookDTO = dto_factory.createSummaryDTO(b);

            list.add(bookDTO);
        }
        return list;
    }

    public BookDTO createBook(BookDTO b)
    {
        Book book = new Book(b.getId(), b.getTitle(), b.getAuthor(), b.getIsbn(), null);
        book = bookRepository.save(book);

        return dto_factory.createDTO(book);
    }

    public BookDTO getBookDetails(int bookId)
    {
        Book book =
                bookRepository
                        .findById(bookId)
                        .orElse(null);
        if (book != null)
        {
            BookDTO bookDTO = dto_factory.createDTO(book);

            return bookDTO;
        }
        return null;
    }
}
