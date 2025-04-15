package mx.unam.contactos_android;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import mx.unam.contactos_android.databinding.ActivityMainBinding;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.tietFechaNacimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDatePicker();
            }
        });

        binding.btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = binding.tietNombreCompleto.getText().toString();
                String fechaNacimiento = binding.tietFechaNacimiento.getText().toString();
                String telefono = binding.tietTelefono.getText().toString();
                String email = binding.tietEmail.getText().toString();
                String descripcion = binding.tietDescripcion.getText().toString();

                Intent intent = new Intent(MainActivity.this, ConfirmacionActivity.class);

                intent.putExtra("EXTRA_NOMBRE", nombre);
                intent.putExtra("EXTRA_FECHA_NACIMIENTO", fechaNacimiento);
                intent.putExtra("EXTRA_TELEFONO", telefono);
                intent.putExtra("EXTRA_EMAIL", email);
                intent.putExtra("EXTRA_DESCRIPCION", descripcion);

                startActivity(intent);
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        populateFieldsFromIntent(intent);
    }

    private void populateFieldsFromIntent(Intent intent) {
        if (intent != null && intent.getExtras() != null) {
            String nombre = intent.getStringExtra("EXTRA_NOMBRE");
            String fechaNacimiento = intent.getStringExtra("EXTRA_FECHA_NACIMIENTO");
            String telefono = intent.getStringExtra("EXTRA_TELEFONO");
            String email = intent.getStringExtra("EXTRA_EMAIL");
            String descripcion = intent.getStringExtra("EXTRA_DESCRIPCION");
            if (nombre != null) binding.tietNombreCompleto.setText(nombre);
            if (fechaNacimiento != null) binding.tietFechaNacimiento.setText(fechaNacimiento);
            if (telefono != null) binding.tietTelefono.setText(telefono);
            if (email != null) binding.tietEmail.setText(email);
            if (descripcion != null) binding.tietDescripcion.setText(descripcion);
        }
    }

    private void mostrarDatePicker() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
                int monthCorrected = selectedMonth + 1;
                String fechaSeleccionada = String.format(Locale.getDefault(), "%02d/%02d/%d", selectedDay, monthCorrected, selectedYear);
                binding.tietFechaNacimiento.setText(fechaSeleccionada);
            }
        };

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                dateSetListener,
                year, month, day);

        datePickerDialog.show();
    }
}