package com.gdmatstaffs.JUnitDemo.book;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.gdmatstaffs.JUnitDemo.copy.CopyRepository;
import com.gdmatstaffs.JUnitDemo.copy_status.CopyStatusRepository;
import com.gdmatstaffs.JUnitDemo.dto.BookDTO;
import com.gdmatstaffs.JUnitDemo.dto.CopyDTO;
import com.gdmatstaffs.JUnitDemo.entity.Book;
import com.gdmatstaffs.JUnitDemo.entity.Copy;
import com.gdmatstaffs.JUnitDemo.entity.CopyStatus;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class BookRestControllerHttpIntegrationTest
{

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CopyRepository copyRepository;

    @Autowired
    private CopyStatusRepository copyStatusRepository;

    @Test
    void when_NoBooks_expect_EmptyListOfBookDTO() throws Exception
    {
        copyRepository.deleteAll();
        bookRepository.deleteAll();

        ResponseEntity<String> responseEntity = restTemplate.getForEntity("/book", String.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, responseEntity.getHeaders().getContentType());
        assertEquals("[]", responseEntity.getBody());
    }

    @Test
    void when_ThreeBooks_expect_GetSummaryOfAllBooksReturnsListFromBookService() throws Exception
    {
        CopyStatus available = copyStatusRepository.findById(1).get();
        List<Book> books =
                Arrays.asList(
                        new Book(0, "Title 1", "Author 1", "ISBN 1", null),
                        new Book(0, "Title 2", "Author 2", "ISBN 2", null),
                        new Book(0, "Title 3", "Author 3", "ISBN 3", new ArrayList<>())
                );

        copyRepository.deleteAll();
        bookRepository.deleteAll();
        bookRepository.saveAll(books);

        List<Copy> copies =
                Arrays.asList(
                        new Copy(0, books.get(0), available),
                        new Copy(0, books.get(0), available),
                        new Copy(0, books.get(1), available)
                );

        copyRepository.saveAll(copies);

        List<BookDTO> expectedBookDTOList =
                Arrays.asList(
                        new BookDTO(books.get(0).getId(), "Title 1", "Author 1", "ISBN 1", 2, null),
                        new BookDTO(books.get(1).getId(), "Title 2", "Author 2", "ISBN 2", 1, null),
                        new BookDTO(books.get(2).getId(), "Title 3", "Author 3", "ISBN 3", 0, null)
                );
        String expectedJson = new ObjectMapper().writeValueAsString(expectedBookDTOList);

        ResponseEntity<String> responseEntity = restTemplate.getForEntity("/book", String.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, responseEntity.getHeaders().getContentType());
        JSONAssert.assertEquals(expectedJson, responseEntity.getBody(), false);
    }

    @Test
    void when_BookExists_expect_NonNullBookDTO() throws Exception
    {
        CopyStatus available = copyStatusRepository.findById(1).get();
        Book book = new Book(0, "Title 2", "Author 2", "ISBN 2", null);

        copyRepository.deleteAll();
        bookRepository.deleteAll();
        bookRepository.save(book);

        List<Copy> copies =
                Arrays.asList(
                        new Copy(0, book, available),
                        new Copy(0, book, available),
                        new Copy(0, book, available),
                        new Copy(0, book, available)
                );

        copyRepository.saveAll(copies);

        BookDTO expectedBookDTO =
                new BookDTO(
                        book.getId(),
                        "Title 2",
                        "Author 2",
                        "ISBN 2",
                        4,
                        null);

        List<CopyDTO> copyDTOs =
                Arrays.asList(
                        new CopyDTO(copies.get(0).getId(), expectedBookDTO, "Available"),
                        new CopyDTO(copies.get(1).getId(), expectedBookDTO, "Available"),
                        new CopyDTO(copies.get(2).getId(), expectedBookDTO, "Available"),
                        new CopyDTO(copies.get(3).getId(), expectedBookDTO, "Available")
                );

        expectedBookDTO.setCopies(copyDTOs);

        ObjectMapper mapper = new ObjectMapper();
        // tell the mapper to take notice of transient setting on fields not getters
        mapper.setVisibility(
                VisibilityChecker.Std.defaultInstance()
                        .withFieldVisibility(JsonAutoDetect.Visibility.ANY)
                        .withGetterVisibility(JsonAutoDetect.Visibility.NONE)
        );

        String expectedJson = mapper.writeValueAsString(expectedBookDTO);

        ResponseEntity<String> responseEntity = restTemplate.getForEntity("/book/" + book.getId(), String.class);

        System.out.println(responseEntity.getBody());

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, responseEntity.getHeaders().getContentType());
        JSONAssert.assertEquals(expectedJson, responseEntity.getBody(), false);
    }

    @Test
    void when_BookDoesNotExist_expect_Null() throws Exception
    {
        copyRepository.deleteAll();
        bookRepository.deleteAll();

        ResponseEntity<String> responseEntity = restTemplate.getForEntity("/book/10", String.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
    }
}