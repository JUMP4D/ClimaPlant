package com.example.climaplant;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextClock;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PlantAdapter extends RecyclerView.Adapter<PlantAdapter.PlantViewHolder> {

    private List<Plant> plantList;
    private OnPlantClickListener listener;

    public PlantAdapter(List<Plant> plantList, OnPlantClickListener listener) {
        this.plantList = plantList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PlantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_plant, parent, false);
        return new PlantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlantViewHolder holder, int position) {
        Plant plant = plantList.get(position);
        holder.bind(plant, listener);
    }

    @Override
    public int getItemCount() {
        return plantList.size();
    }

    public static class PlantViewHolder extends RecyclerView.ViewHolder {
        TextView plantName;
        TextView plantDescription;
        TextView plantLocation;

        public PlantViewHolder(@NonNull View itemView) {
            super(itemView);
            plantName = itemView.findViewById(R.id.plant_name);
            plantDescription = itemView.findViewById(R.id.plant_description);
            plantLocation = itemView.findViewById(R.id.plant_location);
        }

        public void bind(final Plant plant, final OnPlantClickListener listener) {
            plantName.setText(plant.getName());
            plantDescription.setText(plant.getDescription());
            plantLocation.setText("\uD83D\uDCCD " + plant.getLocation());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onPlantClick(plant);
                }
            });
        }
    }
}