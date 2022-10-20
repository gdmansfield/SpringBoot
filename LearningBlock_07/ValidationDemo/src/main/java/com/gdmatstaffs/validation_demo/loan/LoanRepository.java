package com.gdmatstaffs.validation_demo.loan;

import com.gdmatstaffs.validation_demo.entity.Loan;
import org.springframework.data.repository.CrudRepository;

import java.util.stream.Stream;

public interface LoanRepository extends CrudRepository<Loan, Integer>
{
    Loan findByCopyIdAndReturnDateIsNull(int copyId);
    Stream<Loan> streamAllByReturnDateIsNull();
}
