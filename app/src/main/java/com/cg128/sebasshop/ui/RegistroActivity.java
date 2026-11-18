package com.cg128.sebasshop.ui;

import android.os.Bundle;
import android.util.Patterns;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.cg128.sebasshop.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.color.DynamicColors;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class RegistroActivity extends AppCompatActivity {

    private TextInputLayout layoutNombre, layoutCorreo;
    private TextInputLayout layoutPassword, layoutConfirmar;
    private TextInputEditText etNombre, etCorreo, etPassword, etConfirmar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DynamicColors.applyToActivityIfAvailable(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.rootRegistro), (vista, insets) -> {
                    Insets barras = insets.getInsets(
                            WindowInsetsCompat.Type.systemBars());
                    vista.setPadding(barras.left, barras.top,
                            barras.right, barras.bottom);
                    return insets;
                });

        layoutNombre = findViewById(R.id.layoutNombre);
        layoutCorreo = findViewById(R.id.layoutCorreoRegistro);
        layoutPassword = findViewById(R.id.layoutPasswordRegistro);
        layoutConfirmar = findViewById(R.id.layoutConfirmar);

        etNombre = findViewById(R.id.etNombre);
        etCorreo = findViewById(R.id.etCorreoRegistro);
        etPassword = findViewById(R.id.etPasswordRegistro);
        etConfirmar = findViewById(R.id.etConfirmar);

        MaterialButton btnRegistrar = findViewById(R.id.btnRegistrar);
        TextView tvVolver = findViewById(R.id.tvVolver);
        TextView tvYaTengoCuenta = findViewById(R.id.tvYaTengoCuenta);

        btnRegistrar.setOnClickListener(v -> validarRegistro());
        tvVolver.setOnClickListener(v -> finish());
        tvYaTengoCuenta.setOnClickListener(v -> finish());
    }

    private void validarRegistro() {
        String nombre = texto(etNombre).trim();
        String correo = texto(etCorreo).trim();
        String password = texto(etPassword);
        String confirmar = texto(etConfirmar);

        layoutNombre.setError(null);
        layoutCorreo.setError(null);
        layoutPassword.setError(null);
        layoutConfirmar.setError(null);
        boolean valido = true;

        if (nombre.isEmpty()) {
            layoutNombre.setError("Escribe tu nombre");
            valido = false;
        } else if (nombre.length() < 3) {
            layoutNombre.setError("El nombre es demasiado corto");
            valido = false;
        }

        if (correo.isEmpty()) {
            layoutCorreo.setError("Escribe tu correo");
            valido = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
            layoutCorreo.setError("El correo no es válido");
            valido = false;
        }

        if (password.isEmpty()) {
            layoutPassword.setError("Escribe una contraseña");
            valido = false;
        } else if (password.length() < 6) {
            layoutPassword.setError("Mínimo 6 caracteres");
            valido = false;
        }

        if (confirmar.isEmpty()) {
            layoutConfirmar.setError("Confirma tu contraseña");
            valido = false;
        } else if (!password.equals(confirmar)) {
            layoutConfirmar.setError("Las contraseñas no coinciden");
            valido = false;
        }

        if (valido) {
            Toast.makeText(this,
                    "¡Cuenta creada! (demostración)",
                    Toast.LENGTH_LONG).show();
            finish();
        }
    }

    private String texto(TextInputEditText campo) {
        return campo.getText() == null ? "" : campo.getText().toString();
    }
}
