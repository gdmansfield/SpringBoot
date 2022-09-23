package com.gdmatstaffs.JUnitDemo.copy;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.gdmatstaffs.JUnitDemo.book.BookRepository;
import com.gdmatstaffs.JUnitDemo.copy_status.CopyStatusRepository;
import com.gdmatstaffs.JUnitDemo.dto.CopyDTO;
import com.gdmatstaffs.JUnitDemo.entity.Book;
import com.gdmatstaffs.JUnitDemo.entity.Copy;
import com.gdmatstaffs.JUnitDemo.entity.CopyStatus;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CopyRestControllerHttpIntegrationTest
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
    void when_BorrowAvailableCopy_expect_NonNullCopyDTO() throws Exception
    {
        CopyStatus available = copyStatusRepository.findById(1).get();
        Book book = new Book(0, "Title 1", "Author 1", "ISBN 1", null);

        copyRepository.deleteAll();
        bookRepository.deleteAll();
        bookRepository.save(book);

        Copy copy = new Copy(0, book, available);

        copyRepository.save(copy);

        CopyDTO expectedCopyDTO = new CopyDTO(copy.getId(), null, "On loan");

        ObjectMapper mapper = new ObjectMapper();
        // tell the mapper to take notice of transient setting on fields not getters
        mapper.setVisibility(
                VisibilityChecker.Std.defaultInstance()
                        .withFieldVisibility(JsonAutoDetect.Visibility.ANY)
                        .withGetterVisibility(JsonAutoDetect.Visibility.NONE)
        );

        String expectedJson = mapper.writeValueAsString(expectedCopyDTO);

        ResponseEntity<String> responseEntity =
                restTemplate.postForEntity(
                        "/copy/" + copy.getId() + "/borrow",
                        null, // payload
                        String.class);

        System.out.println(responseEntity.getBody());

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, responseEntity.getHeaders().getContentType());
        JSONAssert.assertEquals(expectedJson, responseEntity.getBody(), false);
    }
}