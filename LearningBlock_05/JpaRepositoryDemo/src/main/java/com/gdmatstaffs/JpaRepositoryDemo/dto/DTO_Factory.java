package com.gdmatstaffs.JpaRepositoryDemo.dto;

import com.gdmatstaffs.JpaRepositoryDemo.entity.Book;
import com.gdmatstaffs.JpaRepositoryDemo.entity.Copy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

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
        bookDTO.setNumberOfCopies(b.getCopyCount());
        bookDTO.setCopies(createDTOCollection(b.getCopies()));

        return bookDTO;
    }

    public BookDTO createSummaryDTO(Book b)
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

    public CopyDTO createDTO(Copy copy)
    {
        return new CopyDTO(copy.getId(), createSummaryDTO(copy.getBook()), copy.getStatus().getStatus());
    }

    public CopyDTO createDTO(Copy copy, BookDTO bookDTO)
    {
        return new CopyDTO(copy.getId(), bookDTO, copy.getStatus().getStatus());
    }

    public Collection<CopyDTO> createDTOCollection(Collection<Copy> copies)
    {
        BookDTO b = createSummaryDTO(copies.stream().findFirst().get().getBook());
        return copies.stream().map(c -> createDTO(c, b)).collect(Collectors.toCollection(ArrayList::new));
    }
}
