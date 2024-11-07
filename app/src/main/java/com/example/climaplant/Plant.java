package com.example.climaplant;

import java.io.Serializable;

public class Plant implements Serializable {
    private String name;
    private String description;
    private String location;

    public Plant(String name, String description, String location) {
        this.name = name;
        this.description = description;
        this.location = location;
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
}