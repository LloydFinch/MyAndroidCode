package com.example.android_day01715_homework.entity;


public class District
{
    private String id;
    private String name;
    public District()
    {
        super();
    }
    public District(String id, String name)
    {
        super();
        this.id = id;
        this.name = name;
    }
    public String getId()
    {
        return id;
    }
    public void setId(String id)
    {
        this.id = id;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public String toString()
    {
        return  name;
    }
    
}
