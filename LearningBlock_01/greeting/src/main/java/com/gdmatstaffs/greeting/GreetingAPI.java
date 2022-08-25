package com.gdmatstaffs.greeting;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/greetings")
public class GreetingAPI
{
    @GetMapping
    public String helloWorld()
    {
        return "Hello world!";
    }

    @GetMapping(path = "/{personName}")
    public String greetPerson(@PathVariable("personName") String name)
    {
        return "Hello, " + name;
    }

    @PostMapping
    public String greetPostedPerson(@RequestBody String name)
    {
        return "Hello, " + name;
    }
}
