package com.gdmatstaffs.demoJSON;

public class MyB
{
    int id;
    private transient MyA a;

    public MyB(int id)
    {
        this.id = id;
    }

    public MyA getA()
    {
        return a;
    }

    public int getId()
    {
        return id;
    }

    public void setA(MyA a)
    {
        this.a = a;
    }

    public void setId(int id)
    {
        this.id = id;
    }
}
