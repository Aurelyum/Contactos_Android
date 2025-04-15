package mx.unam.contactos_android;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import mx.unam.contactos_android.databinding.ActivityConfirmacionBinding;

public class ConfirmacionActivity extends AppCompatActivity {

    private ActivityConfirmacionBinding binding;
    private String nombre, fechaNacimiento, telefono, email, descripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityConfirmacionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        Intent intent = getIntent();

        nombre = intent.getStringExtra("EXTRA_NOMBRE");
        fechaNacimiento = intent.getStringExtra("EXTRA_FECHA_NACIMIENTO");
        telefono = intent.getStringExtra("EXTRA_TELEFONO");
        email = intent.getStringExtra("EXTRA_EMAIL");
        descripcion = intent.getStringExtra("EXTRA_DESCRIPCION");
        binding.tvNombreConfirmacion.setText(nombre);
        binding.tvFechaConfirmacion.setText(fechaNacimiento);
        binding.tvTelefonoConfirmacion.setText(telefono);
        binding.tvEmailConfirmacion.setText(email);
        binding.tvDescripcionConfirmacion.setText(descripcion);
        binding.btnEditarDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentRegreso = new Intent(ConfirmacionActivity.this, MainActivity.class);
                intentRegreso.putExtra("EXTRA_NOMBRE", nombre);
                intentRegreso.putExtra("EXTRA_FECHA_NACIMIENTO", fechaNacimiento);
                intentRegreso.putExtra("EXTRA_TELEFONO", telefono);
                intentRegreso.putExtra("EXTRA_EMAIL", email);
                intentRegreso.putExtra("EXTRA_DESCRIPCION", descripcion);
                intentRegreso.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

                startActivity(intentRegreso);
                finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}