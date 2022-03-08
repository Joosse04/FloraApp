package org.izv.flora.model.adapter;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.izv.flora.MainActivity;
import org.izv.flora.view.EditFloraActivity;
import org.izv.flora.R;
import org.izv.flora.model.entity.Flora;

public class FloraViewHolder extends RecyclerView.ViewHolder {

    public Flora flora;
    public TextView tvId, tvNombre, tvFamilia;
    public ImageView ivFlora;

    public FloraViewHolder(@NonNull View itemView) {
        super(itemView);
        tvId = itemView.findViewById(R.id.tvId);
        tvNombre = itemView.findViewById(R.id.tvNombre);
        tvFamilia = itemView.findViewById(R.id.tvFamilia);
        ivFlora= itemView.findViewById(R.id.ivFlora);
    }

}
