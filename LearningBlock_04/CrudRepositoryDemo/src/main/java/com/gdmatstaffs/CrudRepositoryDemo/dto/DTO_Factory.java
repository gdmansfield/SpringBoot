package com.gdmatstaffs.CrudRepositoryDemo.dto;

import com.gdmatstaffs.CrudRepositoryDemo.entity.Book;
import com.gdmatstaffs.CrudRepositoryDemo.entity.BookCopyView;
import com.gdmatstaffs.CrudRepositoryDemo.entity.Copy;
import org.springframework.stereotype.Component;

@Component
public class DTO_Factory
{

    public BookDTO createDTO(Book b)
    {
        BookDTO bookDTO =
                new BookDTO(
                        b.getId(),
                        b.getTitle(),
                        b.getAuthor(),
                        b.getIsbn());

        return bookDTO;
    }

    public BookDTO createDTO(BookCopyView b)
    {
        BookDTO bookDTO =
                new BookDTO(
                        b.getId(),
                        b.getTitle(),
                        b.getAuthor(),
                        b.getIsbn());
        bookDTO.setNumberOfCopies(b.getCopyCount());

        return bookDTO;
    }

    public CopyDTO createDTO(Copy copy, BookDTO book, String status)
    {
        return new CopyDTO(copy.getId(), book, status);
    }
}
