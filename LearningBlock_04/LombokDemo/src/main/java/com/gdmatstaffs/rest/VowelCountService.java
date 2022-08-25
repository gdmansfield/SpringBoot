package com.gdmatstaffs.rest;

import com.gdmatstaffs.DTO.Vowels;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.TreeMap;
import java.util.stream.IntStream;

@Service
@AllArgsConstructor
public class VowelCountService
{
    private final VowelCountRepository vowelCountRepository;

    public Vowels countVowels(String sentence)
    {
        TreeMap<String, Integer> vowels = countVowelsInSentence(sentence);
        TreeMap<String, Integer> allVowels = createVowelMap();

        try
        {
            vowelCountRepository.updateVowels(vowels);
            vowelCountRepository.getAllVowels(allVowels);
        }
        catch (Exception e)
        {

        }

        return new Vowels(vowels, allVowels);
    }

    private TreeMap<String, Integer> countVowelsInSentence(String sentence)
    {
        TreeMap<String, Integer> vowels = createVowelMap();

        String uppercaseSentence = sentence.toUpperCase();
        IntStream
                .range(0, uppercaseSentence.length())
                .mapToObj(i -> "" + uppercaseSentence.charAt(i))
                .filter(s -> vowels.containsKey(s))
                .forEach(s -> vowels.replace(s, vowels.get(s) + 1));

        return vowels;
    }

        private TreeMap<String, Integer> createVowelMap()
        {
            TreeMap<String, Integer> vowels = new TreeMap<>();
            vowels.put("A", 0);
            vowels.put("E", 0);
            vowels.put("I", 0);
            vowels.put("O", 0);
            vowels.put("U", 0);
            return vowels;
        }
}
