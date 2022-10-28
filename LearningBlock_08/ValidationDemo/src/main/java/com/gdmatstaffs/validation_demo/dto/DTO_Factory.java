package com.gdmatstaffs.validation_demo.dto;

import com.gdmatstaffs.validation_demo.entity.Book;
import com.gdmatstaffs.validation_demo.entity.Copy;
import com.gdmatstaffs.validation_demo.entity.Loan;
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
                        b.getIsbn(),
                        b.getCopyCount(),
                        createDTOCollection(b.getCopies()));

        return bookDTO;
    }

    public BookDTO createSummaryDTO(Book b)
    {
        BookDTO bookDTO =
                new BookDTO(
                        b.getId(),
                        b.getTitle(),
                        b.getAuthor(),
                        b.getIsbn(),
                        b.getCopyCount(),
                        null);

        return bookDTO;
    }

    public CurrentLoanDTO createDTO(Loan loan)
    {
        return
                new CurrentLoanDTO(
                        loan.getId(),
                        loan.getCopy().getId(),
                        loan.getCopy().getBook().getTitle(),
                        loan.getCopy().getBook().getAuthor(),
                        loan.getLoanDate(),
                        loan.getDueDate());
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
        if (copies == null)
        {
            return null;
        }

        BookDTO b = createSummaryDTO(copies.stream().findFirst().get().getBook());
        return copies.stream().map(c -> createDTO(c, b)).collect(Collectors.toCollection(ArrayList::new));
    }
}
