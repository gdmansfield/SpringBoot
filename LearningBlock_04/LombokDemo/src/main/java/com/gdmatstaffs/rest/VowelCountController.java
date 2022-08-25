package com.gdmatstaffs.rest;

import com.gdmatstaffs.DTO.Vowels;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/rest/vowelCount")
@AllArgsConstructor
public class VowelCountController
{
    private final VowelCountService vowelCountService;

    @PostMapping
    public Vowels countVowels(@RequestBody String sentence)
    {
        return vowelCountService.countVowels(sentence);
    }
}
