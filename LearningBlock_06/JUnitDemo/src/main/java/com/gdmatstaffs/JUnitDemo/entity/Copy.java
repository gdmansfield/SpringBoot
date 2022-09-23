package com.gdmatstaffs.JUnitDemo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="copy")
public class Copy
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="book_id", nullable=false)
    private Book book;

    @ManyToOne
    @JoinColumn(name="status_id", nullable=false)
    private CopyStatus status;
}
