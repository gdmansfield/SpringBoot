package com.gdmatstaffs.CrudRepositoryDemo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;

import java.util.Collection;

@AllArgsConstructor
@Getter
public class Book
{
    @Id
    private final int id;
    private final String title;
    private final String author;
    private final String isbn;
}
