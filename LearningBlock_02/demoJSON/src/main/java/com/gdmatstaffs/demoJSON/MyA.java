package com.gdmatstaffs.demoJSON;

public class MyA
{
    private int id;
    private MyB b;

    public MyA(int id)
    {
        this.id = id;
    }

    public MyB getB()
    {
        return b;
    }

    public int getId()
    {
        return id;
    }

    public void setB(MyB b)
    {
        this.b = b;
        b.setA(this);
    }

    public void setId(int id)
    {
        this.id = id;
    }
}
