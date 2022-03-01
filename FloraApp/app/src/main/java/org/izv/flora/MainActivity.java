package org.izv.flora;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.izv.flora.adapter.FloraAdapter;
import org.izv.flora.model.api.FloraClient;
import org.izv.flora.view.AddFloraActivity;
import org.izv.flora.view.AddImagenActivity;
import org.izv.flora.viewmodel.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {

    private FloraClient floraClient;
    private FloatingActionButton fabAddFlora, fabAddImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    private void initialize() {
        fabAddFlora = findViewById(R.id.fabAddFlora);
        fabAddFlora.setOnClickListener(v -> openAddFloraActivity());

        fabAddImage = findViewById(R.id.fabAddImage);
        fabAddImage.setOnClickListener(v -> openAddImagenActivity());

        RecyclerView rvFlora = findViewById(R.id.rvFlora);
        rvFlora.setLayoutManager(new LinearLayoutManager(this));

        FloraAdapter floraAdapter = new FloraAdapter(this);
        floraAdapter.notifyDataSetChanged();
        rvFlora.setAdapter(floraAdapter);

        MainActivityViewModel mavm = new ViewModelProvider(this).get(MainActivityViewModel.class);
        mavm.getFloraLiveData().observe(this, floraPlural -> {
            Log.v("xyzyx", floraPlural.toString());
            //recyclerview
            floraAdapter.setFloraList(floraPlural);
        });
        mavm.getFlora();
    }

    private void openAddImagenActivity() {
        Intent intent = new Intent(this, AddImagenActivity.class);
        startActivity(intent);
    }

    private void openAddFloraActivity() {
        Intent intent = new Intent(this, AddFloraActivity.class);
        startActivity(intent);
    }
}