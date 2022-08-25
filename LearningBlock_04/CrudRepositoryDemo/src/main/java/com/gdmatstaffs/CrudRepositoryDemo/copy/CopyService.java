package com.gdmatstaffs.CrudRepositoryDemo.copy;

import com.gdmatstaffs.CrudRepositoryDemo.book.BookRepository;
import com.gdmatstaffs.CrudRepositoryDemo.copy_status.CopyStatusService;
import com.gdmatstaffs.CrudRepositoryDemo.dto.BookDTO;
import com.gdmatstaffs.CrudRepositoryDemo.dto.CopyDTO;
import com.gdmatstaffs.CrudRepositoryDemo.dto.DTO_Factory;
import com.gdmatstaffs.CrudRepositoryDemo.entity.Book;
import com.gdmatstaffs.CrudRepositoryDemo.entity.Copy;
import com.gdmatstaffs.CrudRepositoryDemo.entity.CopyStatus;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CopyService
{
    private final BookRepository bookRepository;
    private final CopyRepository copyRepository;
    private final CopyStatusService copyStatusService;
    private final DTO_Factory dto_factory;

    public CopyDTO borrowCopy(int copyId)
    {
        int availableId = copyStatusService.getByStatus("Available").getId();
        int onLoanId = copyStatusService.getByStatus("On loan").getId();
        Copy copy = copyRepository.findById(copyId).orElse(null);

        if (copy != null && copy.getStatusId() == availableId)
        {
            copy.setStatusId(onLoanId);
            copy = copyRepository.save(copy);

            return getCopyDTO(copy);
        }

        return null;
    }

    public Collection<CopyDTO> findCopiesOfBook(BookDTO book)
    {
        return copyRepository
                .streamAllByBookId(book.getId())
                .map(copy ->
                        dto_factory.createDTO(
                                copy,
                                book,
                                copyStatusService.getById(copy.getStatusId()).getStatus()))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public int findNumberOfCopiesOfBook(int bookId)
    {
        return copyRepository.countByBookId(bookId);
    }

    private CopyDTO getCopyDTO(Copy copy)
    {
        BookDTO bookDTO = getBookDtoForCopy(copy);
        String copyStatus = copyStatusService.getById(copy.getStatusId()).getStatus();
        return dto_factory.createDTO(copy, bookDTO, copyStatus);
    }

    private BookDTO getBookDtoForCopy(Copy copy)
    {
        Book book =
                bookRepository
                        .findById(copy.getBookId())
                        .get();
        return dto_factory.createDTO(book);
    }
}
