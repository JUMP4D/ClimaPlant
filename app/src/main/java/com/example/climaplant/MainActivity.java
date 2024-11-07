package com.example.climaplant;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.climaplant.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnPlantClickListener{

    private ActivityMainBinding binding;
    private PlantAdapter plantAdapter;
    private List<Plant> plantList;
    private ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new
            ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == 1){
                        Intent resultIntent = result.getData();
                        Plant plant = (Plant) resultIntent.getSerializableExtra("plant");
                        plantList.add(plant);
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        plantList = new ArrayList<>();
        plantAdapter = new PlantAdapter(plantList, this);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(plantAdapter);

        binding.addPlantButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getApplicationContext(), AddPlantActivity.class);
                activityResultLauncher.launch(myIntent);
            }
        });

        binding.titleTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.truffaut.com/plantes/idees-et-conseils.html";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });
    }
    @Override
    public void onPlantClick(Plant plant) {
        // Handle the plant click event
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("plant", plant);
        startActivity(intent);
    }
}