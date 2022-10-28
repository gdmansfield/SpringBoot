package com.gdmatstaffs.validation_demo.dto;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookDTO
{
    @Min(value = 1, message = "Id must be greater than zero")
    private int id;

    @NotBlank(message = "Title cannot be blank")
    private String title;

    @NotBlank(message = "Author cannot be blank")
    private String author;

    @NotBlank(message = "ISBN must be present")
    private String isbn;

    @Min(value = 0, message = "Number of copies must be zero or more")
    private int numberOfCopies;

    private Collection<CopyDTO> copies;
}
