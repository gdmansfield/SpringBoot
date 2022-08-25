package com.gdmatstaffs.CrudRepositoryDemo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@Getter
public class Copy
{
    @Id
    private final int id;
    private final int bookId;
    @Setter
    private int statusId;
}
