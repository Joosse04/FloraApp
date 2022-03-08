package org.izv.flora;

import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.izv.flora.model.adapter.FloraAdapter;
import org.izv.flora.view.AddFloraActivity;

import org.izv.flora.viewmodel.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {

    private FloraAdapter floraAdapter;
    private RecyclerView rvFlora;
    private FloatingActionButton fabAddFlora;
    public static long idFlora;
    public static int aqui;
    public static String nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    private void initialize() {
        fabAddFlora = findViewById(R.id.fabAddFlora);
        fabAddFlora.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, AddFloraActivity.class));
        });

        rvFlora = findViewById(R.id.rvFlora);
        rvFlora.setLayoutManager(new LinearLayoutManager(this));

        floraAdapter = new FloraAdapter(this);

        aqui = 0;
        nombre = "";

        MainActivityViewModel mavm = new ViewModelProvider(this).get(MainActivityViewModel.class);
        mavm.getFlora();

        mavm.getFloraLiveData().observe(this, floraPlural -> {
            Log.v("xyzyx", floraPlural.toString());
            floraAdapter.setFloraList(mavm.getFloraLiveData().getValue());
            rvFlora.setAdapter(floraAdapter);
        });
        mavm.getFlora();
    }

}