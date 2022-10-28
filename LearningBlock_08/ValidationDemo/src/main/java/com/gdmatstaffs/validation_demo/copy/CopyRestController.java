package com.gdmatstaffs.validation_demo.copy;

import com.gdmatstaffs.validation_demo.dto.CopyDTO;
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

    @PostMapping(path = "/{id}/return")
    public CopyDTO returnCopy(@PathVariable("id") int copyId)
    {
        return copyService.returnCopy(copyId);
    }
}
