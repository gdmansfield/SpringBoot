package com.gdmatstaffs.validation_demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class CurrentLoanDTO
{
    private final int id;
    private final int copyId;
    private final String bookTitle;
    private final String bookAuthor;
    private final LocalDate loanDate;
    private final LocalDate dueDate;
}
