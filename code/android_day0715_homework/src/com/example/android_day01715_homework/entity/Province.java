package com.example.android_day01715_homework.entity;

import java.util.List;

public class Province
{
    private String id;
    private String name;
    private List<City> cities;

    public Province()
    {
        super();
    }

    public Province(String id, String name, List<City> cities)
    {
        super();
        this.id = id;
        this.name = name;
        this.cities = cities;
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

    public List<City> getCities()
    {
        return cities;
    }

    public void setCities(List<City> cities)
    {
        this.cities = cities;
    }

    public String toString()
    {
        return name;
    }

}
