package com.example.climaplant;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Weather implements Serializable {
    @SerializedName("description")
    private String description;

    public String getDescription() {
        return description;
    }

}