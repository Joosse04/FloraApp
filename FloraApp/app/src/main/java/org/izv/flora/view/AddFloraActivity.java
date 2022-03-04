package org.izv.flora.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import org.izv.flora.MainActivity;
import org.izv.flora.R;
import org.izv.flora.model.entity.Flora;
import org.izv.flora.viewmodel.AddFloraViewModel;

public class AddFloraActivity extends AppCompatActivity {

    private EditText etNombre, etFamilia, etIdentificacion, etAltitud, etHabitat, etFitosociologia,
            etBiotipo, etBiologiaReproductiva, etFloracion, etFructificacion, etExpresionSexual,
            etPolinizacion, etDispersion, etNumeroCromosomatico, etReproduccionAsexual,
            etDistribucion, etBiologia, etDemografia, etAmenazas, etMedidasPropuestas;
    private TextInputLayout tilNombre;
    private Button btAñadir, btCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_flora);
        initialize();
    }

    private void initialize() {
        AddFloraViewModel avm = new ViewModelProvider(this).get(AddFloraViewModel.class);
        avm.getAddFloraLiveData().observe(this, new Observer<Long>() {
            @Override
            public void onChanged(Long aLong) {
                if(aLong > 0) {
                    finish();
                } else {
                    Toast.makeText(AddFloraActivity.this, "Error", Toast.LENGTH_LONG).show();
                }
            }
        });
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
        etAmenazas = findViewById(R.id.etAmenazas);
        etMedidasPropuestas = findViewById(R.id.etMedidasPropuestas);

        tilNombre = findViewById(R.id.layoutNombre);

        btAñadir = findViewById(R.id.btAñadir);
        btAñadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etNombre.getText().toString().trim().equals("")) {
                    tilNombre.setError("Este campo no puede estar vacío");
                }else {
                    Flora flora = new Flora();
                    flora.setNombre(etNombre.getText().toString());
                    flora.setFamilia(etFamilia.getText().toString());
                    flora.setIdentificacion(etIdentificacion.getText().toString());
                    flora.setAltitud(etAltitud.getText().toString());
                    flora.setHabitat(etHabitat.getText().toString());
                    flora.setFitosociologia(etFitosociologia.getText().toString());
                    flora.setBiotipo(etBiotipo.getText().toString());
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
                    avm.createFlora(flora);

                    MainActivity.dondepasa=1;
                    MainActivity.nombre = etNombre.getText().toString();
                    Intent intent = new Intent(AddFloraActivity.this, AddImagenActivity.class);
                    startActivity(intent);
                }
            }
        });

        btCancelar = findViewById(R.id.btCancelar);
        btCancelar.setOnClickListener(v-> {
            finish();
        });
    }
}