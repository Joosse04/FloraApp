package org.izv.flora.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

import org.izv.flora.MainActivity;
import org.izv.flora.R;
import org.izv.flora.model.entity.Flora;

import org.izv.flora.viewmodel.DeleteFloraViewModel;
import org.izv.flora.viewmodel.EditFloraViewModel;
import org.izv.flora.viewmodel.MainActivityViewModel;

import java.util.ArrayList;

public class EditFloraActivity extends AppCompatActivity {

    private MainActivityViewModel mavm;
    private EditFloraViewModel evm;
    private DeleteFloraViewModel dfvm;

    private EditText etNombre, etFamilia, etIdentificacion, etAltitud, etHabitat, etFitosociologia,
            etBiotipo, etBiologiaReproductiva, etFloracion, etFructificacion, etExpresionSexual,
            etPolinizacion, etDispersion, etNumeroCromosomatico, etReproduccionAsexual,
            etDistribucion, etBiologia, etDemografia, etAmenazas, etMedidasPropuestas;
    private TextInputLayout tilNombre;
    private Button btEditar, btBorrar, btCancelar;
    private int pos;

    private AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_flora);
        initialize();
    }

    private void initialize() {
        etNombre = findViewById(R.id.etNombre);
        etFamilia = findViewById(R.id.etFamilia);
        etIdentificacion = findViewById(R.id.etIdentificacion);
        etAltitud = findViewById(R.id.etAltitud);
        etHabitat = findViewById(R.id.etHabitat);
        etFitosociologia = findViewById(R.id.etFitosociologia);
        etBiotipo = findViewById(R.id.etBiotipo);
        etBiologiaReproductiva = findViewById(R.id.etBiologiaReproductiva);
        etFloracion = findViewById(R.id.etFloracion);
        etFructificacion = findViewById(R.id.etFructificacion);
        etExpresionSexual = findViewById(R.id.etExpresionSexual);
        etPolinizacion = findViewById(R.id.etPolinizacion);
        etDispersion = findViewById(R.id.etDispersion);
        etNumeroCromosomatico = findViewById(R.id.etNumeroCromosomatico);
        etReproduccionAsexual = findViewById(R.id.etReproduccionAsexual);
        etDistribucion = findViewById(R.id.etDistribucion);
        etBiologia = findViewById(R.id.etBiologia);
        etDemografia = findViewById(R.id.etDemografia);
        etMedidasPropuestas = findViewById(R.id.etMedidasPropuestas);
        etAmenazas = findViewById(R.id.etAmenazas);

        tilNombre = findViewById(R.id.layoutNombre);

        mavm = new ViewModelProvider(this).get(MainActivityViewModel.class);
        mavm.getFlora();

        MutableLiveData<ArrayList<Flora>> floraList = mavm.getFloraLiveData();
        floraList.observe(this,  floras -> {
            //busco la flora a cargar en pantalla
            for(int i = 0; i < floras.size(); i++) {
                if(floras.get(i).getId() == MainActivity.idFlora) {
                    pos = i;
                    etNombre.setText(floras.get(pos).getNombre());
                    etFamilia.setText(floras.get(pos).getFamilia());
                    etIdentificacion.setText(floras.get(pos).getIdentificacion());
                    etAltitud.setText(floras.get(pos).getAltitud());
                    etHabitat.setText(floras.get(pos).getHabitat());
                    etFitosociologia.setText(floras.get(pos).getFitosociologia());
                    etBiotipo.setText(floras.get(pos).getBiotipo());
                    etBiologiaReproductiva.setText(floras.get(pos).getBiologia_reproductiva());
                    etFloracion.setText(floras.get(pos).getFloracion());
                    etFructificacion.setText(floras.get(pos).getFructificacion());
                    etExpresionSexual.setText(floras.get(pos).getExpresion_sexual());
                    etPolinizacion.setText(floras.get(pos).getPolinizacion());
                    etDispersion.setText(floras.get(pos).getDispersion());
                    etNumeroCromosomatico.setText(floras.get(pos).getNumero_cromosomatico());
                    etReproduccionAsexual.setText(floras.get(pos).getReproduccion_asexual());
                    etDistribucion.setText(floras.get(pos).getDistribucion());
                    etBiologia.setText(floras.get(pos).getBiologia());
                    etDemografia.setText(floras.get(pos).getDemografia());
                    etMedidasPropuestas.setText(floras.get(pos).getMedidas_propuestas());
                    etAmenazas.setText(floras.get(pos).getAmenazas());
                }
            }
        });

        evm = new ViewModelProvider(this).get(EditFloraViewModel.class);
        evm.getEditFloraLiveData().observe(this, aLong -> {
            Log.v("xyzyx", "respuesta " + aLong);
            if(aLong > 0) {
                finish();
            }
        });

        dfvm = new ViewModelProvider(this).get(DeleteFloraViewModel.class);
        dfvm.getDeleteFloraLiveData().observe(this, new Observer<Long>() {
            @Override
            public void onChanged(Long aLong) {
                Log.v("xyzyx", "respuesta " + aLong);
                if(aLong > 0) {
                    finish();
                }
            }
        });

        btEditar = findViewById(R.id.btEditar);
        btEditar.setOnClickListener(v -> {
            builder = new AlertDialog.Builder(v.getContext());
            builder.setTitle(etNombre.getText()).setMessage("¿Desea editar la imagen?")
                    .setNegativeButton("No", (dialog, which) -> {
                        if(etNombre.getText().toString().trim().equals("")) {
                            tilNombre.setError("Este campo no puede estar vacío");
                        }else {
                            evm.editFlora(MainActivity.idFlora, getFlora());
                            Intent intent = new Intent(EditFloraActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                    })
                    .setPositiveButton( "Si", (dialog, which) -> {
                        if(etNombre.getText().toString().trim().equals("")) {
                            tilNombre.setError("Este campo no puede estar vacío");
                        }else {
                            getFlora();
                            evm.editFlora(MainActivity.idFlora,getFlora());
                            Intent intent = new Intent(EditFloraActivity.this,  AddImagenActivity.class);
                            MainActivity.aqui = 2;
                            MainActivity.nombre = etNombre.getText().toString();
                            startActivity(intent);
                        }
                    ;}).show();
        });

        btBorrar = findViewById(R.id.btBorrar);
        btBorrar.setOnClickListener(v -> {
            builder = new AlertDialog.Builder(v.getContext());
            builder.setTitle("BORRAR FLORA...")
                    .setMessage("¿Deseas borrar la flora?")
                    .setNegativeButton("No", (dialog, which) -> {

                    })
                    .setPositiveButton( "Si", (dialog, which) -> {
                        dfvm.deleteFlora(MainActivity.idFlora);
                        startActivity(new Intent(EditFloraActivity.this, MainActivity.class));
                    }).show();
        });

        btCancelar = findViewById(R.id.btCancelar3);
        btCancelar.setOnClickListener(v -> {
            finish();
        });
    }

    private Flora getFlora(){
        Flora flora = new Flora();
        flora.setNombre(etNombre.getText().toString());
        flora.setFamilia(etFamilia.getText().toString());
        flora.setIdentificacion(etIdentificacion.getText().toString());
        flora.setAltitud(etAltitud.getText().toString());
        flora.setHabitat(etHabitat.getText().toString());
        flora.setFitosociologia(etFitosociologia.getText().toString());
        flora.setBiologia(etBiologia.getText().toString());
        flora.setBiologia_reproductiva(etBiologiaReproductiva.getText().toString());
        flora.setFloracion(etFloracion.getText().toString());
        flora.setFructificacion(etFructificacion.getText().toString());
        flora.setExpresion_sexual(etExpresionSexual.getText().toString());
        flora.setPolinizacion(etPolinizacion.getText().toString());
        flora.setDispersion(etDispersion.getText().toString());
        flora.setNumero_cromosomatico(etNumeroCromosomatico.getText().toString());
        flora.setReproduccion_asexual(etReproduccionAsexual.getText().toString());
        flora.setDistribucion(etDistribucion.getText().toString());
        flora.setBiologia(etBiologia.getText().toString());
        flora.setDemografia(etDemografia.getText().toString());
        flora.setAmenazas(etAmenazas.getText().toString());
        flora.setMedidas_propuestas(etMedidasPropuestas.getText().toString());
        return flora;
    }

}