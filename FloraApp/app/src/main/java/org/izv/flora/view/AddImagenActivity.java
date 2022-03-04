package org.izv.flora.view;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.izv.flora.MainActivity;
import org.izv.flora.R;
import org.izv.flora.model.entity.Flora;
import org.izv.flora.model.entity.Imagen;
import org.izv.flora.viewmodel.AddImagenViewModel;
import org.izv.flora.viewmodel.MainActivityViewModel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class AddImagenActivity extends AppCompatActivity {

    private ActivityResultLauncher<Intent> launcher;
    private Intent resultadoImagen = null;
    private Bitmap btpImg = null;
    private InputStream inStream;
    private MainActivityViewModel mavm;
    private AddImagenViewModel aivm;
    private ImageView btSeleccionaImagen;
    private EditText etNombre2, etDescripcion2;
    private TextView tvNombreFlora;
    private Button btAñadeImagen, btCancelar;

    private int pos;
    private long id ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_imagen);
        initialize();
    }

    private void initialize() {
        launcher = getLauncher();

        pos = 0;
        id = 1;

        tvNombreFlora = findViewById(R.id.tvNombreFlora);

        etNombre2 = findViewById(R.id.etNombre2);
        etDescripcion2 = findViewById(R.id.etDescripcion2);

        mavm = new ViewModelProvider(this).get(MainActivityViewModel.class); // Obtengo el viewmodel de la actividad
        mavm.getFlora(); // Llama al servicio REST, la encola y devuelve la lista de la base de datos

        MutableLiveData<ArrayList<Flora>> floraList = mavm.getFloraLiveData();
        floraList.observe(this,  floras -> { // Cuando se llene la lista veremos los cambios
            if(MainActivity.dondepasa==1){
                tvNombreFlora.setText(floras.get(mavm.getFloraLiveData().getValue().size()-1).getNombre());
                id= floras.get(mavm.getFloraLiveData().getValue().size()-1).getId();
            }else if(MainActivity.dondepasa==2){
                tvNombreFlora.setText(MainActivity.nombre);
                id = MainActivity.id;
            }
        });

        btSeleccionaImagen = findViewById(R.id.btSeleccionaImagen);
        btSeleccionaImagen.setOnClickListener(v -> {
            selectImage();
        });

        btAñadeImagen = findViewById(R.id.btAñadir2);
        btAñadeImagen.setOnClickListener(v -> {
            uploadDataImage();
            Intent refresh = new Intent(AddImagenActivity.this, MainActivity.class);
            startActivity(refresh);
        });

        btCancelar = findViewById(R.id.btCancelar2);
        btCancelar.setOnClickListener(v-> {
            finish();
        });

        aivm = new ViewModelProvider(this).get(AddImagenViewModel.class);
    }

    private void uploadDataImage() {
        String nombre = etNombre2.getText().toString();
        String descripcion = etDescripcion2.getText().toString();

        if(!(id < 0 || nombre.trim().isEmpty() || resultadoImagen == null)) {
            Imagen imagen = new Imagen();
            imagen.nombre = nombre;
            imagen.descripcion = descripcion;
            imagen.idflora = id;
            aivm.saveImagen(resultadoImagen, imagen);
        }
    }

    ActivityResultLauncher<Intent> getLauncher() {
        return registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    // Respuesta al resultado de haber seleccionado una imagen
                    if(result.getResultCode() == Activity.RESULT_OK) {
                        // copyData(result.getData());
                        resultadoImagen = result.getData();
                        try {
                            inStream = getContentResolver().openInputStream(resultadoImagen.getData());
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        btpImg = BitmapFactory.decodeStream(inStream);
                        resultadoImagen.toString();
                        try {
                            inStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        btSeleccionaImagen.setImageBitmap(btpImg);
                    }
                }
        );
    }

    Intent getContentIntent() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        return intent;
    }

    void selectImage() {
        Intent intent = getContentIntent();
        launcher.launch(intent);
    }
}