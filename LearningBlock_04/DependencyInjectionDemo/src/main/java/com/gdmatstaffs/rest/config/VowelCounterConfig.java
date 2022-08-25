package com.gdmatstaffs.rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.TreeMap;

@Configuration
public class VowelCounterConfig
{

    @Bean
    @Scope("prototype")
    public TreeMap<String, Integer> vowelMap()
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
