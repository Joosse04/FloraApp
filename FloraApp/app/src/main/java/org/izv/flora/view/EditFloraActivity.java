package org.izv.flora.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputLayout;

import org.izv.flora.MainActivity;
import org.izv.flora.R;
import org.izv.flora.model.entity.Flora;
import org.izv.flora.model.entity.Imagen;
import org.izv.flora.viewmodel.DeleteFloraViewModel;
import org.izv.flora.viewmodel.DeleteImagenViewModel;
import org.izv.flora.viewmodel.EditFloraViewModel;

public class EditFloraActivity extends AppCompatActivity {

    private EditText etNombre, etFamilia, etIdentificacion, etAltitud, etHabitat, etFitosociologia,
            etBiotipo, etBiologiaReproductiva, etFloracion, etFructificacion, etExpresionSexual,
            etPolinizacion, etDispersion, etNumeroCromosomatico, etReproduccionAsexual,
            etDistribucion, etBiologia, etDemografia, etAmenazas, etMedidasPropuestas;
    private TextInputLayout tilNombre;
    private Button btEditar, btBorrar, btCancelar;

    Flora flora = new Flora();
    Imagen imagen = new Imagen();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_flora);
        initialize();
    }

    private void initialize() {
        flora = getIntent().getExtras().getParcelable("flora");

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

        EditFloraViewModel evm = new ViewModelProvider(this).get(EditFloraViewModel.class);
        evm.getEditFloraLiveData().observe(this, new Observer<Long>() {
            @Override
            public void onChanged(Long aLong) {
                Log.v("xyzyx", "respuesta " + aLong);
                if(aLong > 0) {
                    finish();
                }
            }
        });

        etNombre.setText(flora.getNombre());
        etFamilia.setText(flora.getFamilia());
        etIdentificacion.setText(flora.getIdentificacion());
        etAltitud.setText(flora.getAltitud());
        etHabitat.setText(flora.getHabitat());
        etFitosociologia.setText(flora.getFitosociologia());
        etBiotipo.setText(flora.getBiotipo());
        etBiologiaReproductiva.setText(flora.getBiologia_reproductiva());
        etFloracion.setText(flora.getFloracion());
        etFructificacion.setText(flora.getFructificacion());
        etExpresionSexual.setText(flora.getExpresion_sexual());
        etPolinizacion.setText(flora.getPolinizacion());
        etDispersion.setText(flora.getDispersion());
        etNumeroCromosomatico.setText(flora.getNumero_cromosomatico());
        etReproduccionAsexual.setText(flora.getReproduccion_asexual());
        etDistribucion.setText(flora.getDistribucion());
        etBiologia.setText(flora.getBiologia());
        etDemografia.setText(flora.getDemografia());
        etMedidasPropuestas.setText(flora.getMedidas_propuestas());
        etAmenazas.setText(flora.getAmenazas());

        btEditar = findViewById(R.id.btEditar);
        btEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etNombre.getText().toString().trim().equals("")) {
                    tilNombre.setError("Este campo no puede estar vacío");
                }else {
                    getFlora();
                    Log.v("xyzyx", String.valueOf(getFlora()));
                    evm.editFlora(flora.getId(),getFlora());
                    MainActivity.dondepasa=2;
                    MainActivity.nombre = etNombre.getText().toString();
                    Intent refresh = new Intent(EditFloraActivity.this, MainActivity.class);
                    startActivity(refresh);
                }
            }
        });

        DeleteFloraViewModel dfvm = new ViewModelProvider(this).get(DeleteFloraViewModel.class);
        dfvm.getDeleteFloraLiveData().observe(this, new Observer<Long>() {
            @Override
            public void onChanged(Long aLong) {
                Log.v("xyzyx", "respuesta " + aLong);
                if(aLong > 0) {
                    finish();
                }
            }
        });

        DeleteImagenViewModel divm = new ViewModelProvider(this).get(DeleteImagenViewModel.class);
        divm.getDeleteImagenLiveData().observe(this, new Observer<Long>() {
            @Override
            public void onChanged(Long aLong) {
                Log.v("xyzyx", "respuesta " + aLong);
                if(aLong > 0) {
                    finish();
                }
            }
        });

        btBorrar = findViewById(R.id.btBorrar);
        btBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder  = new AlertDialog.Builder(v.getContext());
                builder.setTitle("ALERTA BORRAR FLORA...")
                        .setMessage("¿Deseas borrar esta flora?")
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setPositiveButton( "Sí", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //getFlora();
                                dfvm.deleteFlora(flora.getId());
                                divm.deleteImagen(imagen.id);
                                Intent refresh = new Intent(EditFloraActivity.this, MainActivity.class);
                                startActivity(refresh);
                            }
                        });
                builder.create().show();
            }
        });

        btCancelar = findViewById(R.id.btCancelar3);
        btCancelar.setOnClickListener(v-> {
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
