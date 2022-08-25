package com.gdmatstaffs.DTO;

import java.util.TreeMap;

public class VowelCountResult
{
    private String sentence;
    private TreeMap<String, Integer> vowels;

    public VowelCountResult()
    {
        this.sentence = "Blah blah blah";
        this.vowels = new TreeMap<>();
    }

    public VowelCountResult(String sentence, TreeMap<String, Integer> vowels)
    {
        this.sentence = sentence;
        this.vowels = vowels;
    }

    public String getSentence()
    {
        return sentence;
    }

    public TreeMap<String, Integer> getVowels()
    {
        return vowels;
    }

    public void setSentence(String sentence)
    {
        this.sentence = sentence;
    }
}
