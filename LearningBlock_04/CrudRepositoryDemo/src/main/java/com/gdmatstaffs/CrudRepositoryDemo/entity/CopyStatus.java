package com.gdmatstaffs.CrudRepositoryDemo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@Getter
public class CopyStatus
{
    @Id
    private final int id;
    private final String status;
}
