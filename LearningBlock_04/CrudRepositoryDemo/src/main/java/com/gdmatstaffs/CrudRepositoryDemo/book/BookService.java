package com.gdmatstaffs.CrudRepositoryDemo.book;

import com.gdmatstaffs.CrudRepositoryDemo.copy.CopyService;
import com.gdmatstaffs.CrudRepositoryDemo.dto.BookDTO;
import com.gdmatstaffs.CrudRepositoryDemo.dto.CopyDTO;
import com.gdmatstaffs.CrudRepositoryDemo.dto.DTO_Factory;
import com.gdmatstaffs.CrudRepositoryDemo.entity.Book;
import com.gdmatstaffs.CrudRepositoryDemo.entity.BookCopyView;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@AllArgsConstructor
public class BookService
{
    private final BookRepository bookRepository;
    private final BookCopyRepository bookCopyRepository;
    private final CopyService copyService;
    private DTO_Factory dto_factory;

    public List<BookDTO> getSummaryOfAllBooks()
    {
        List<BookDTO> list = new ArrayList<>();
        for (Book b : bookRepository.findAll())
        {
            BookDTO bookDTO = dto_factory.createDTO(b);

            int numberOfCopies = copyService.findNumberOfCopiesOfBook(b.getId());
            bookDTO.setNumberOfCopies(numberOfCopies);

            list.add(bookDTO);
        }
        return list;
    }

    public List<BookDTO> getAlternativeSummaryOfAllBooks()
    {
        List<BookDTO> list = new ArrayList<>();
        for (BookCopyView b : bookCopyRepository.findAllBooks())
        {
            BookDTO bookDTO = dto_factory.createDTO(b);

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

            Collection<CopyDTO> copies = copyService.findCopiesOfBook(bookDTO);
            bookDTO.setCopies(copies);
            bookDTO.setNumberOfCopies(copies.size());

            return bookDTO;
        }
        return null;
    }
}
