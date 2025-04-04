package com.upn.deam_firsts_project.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.upn.deam_firsts_project.R;
import com.upn.deam_firsts_project.entities.contact;

import org.w3c.dom.Text;

import java.util.List;

public class basicAdapter  extends RecyclerView.Adapter<basicAdapter.ViewHolder> {
    private List<contact> data;

    public basicAdapter(List<contact> data) {
        this.data = data;
    }
    @NonNull
    @Override
    public basicAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_basic, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull basicAdapter.ViewHolder holder, int position) {
        contact contact = data.get(position);
        holder.tvText.setText(contact.getName());
        holder.tvText2.setText(contact.getPhone());
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvText;
        TextView tvText2;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvText = itemView.findViewById(R.id.tvText);
            tvText2 = itemView.findViewById(R.id.tvText2);
        }
    }
}
