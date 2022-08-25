package com.gdmatstaffs.demoJSON;

import java.time.LocalDate;

public class Person
{
    private String name;
    private String email;
    private LocalDate dob;

    public Person()
    {
        this.name = "??";
        this.email = "??";
        this.dob = LocalDate.now();
    }

    public String getName()
    {
        return name;
    }

    public String getEmail()
    {
        return email;
    }

    public LocalDate getDob()
    {
        return dob;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public void setDob(LocalDate dob)
    {
        this.dob = dob;
    }
}
