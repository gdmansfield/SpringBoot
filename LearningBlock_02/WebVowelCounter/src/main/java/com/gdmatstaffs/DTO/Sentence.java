package com.gdmatstaffs.DTO;

public class Sentence
{
    private String text;

    public Sentence()
    {
        text = "";
    }

    public Sentence(String text)
    {
        this.text = text;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }
}
