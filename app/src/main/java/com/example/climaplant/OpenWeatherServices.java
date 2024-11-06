package com.example.climaplant;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OpenWeatherServices {
    @GET("forecast?appid=1081b814bd7b88ca17138e2de3e7e0ff&lang=fr&units=metric")
    Call<Forecast> getForcast(@Query("q") String city);
}