package com.example.climaplant;

import java.io.Serializable;

public class Plant implements Serializable {
    private String name;
    private String location;
    private String Arrosage;

    public Plant(String name, String location, String arrosage) {
        this.name = name;
        this.location = location;
        this.Arrosage = arrosage;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getArrosage() {
        return Arrosage;
    }
}