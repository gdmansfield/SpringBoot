package com.gdmatstaffs.validation_demo.loan;

import com.gdmatstaffs.validation_demo.dto.CurrentLoanDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping(path = "/loan")
@AllArgsConstructor
public class LoanRestController
{
    private final LoanService loanService;

    @GetMapping(path = "/current")
    public Collection<CurrentLoanDTO> getAllCurrentLoans()
    {
        return loanService.getAllCurrentLoans();
    }
}
