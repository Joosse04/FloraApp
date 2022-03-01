package org.izv.flora.adapter;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.izv.flora.view.EditFloraActivity;
import org.izv.flora.R;
import org.izv.flora.model.entity.Flora;

public class FloraViewHolder extends RecyclerView.ViewHolder {

    public Flora flora;
    public TextView tvIdFlora, tvNombreFlora, tvFamiliaFlora;
    public ImageView ivFlora;

    public FloraViewHolder(@NonNull View itemView) {
        super(itemView);
        tvIdFlora = itemView.findViewById(R.id.tvIdFlora);
        tvNombreFlora = itemView.findViewById(R.id.tvNombreFlora);
        tvFamiliaFlora = itemView.findViewById(R.id.tvFamiliaFlora);
        ivFlora= itemView.findViewById(R.id.ivFlora);

        itemView.setOnClickListener(v -> {
            Intent intent = new Intent(itemView.getContext(), EditFloraActivity.class);
            intent.putExtra("flora", flora);
            itemView.getContext().startActivity(intent);
        });
    }

}
