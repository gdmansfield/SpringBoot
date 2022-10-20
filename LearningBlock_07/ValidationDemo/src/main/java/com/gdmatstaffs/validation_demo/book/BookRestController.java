package com.gdmatstaffs.validation_demo.book;

import com.gdmatstaffs.validation_demo.dto.BookDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/book")
@AllArgsConstructor
@Validated
public class BookRestController
{
    private final BookService bookService;

    @GetMapping
    public List<BookDTO> getSummaryOfAllBooks()
    {
        return bookService.getSummaryOfAllBooks();
    }

    @GetMapping(path = "/{id}")
    public BookDTO getBookDetails(
            @PathVariable("id")
            @Min(value = 1, message = "Id must be greater than zero") int bookId)
    {
        return bookService.getBookDetails(bookId);
    }

    @PostMapping(path = "/create")
    public BookDTO createBook(@RequestBody @Valid BookDTO bookDTO)
    {
        return bookService.createBook(bookDTO);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex)
    {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult()
                .getAllErrors()
                .forEach(
                        (error) ->
                        {
                            String fieldName = ((FieldError) error).getField();
                            String errorMessage = error.getDefaultMessage();
                            errors.put(fieldName, errorMessage);
                        });
        return errors;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleConstraintViolationExceptions(ConstraintViolationException ex)
    {
        String violations =
                ex.getConstraintViolations()
                        .stream()
                        .map(v -> v.getMessageTemplate())
                        .collect(Collectors.joining("; "));

        return new ResponseEntity<>("Validation error: " + violations, HttpStatus.BAD_REQUEST);
    }
}
