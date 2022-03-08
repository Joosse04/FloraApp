package org.izv.flora.model.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.izv.flora.MainActivity;
import org.izv.flora.R;
import org.izv.flora.model.entity.Flora;

import org.izv.flora.view.EditFloraActivity;

import java.util.List;

public class FloraAdapter extends RecyclerView.Adapter<FloraViewHolder> {
    private List<Flora> floraList;
    private Flora flora;
    private Context context;

    public FloraAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public FloraViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new FloraViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FloraViewHolder holder, int position) {
        flora = floraList.get(position);

        Picasso.get().load("https://informatica.ieszaidinvergeles.org:10022/felixRDLFApp/public/api/imagen/" + flora.getId()+"/flora").into(holder.ivFlora);

        holder.flora = flora;
        holder.tvId.setText("Id:" + flora.getId());
        holder.tvNombre.setText(flora.getNombre());
        holder.tvFamilia.setText(flora.getFamilia());

        holder.itemView.setOnClickListener(v -> {
            MainActivity.idFlora = floraList.get(position).getId();
            holder.flora = flora;
            Intent intent = new Intent(context, EditFloraActivity.class);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        if(floraList == null) {
            return 0;
        }
        return floraList.size();
    }

    public void setFloraList(List<Flora> floraList) {
        this.floraList = floraList;
        notifyDataSetChanged();
    }

}