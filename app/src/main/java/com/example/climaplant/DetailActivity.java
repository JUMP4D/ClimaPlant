package com.example.climaplant;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import com.example.climaplant.databinding.ActivityDetailBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    private ActivityDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        Plant plant = (Plant) intent.getSerializableExtra("plant");

        binding.nom.setText(plant.getName());

        binding.titleMeteo.setText("Prévisions météo " + plant.getLocation());

        binding.titlearrosage.setText("Arrosage " + plant.getArrosage());

        fetchWeatherData(plant.getName());

        String cityName = plant.getLocation();
        fetchWeatherData(cityName);

        binding.titleTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://fr.wikipedia.org/wiki/" + plant.getName();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });
    }

    private void fetchWeatherData(String cityName) {
        OpenWeatherServices service = RetrofitClientInstance.getRetrofitInstance().create(OpenWeatherServices.class);
        Call<Forecast> call = service.getForcast(cityName);

        call.enqueue(new Callback<Forecast>() {
            @Override
            public void onResponse(Call<Forecast> call, Response<Forecast> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Forecast forecast = response.body();
                    List<WeatherEntry> weatherEntries = forecast.getWeatherEntriesForNextThreeDays();

                    if (weatherEntries != null && !weatherEntries.isEmpty()) {
                        StringBuilder weatherDescription = new StringBuilder();
                        boolean needsWatering = true;

                        for (WeatherEntry entry : weatherEntries) {
                            String date = entry.getDtTxt().split(" ")[0]; // Extract the date part
                            String description = entry.getWeatherDescription();
                            boolean isRainy = description.toLowerCase().contains("pluie") || description.toLowerCase().contains("averse");

                            weatherDescription.append(date)
                                    .append(" - ")
                                    .append(description)
                                    .append("\n");

                            if (isRainy) {
                                needsWatering = false;
                            }
                        }

                        weatherDescription.append("\nLa plante ");
                        if (needsWatering) {
                            weatherDescription.append("doit être arrosée.");
                            binding.weatherImageView.setImageResource(R.drawable.sun);
                        } else {
                            weatherDescription.append("n'a pas besoin d'être arrosée.");
                            binding.weatherImageView.setImageResource(R.drawable.drop);
                        }

                        binding.meteo.setText(weatherDescription.toString().trim()); // Ensure the TextView is correctly referenced
                    } else {
                        binding.meteo.setText("Aucune prévision météo disponible");
                    }
                } else {
                    binding.meteo.setText("Erreur de réponse du serveur");
                }
            }

            @Override
            public void onFailure(Call<Forecast> call, Throwable t) {
                binding.meteo.setText("Échec de la récupération des données météo : " + t.getMessage());
            }
        });
    }
}