package com.example.climaplant;

import java.io.Serializable;

public class Plant implements Serializable {
    private String name;
    private String description;
    private String location;
    private String Arrosage;

    public Plant(String name, String description, String location, String arrosage) {
        this.name = name;
        this.description = description;
        this.location = location;
        this.Arrosage = arrosage;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {

        return description;
    }

    public String getLocation() {
        return location;
    }

    public String getArrosage() {
        return Arrosage;
    }
}