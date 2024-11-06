package com.example.climaplant;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Forecast implements Serializable {
    @SerializedName("list")
    private List<WeatherEntry> weatherEntries;

    public List<WeatherEntry> getWeatherEntries() {
        return weatherEntries;
    }

    public List<WeatherEntry> getWeatherEntriesForNextThreeDays() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime threeDaysLater = now.plus(3, ChronoUnit.DAYS);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        List<WeatherEntry> filteredEntries = new ArrayList<>();
        Set<LocalDate> addedDates = new HashSet<>();

        for (WeatherEntry entry : weatherEntries) {
            LocalDateTime entryTime = LocalDateTime.parse(entry.getDtTxt(), formatter);
            LocalDate entryDate = entryTime.toLocalDate();
            if (entryTime.isAfter(now) && entryTime.isBefore(threeDaysLater) && !addedDates.contains(entryDate)) {
                filteredEntries.add(entry);
                addedDates.add(entryDate);
                if (addedDates.size() == 3) {
                    break;
                }
            }
        }
        return filteredEntries;
    }
}

