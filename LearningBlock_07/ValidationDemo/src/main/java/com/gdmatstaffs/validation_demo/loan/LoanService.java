package com.gdmatstaffs.validation_demo.loan;

import com.gdmatstaffs.validation_demo.dto.CurrentLoanDTO;
import com.gdmatstaffs.validation_demo.dto.DTO_Factory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LoanService
{
    private final LoanRepository loanRepository;
    private final DTO_Factory dto_factory;

    @Transactional(readOnly=true)
    public Collection<CurrentLoanDTO> getAllCurrentLoans()
    {
        return
                loanRepository
                        .streamAllByReturnDateIsNull()
                        .map(loan -> dto_factory.createDTO(loan))
                        .collect(Collectors.toCollection(ArrayList::new));
    }
}
