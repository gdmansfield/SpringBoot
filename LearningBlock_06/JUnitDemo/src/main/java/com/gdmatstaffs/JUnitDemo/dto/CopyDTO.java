package com.gdmatstaffs.JUnitDemo.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class CopyDTO
{
    private final int id;
    private transient final BookDTO book;
    private String status;
}
