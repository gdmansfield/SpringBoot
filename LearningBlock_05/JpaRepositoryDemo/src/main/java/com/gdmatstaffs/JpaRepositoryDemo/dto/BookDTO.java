package com.gdmatstaffs.JpaRepositoryDemo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class BookDTO
{
    private final int id;
    private final String title;
    private final String author;
    private final String isbn;
    private int numberOfCopies;
    private Collection<CopyDTO> copies;
}
