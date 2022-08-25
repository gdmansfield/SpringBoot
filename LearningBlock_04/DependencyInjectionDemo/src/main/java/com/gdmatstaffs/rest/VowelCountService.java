package com.gdmatstaffs.rest;

import com.gdmatstaffs.DTO.Vowels;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.TreeMap;
import java.util.stream.IntStream;

@Service
@AllArgsConstructor
public class VowelCountService
{

    private final ApplicationContext context;
    private final VowelCountRepository vowelCountRepository;

    public Vowels countVowels(String sentence)
    {
        TreeMap<String, Integer> vowels = countVowelsInSentence(sentence);
        TreeMap<String, Integer> allVowels = (TreeMap<String, Integer>)context.getBean("vowelMap");

        try
        {
            vowelCountRepository.updateVowels(vowels);
            allVowels = vowelCountRepository.getAllVowels();
        }
        catch (Exception e)
        {
            e.printStackTrace(System.out);
        }

        return new Vowels(vowels, allVowels);
    }

    private TreeMap<String, Integer> countVowelsInSentence(String sentence)
    {
        TreeMap<String, Integer> vowels = (TreeMap<String, Integer>)context.getBean("vowelMap");

        String uppercaseSentence = sentence.toUpperCase();
        IntStream
                .range(0, uppercaseSentence.length())
                .mapToObj(i -> "" + uppercaseSentence.charAt(i))
                .filter(s -> vowels.containsKey(s))
                .forEach(s -> vowels.replace(s, vowels.get(s) + 1));

        return vowels;
    }
}
