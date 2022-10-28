package com.gdmatstaffs.validation_demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="loan")
public class Loan
{
    @Id
    private int id;

    @ManyToOne
    @JoinColumn(name="copy_id", nullable=false)
    private Copy copy;

    private LocalDate loanDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
}
