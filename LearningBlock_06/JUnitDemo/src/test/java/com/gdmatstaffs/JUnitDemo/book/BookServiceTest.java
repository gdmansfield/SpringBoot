package com.gdmatstaffs.JUnitDemo.book;

import com.gdmatstaffs.JUnitDemo.dto.BookDTO;
import com.gdmatstaffs.JUnitDemo.dto.DTO_Factory;
import com.gdmatstaffs.JUnitDemo.entity.Book;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest
{

    @Test
    void when_ZeroBookEntities_Expect_GetSummaryOfAllBooksMakesOneCallToBookRepositoryFindAllAndNoInteractionWithDtoFactory()
    {
        List<Book> bookList = Collections.EMPTY_LIST;

        BookRepository mockBookRepository = mock(BookRepository.class);
        when(mockBookRepository.findAll()).thenReturn(bookList);

        DTO_Factory mockDto_factory = mock(DTO_Factory.class);
        BookService bookService = new BookService(mockBookRepository, mockDto_factory);

        bookService.getSummaryOfAllBooks();

        verify(mockBookRepository, times(1)).findAll();
        verifyNoInteractions(mockDto_factory);
    }

    @Test
    void when_ZeroBookEntities_Expect_GetSummaryOfAllBooksReturnsEmptyBookDtoList()
    {
        List<Book> bookList = Collections.EMPTY_LIST;

        BookRepository mockBookRepository = mock(BookRepository.class);
        when(mockBookRepository.findAll()).thenReturn(bookList);

        DTO_Factory mockDto_factory = mock(DTO_Factory.class);
        when(mockDto_factory.createSummaryDTO(any(Book.class)))
                .thenReturn(mock(BookDTO.class));

        BookService bookService = new BookService(mockBookRepository, mockDto_factory);

        List<BookDTO> result = bookService.getSummaryOfAllBooks();

        assertTrue(result.isEmpty());
    }

    @Test
    void when_ThreeBookEntities_Expect_GetSummaryOfAllBooksMakesOneCallToBookRepositoryFindAllAndThreeCallsToDtoFactoryCreateSummaryDTO()
    {
        List<Book> bookList =
                Arrays.asList(
                        mock(Book.class),
                        mock(Book.class),
                        mock(Book.class)
                );

        BookRepository mockBookRepository = mock(BookRepository.class);
        when(mockBookRepository.findAll()).thenReturn(bookList);

        DTO_Factory mockDto_factory = mock(DTO_Factory.class);

        BookService bookService = new BookService(mockBookRepository, mockDto_factory);

        bookService.getSummaryOfAllBooks();

        verify(mockBookRepository, times(1)).findAll();
        verify(mockDto_factory, times(3)).createSummaryDTO(any(Book.class));
    }

    @Test
    void when_ThreeBookEntities_Expect_GetSummaryOfAllBooksReturnsListOfThreeBookDtos()
    {
        List<Book> bookList =
                Arrays.asList(
                        mock(Book.class),
                        mock(Book.class),
                        mock(Book.class)
                );

        BookRepository mockBookRepository = mock(BookRepository.class);
        when(mockBookRepository.findAll()).thenReturn(bookList);

        DTO_Factory mockDto_factory = mock(DTO_Factory.class);
        when(mockDto_factory.createSummaryDTO(any(Book.class)))
                .thenReturn(mock(BookDTO.class));

        BookService bookService = new BookService(mockBookRepository, mockDto_factory);

        List<BookDTO> result = bookService.getSummaryOfAllBooks();

        assertEquals(3, result.size());
    }

    @Test
    void when_BookIdExists_Expect_GetBookDetailsMakesOneCallToBookRepositoryFindByIdAndOneCallsToDtoFactoryCreateDTO()
    {
        BookRepository mockBookRepository = mock(BookRepository.class);
        when(mockBookRepository.findById(anyInt()))
                .thenReturn(Optional.of(mock(Book.class)));

        DTO_Factory mockDto_factory = mock(DTO_Factory.class);

        BookService bookService = new BookService(mockBookRepository, mockDto_factory);

        bookService.getBookDetails(anyInt());

        verify(mockBookRepository, times(1)).findById(anyInt());
        verify(mockDto_factory, times(1)).createDTO(any(Book.class));
    }

    @Test
    void when_BookIdExists_Expect_GetBookDetailsReturnsNonNullBookDTO()
    {
        BookRepository mockBookRepository = mock(BookRepository.class);
        when(mockBookRepository.findById(anyInt()))
                .thenReturn(Optional.of(mock(Book.class)));

        DTO_Factory mockDto_factory = mock(DTO_Factory.class);
        when(mockDto_factory.createDTO(any(Book.class)))
                .thenReturn(mock(BookDTO.class));

        BookService bookService = new BookService(mockBookRepository, mockDto_factory);

        BookDTO result = bookService.getBookDetails(anyInt());

        assertNotNull(result);
    }

    @Test
    void when_BookIdDoesNotExist_Expect_GetBookDetailsMakesOneCallToBookRepositoryFindByIdAndNoCallsToDtoFactoryCreateDTO()
    {
        BookRepository mockBookRepository = mock(BookRepository.class);
        when(mockBookRepository.findById(anyInt()))
                .thenReturn(Optional.empty());

        DTO_Factory mockDto_factory = mock(DTO_Factory.class);

        BookService bookService = new BookService(mockBookRepository, mockDto_factory);

        bookService.getBookDetails(anyInt());

        verify(mockBookRepository, times(1)).findById(anyInt());
        verifyNoInteractions(mockDto_factory);
    }

    @Test
    void when_BookIdDoesNotExist_Expect_GetBookDetailsReturnsNull()
    {
        BookRepository mockBookRepository = mock(BookRepository.class);
        when(mockBookRepository.findById(anyInt()))
                .thenReturn(Optional.empty());

        DTO_Factory mockDto_factory = mock(DTO_Factory.class);

        BookService bookService = new BookService(mockBookRepository, mockDto_factory);

        BookDTO result = bookService.getBookDetails(anyInt());

        assertNull(result);
    }
}