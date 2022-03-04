package org.izv.flora.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.izv.flora.MainActivity;
import org.izv.flora.R;
import org.izv.flora.model.entity.Flora;

import java.util.List;

public class FloraAdapter extends RecyclerView.Adapter<FloraViewHolder> {

    private Context context;
    private List<Flora> floraList;

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
        Flora flora = floraList.get(position);
        holder.flora = flora;
        MainActivity.id = floraList.get(position).getId();
        String url ="https://informatica.ieszaidinvergeles.org:10022/felixRDLFApp/public/api/imagen/" + flora.getId() + "/flora";
        holder.tvId.setText("Id: " + flora.getId());
        holder.tvNombre.setText(flora.getNombre());
        holder.tvFamilia.setText(flora.getFamilia());

        Glide.with(context).load(url).into(holder.ivFlora);

        Log.v("xyzyx", url);
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
