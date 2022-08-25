package com.gdmatstaffs.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.TreeMap;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vowels
{
    private TreeMap<String, Integer> vowels;
    private TreeMap<String, Integer> allVowels;
}
