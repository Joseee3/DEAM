package com.upn.deam_firsts_project.adapters;

import android.content.res.ColorStateList;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.upn.deam_firsts_project.R;
import com.upn.deam_firsts_project.entities.Color;

import java.util.List;

public class ColorAdapter extends RecyclerView.Adapter<ColorAdapter.ViewHolder> {
    private List<Color> colorList;

    public ColorAdapter(List<Color> colorList) {
        this.colorList = colorList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_color, parent, false);
        return new ViewHolder(view);
    }

@Override
public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    Color color = colorList.get(position);
    holder.tvColorName.setText(color.getName());
    holder.tvHexCode.setText(color.getHexCode());


//    try{
//        String hex = "#" + color.getHexCode();
//        holder.colorCircle.setBackgroundColor(android.graphics.Color.parseColor(hex));
//    }catch (IllegalArgumentException e){
//        Log.d("ColorAdapter", "Invalid color code: " + color.getHexCode());
//    }

    try{
        String hex = "#" + color.getHexCode();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            holder.colorCircle.setBackgroundTintList(ColorStateList.valueOf(android.graphics.Color.parseColor(hex)));
        } else {
            holder.colorCircle.setBackgroundColor(android.graphics.Color.parseColor(hex));

        }}catch (IllegalArgumentException e){
        Log.d("ColorAdapter", "Invalid color code: " + color.getHexCode());
    }

    }
    @Override
    public int getItemCount() {
        return colorList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvColorName, tvHexCode;
        View colorCircle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvColorName = itemView.findViewById(R.id.tvColorName);
            tvHexCode = itemView.findViewById(R.id.tvHexCode);
            colorCircle = itemView.findViewById(R.id.colorCircle);
        }
    }
}