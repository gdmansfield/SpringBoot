package com.gdmatstaffs.rest;

import com.gdmatstaffs.DTO.Vowels;
import org.springframework.web.bind.annotation.*;

import java.util.TreeMap;
import java.util.stream.IntStream;

@RestController
@RequestMapping(path = "/rest/vowelCount")
public class VowelCounter
{
    @PostMapping
    public Vowels countVowels(@RequestBody String sentence)
    {
        TreeMap<String, Integer> vowels = new TreeMap<>();
        vowels.put("A", 0);
        vowels.put("E", 0);
        vowels.put("I", 0);
        vowels.put("O", 0);
        vowels.put("U", 0);

        String uppercaseSentence = sentence.toUpperCase();
        IntStream
                .range(0, uppercaseSentence.length())
                .mapToObj(i -> "" + uppercaseSentence.charAt(i))
                .filter(s -> vowels.containsKey(s))
                .forEach(s -> vowels.replace(s, vowels.get(s) + 1));

        return new Vowels(vowels);
    }
}
