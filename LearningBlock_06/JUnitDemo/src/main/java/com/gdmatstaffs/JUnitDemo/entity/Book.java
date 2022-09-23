package com.gdmatstaffs.JUnitDemo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="book")
public class Book
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String author;
    private String isbn;

    @OneToMany(mappedBy="book")
    @OrderBy(value="id")
    private Collection<Copy> copies;

    public int getCopyCount()
    {
        return copies.size();
    }
}
