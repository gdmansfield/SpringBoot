package com.gdmatstaffs.validation_demo.copy;

import com.gdmatstaffs.validation_demo.copy_status.CopyStatusService;
import com.gdmatstaffs.validation_demo.dto.CopyDTO;
import com.gdmatstaffs.validation_demo.dto.DTO_Factory;
import com.gdmatstaffs.validation_demo.entity.Copy;
import com.gdmatstaffs.validation_demo.entity.CopyStatus;
import com.gdmatstaffs.validation_demo.entity.Loan;
import com.gdmatstaffs.validation_demo.loan.LoanRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class CopyService
{
    private final CopyRepository copyRepository;
    private final LoanRepository loanRepository;
    private final CopyStatusService copyStatusService;
    private final DTO_Factory dto_factory;

    public CopyDTO borrowCopy(int copyId)
    {
        CopyStatus availableId = copyStatusService.getByStatus("Available");
        CopyStatus onLoanId = copyStatusService.getByStatus("On loan");
        Copy copy = copyRepository.findById(copyId).orElse(null);

        if (copy != null && copy.getStatus().equals(availableId))
        {
            copy.setStatus(onLoanId);
            copy = copyRepository.save(copy);

            Loan loan = createLoanForCopy(copy);
            loanRepository.save(loan);

            return dto_factory.createDTO(copy);
        }

        return null;
    }

    private Loan createLoanForCopy(Copy copy)
    {
        LocalDate loanDate = LocalDate.now();
        LocalDate dueDate = LocalDate.now().plusDays(14);
        return
                new Loan(
                        0,
                        copy,
                        loanDate,
                        dueDate,
                        null);
    }

    public CopyDTO returnCopy(int copyId)
    {
        CopyStatus availableId = copyStatusService.getByStatus("Available");
        CopyStatus onLoanId = copyStatusService.getByStatus("On loan");
        Copy copy = copyRepository.findById(copyId).orElse(null);

        if (copy != null && copy.getStatus().equals(onLoanId))
        {
            copy.setStatus(availableId);
            copy = copyRepository.save(copy);

            Loan loan = loanRepository.findByCopyIdAndReturnDateIsNull(copy.getId());
            loan.setReturnDate(LocalDate.now());
            loanRepository.save(loan);

            return dto_factory.createDTO(copy);
        }

        return null;
    }
}
