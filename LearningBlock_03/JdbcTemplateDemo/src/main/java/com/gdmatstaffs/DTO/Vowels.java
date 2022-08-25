package com.gdmatstaffs.DTO;

import java.util.TreeMap;

public class Vowels
{

    private TreeMap<String, Integer> vowels;
    private TreeMap<String, Integer> allVowels;

    public Vowels()
    {
        vowels = new TreeMap<>();
        allVowels = new TreeMap<>();
    }

    public Vowels(TreeMap<String, Integer> vowels, TreeMap<String, Integer> allVowels)
    {
        this.vowels = vowels;
        this.allVowels = allVowels;
    }


    public TreeMap<String, Integer> getAllVowels()
    {
        return allVowels;
    }

    public TreeMap<String, Integer> getVowels()
    {
        return vowels;
    }

    public void setAllVowels(TreeMap<String, Integer> allVowels)
    {
        this.allVowels = allVowels;
    }

    public void setVowels(TreeMap<String, Integer> vowels)
    {
        this.vowels = vowels;
    }
}
