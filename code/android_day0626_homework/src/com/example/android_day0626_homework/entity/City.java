package com.example.android_day0626_homework.entity;

import java.util.List;

public class City
{
    private String id;
    private String name;
    private List<District> districts;

    public City()
    {
        super();
    }

    public City(String id, String name, List<District> districts)
    {
        super();
        this.id = id;
        this.name = name;
        this.districts = districts;
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

    public List<District> getDistricts()
    {
        return districts;
    }

    public void setDistricts(List<District> districts)
    {
        this.districts = districts;
    }

    public String toString()
    {
        return name;
    }

}
