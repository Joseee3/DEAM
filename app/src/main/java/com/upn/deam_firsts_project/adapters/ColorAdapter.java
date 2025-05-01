package com.upn.deam_firsts_project.adapters;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.upn.deam_firsts_project.FormColorActivity;
import com.upn.deam_firsts_project.R;
import com.upn.deam_firsts_project.entities.Color;

import java.util.List;

public class ColorAdapter extends RecyclerView.Adapter<ColorAdapter.ColorViewHolder> {

    private List<Color> data;
    private Activity activity;
    public ColorAdapter(List<Color> data, Activity activity) {

        this.data = data;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ColorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_color, parent, false);

        return new ColorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ColorViewHolder holder, int position) {
        Color color = data.get(position);

        TextView tvColorName = holder.itemView.findViewById(R.id.tvColorName);
        TextView tvColorHex = holder.itemView.findViewById(R.id.tvHexCode);
        View vColorBg = holder.itemView.findViewById(R.id.colorCircle);

        tvColorName.setText(color.name);

        holder.itemView.setOnClickListener(v -> {
            Toast.makeText(v.getContext(), "Color: " + color.name, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(v.getContext(), FormColorActivity.class);
            intent.putExtra("colorId", color.id);
            intent.putExtra("colorName", color.name);
            intent.putExtra("colorHex", color.hexCode);
            activity.startActivityForResult(intent, 123);
        });

        try {
            String hex = color.hexCode;
            tvColorHex.setText(hex);
            vColorBg.setBackgroundColor(android.graphics.Color.parseColor(hex));
        } catch(Exception ex) {
            Log.d("MAIN_APP", "Usando color por defecto");
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ColorViewHolder extends  RecyclerView.ViewHolder {

        public ColorViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
