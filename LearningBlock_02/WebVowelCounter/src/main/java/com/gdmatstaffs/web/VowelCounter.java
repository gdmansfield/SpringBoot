package com.gdmatstaffs.web;

import com.gdmatstaffs.DTO.Sentence;
import com.gdmatstaffs.DTO.VowelCountResult;
import com.gdmatstaffs.DTO.Vowels;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class VowelCounter
{
    @GetMapping(path = "/web/vowelCount")
    public String countVowels(Model model)
    {
        model.addAttribute("sentence", new Sentence());
        return "index";
    }

    @PostMapping(path = "/web/vowelCount")
    public String countVowels(@ModelAttribute Sentence sentence, Model model)
    {
        Vowels vowels =
                new RestTemplateBuilder()
                        .build()
                        .postForObject(
                                "http://localhost:8080/rest/vowelCount",
                                sentence.getText(),
                                Vowels.class);

        model.addAttribute(
                "vowelCountResult",
                new VowelCountResult(sentence.getText(), vowels.getVowels()));

        return "result";
    }
}
