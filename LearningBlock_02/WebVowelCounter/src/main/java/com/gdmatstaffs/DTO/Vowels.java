package com.gdmatstaffs.DTO;

import java.util.TreeMap;

public class Vowels
{

    private TreeMap<String, Integer> vowels;

    public Vowels()
    {
        vowels = new TreeMap<>();
    }

    public Vowels(TreeMap<String, Integer> vowels)
    {
        this.vowels = vowels;
    }

    public TreeMap<String, Integer> getVowels()
    {
        return vowels;
    }

    public void setVowels(TreeMap<String, Integer> vowels)
    {
        this.vowels = vowels;
    }
}
