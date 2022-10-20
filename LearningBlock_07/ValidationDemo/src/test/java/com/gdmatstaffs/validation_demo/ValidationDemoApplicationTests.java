package com.gdmatstaffs.validation_demo;

import com.gdmatstaffs.validation_demo.book.BookRestController;
import com.gdmatstaffs.validation_demo.book.BookService;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(BookRestController.class)
@AutoConfigureMockMvc
class ValidationDemoApplicationTests
{
	@MockBean
	BookService bookService;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void when_PostRequestToCreateBookHasInvalidBook_expect_ErrorMessages() throws Exception
	{
		String badBookDTO =
				"{\"id\": -1, \"title\": \"\", \"author\": \"\", \"isbn\": \"\", \"numberOfCopies\": -1}";
		mockMvc
			.perform(MockMvcRequestBuilders.post("/book/create")
			.content(badBookDTO)
			.contentType(MediaType.APPLICATION_JSON))

			.andExpect(MockMvcResultMatchers.status().isBadRequest())
			.andExpect(MockMvcResultMatchers.jsonPath(
					"$.id", Is.is("Id must be greater than zero")))
			.andExpect(MockMvcResultMatchers.jsonPath(
					"$.title", Is.is("Title cannot be blank")))
			.andExpect(MockMvcResultMatchers.jsonPath(
					"$.author", Is.is("Author cannot be blank")))
			.andExpect(MockMvcResultMatchers.jsonPath(
					"$.isbn", Is.is("ISBN must be present")))
			.andExpect(MockMvcResultMatchers.jsonPath(
					"$.numberOfCopies", Is.is("Number of copies must be zero or more")))
			.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
	}
}
