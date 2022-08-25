package com.gdmatstaffs.CrudRepositoryDemo.copy;

import com.gdmatstaffs.CrudRepositoryDemo.dto.CopyDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/copy")
@AllArgsConstructor
public class CopyRestController
{
    private final CopyService copyService;

    @PostMapping(path = "/{id}/borrow")
    public CopyDTO borrowCopy(@PathVariable("id") int copyId)
    {
        return copyService.borrowCopy(copyId);
    }
}
