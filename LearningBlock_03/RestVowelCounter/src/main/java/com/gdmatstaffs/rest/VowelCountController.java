package com.gdmatstaffs.rest;

import com.gdmatstaffs.DTO.Vowels;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/rest/vowelCount")
public class VowelCountController
{
    private final VowelCountService vowelCountService;

    public VowelCountController(VowelCountService vowelCountService)
    {
        this.vowelCountService = vowelCountService;
    }

    @PostMapping
    public Vowels countVowels(@RequestBody String sentence)
    {
        return vowelCountService.countVowels(sentence);
    }
}
