package com.example.climaplant;

import java.io.Serializable;

public class Plant implements Serializable {
    private String name;
    private String description;

    public Plant(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}