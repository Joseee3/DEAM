package com.upn.deam_firsts_project.adapters;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.upn.deam_firsts_project.R;
import com.upn.deam_firsts_project.entities.color;

import java.util.List;

public class colorAdapter extends RecyclerView.Adapter<colorAdapter.ViewHolder> {
    private List<color> colorList;

    public colorAdapter(List<color> colorList) {
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
    color currentColor = colorList.get(position);
    holder.tvColorName.setText(currentColor.getName());
    holder.tvHexCode.setText(currentColor.getHexCode());

    // Cambiar el color del círculo usando setBackgroundTintList
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        holder.colorCircle.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(currentColor.getHexCode())));
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