package com.gdmatstaffs.JpaRepositoryDemo.book;

import com.gdmatstaffs.JpaRepositoryDemo.dto.BookDTO;
import com.gdmatstaffs.JpaRepositoryDemo.dto.DTO_Factory;
import com.gdmatstaffs.JpaRepositoryDemo.entity.Book;
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
