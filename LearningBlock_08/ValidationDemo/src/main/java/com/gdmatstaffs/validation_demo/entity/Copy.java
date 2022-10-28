package com.gdmatstaffs.validation_demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="copy")
public class Copy
{
    @Id
    private int id;
    
    @ManyToOne
    @JoinColumn(name="book_id", nullable=false)
    private Book book;

    @ManyToOne
    @JoinColumn(name="status_id", nullable=false)
    private CopyStatus status;
}
