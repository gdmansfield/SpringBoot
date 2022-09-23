package com.gdmatstaffs.JUnitDemo.copy;

import com.gdmatstaffs.JUnitDemo.copy_status.CopyStatusService;
import com.gdmatstaffs.JUnitDemo.dto.CopyDTO;
import com.gdmatstaffs.JUnitDemo.dto.DTO_Factory;
import com.gdmatstaffs.JUnitDemo.entity.Copy;
import com.gdmatstaffs.JUnitDemo.entity.CopyStatus;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CopyService
{
    private final CopyRepository copyRepository;
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

            return dto_factory.createDTO(copy);
        }

        return null;
    }
}
