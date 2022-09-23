package com.gdmatstaffs.JUnitDemo.dto;

import com.gdmatstaffs.JUnitDemo.entity.Book;
import com.gdmatstaffs.JUnitDemo.entity.Copy;
import com.gdmatstaffs.JUnitDemo.entity.CopyStatus;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DTO_FactoryTest
{

    @Test
    void when_BookEntityHasNoCopies_Expect_CorrectBookDTO_WithNoCopyDTOs()
    {
        Book book =
                new Book(
                        1,
                        "Title",
                        "Author",
                        "ISBN",
                        new ArrayList<>());

        BookDTO expectedBookDTO =
                new BookDTO(
                        1,
                        "Title",
                        "Author",
                        "ISBN",
                        0,
                        new ArrayList<>());

        BookDTO result = new DTO_Factory().createDTO(book);

        assertEquals(expectedBookDTO, result);
    }

    @Test
    void when_BookEntityHasOneCopy_Expect_CorrectBookDTO_WithOneCopyDTO()
    {
        Book book =
                new Book(
                        1,
                        "Title",
                        "Author",
                        "ISBN",
                        null);

        CopyStatus copyStatus = new CopyStatus(1, "Available");

        List<Copy> copies =
                Collections.singletonList(
                        new Copy(1, book, copyStatus));
        book.setCopies(copies);

        BookDTO expectedBookDTO =
                new BookDTO(
                        1,
                        "Title",
                        "Author",
                        "ISBN",
                        1,
                        null);

        expectedBookDTO.setCopies(
                Collections.singletonList(
                        new CopyDTO(1, expectedBookDTO, "Available")));

        BookDTO result = new DTO_Factory().createDTO(book);

        assertEquals(expectedBookDTO, result);
    }

    @Test
    void when_BookEntityHasThreeCopies_Expect_CorrectBookDTO_WithThreeCopyDTOs()
    {
        Book book =
                new Book(
                        1,
                        "Title",
                        "Author",
                        "ISBN",
                        null);

        CopyStatus availableStatus = new CopyStatus(1, "Available");
        CopyStatus onLoanStatus = new CopyStatus(1, "On loan");

        List<Copy> copies =
                Arrays.asList(
                        new Copy(1, book, availableStatus),
                        new Copy(2, book, onLoanStatus),
                        new Copy(3, book, availableStatus)
                );

        book.setCopies(copies);

        BookDTO expectedBookDTO =
                new BookDTO(
                        1,
                        "Title",
                        "Author",
                        "ISBN",
                        3,
                        null);

        expectedBookDTO.setCopies(
                Arrays.asList(
                        new CopyDTO(1, expectedBookDTO, "Available"),
                        new CopyDTO(2, expectedBookDTO, "On loan"),
                        new CopyDTO(3, expectedBookDTO, "Available")
                ));

        BookDTO result = new DTO_Factory().createDTO(book);

        assertEquals(expectedBookDTO, result);
    }
}
