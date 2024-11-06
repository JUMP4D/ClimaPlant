package com.example.climaplant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.climaplant.databinding.ActivityAddPlantBinding;

public class AddPlantActivity extends AppCompatActivity {

    private ActivityAddPlantBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddPlantBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding.textEditNom.getText().toString();
                String description = binding.textEditDesc.getText().toString();
                Plant plant = new Plant(name, description);
                Intent resultIntent = new Intent();
                resultIntent.putExtra("plant", plant);
                setResult(1, resultIntent);
                finish();
            }
        });
    }
}