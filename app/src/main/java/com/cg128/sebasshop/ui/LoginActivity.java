package com.cg128.sebasshop.ui;

import android.content.Intent;
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

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout layoutCorreo, layoutPassword;
    private TextInputEditText etCorreo, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Material You cuando está disponible. Los colores naranjas de los
        // componentes están definidos explícitamente para mantener la marca.
        DynamicColors.applyToActivityIfAvailable(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        aplicarInsets(R.id.rootLogin);

        layoutCorreo = findViewById(R.id.layoutCorreo);
        layoutPassword = findViewById(R.id.layoutPassword);
        etCorreo = findViewById(R.id.etCorreo);
        etPassword = findViewById(R.id.etPassword);

        MaterialButton btnEntrar = findViewById(R.id.btnEntrar);
        TextView tvCrearCuenta = findViewById(R.id.tvCrearCuenta);

        btnEntrar.setOnClickListener(v -> validarLogin());
        tvCrearCuenta.setOnClickListener(v ->
                startActivity(new Intent(this, RegistroActivity.class)));
    }

    private void aplicarInsets(int id) {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(id), (vista, insets) -> {
            Insets barras = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            vista.setPadding(barras.left, barras.top, barras.right, barras.bottom);
            return insets;
        });
    }

    private void validarLogin() {
        String correo = texto(etCorreo).trim();
        String password = texto(etPassword);

        layoutCorreo.setError(null);
        layoutPassword.setError(null);
        boolean valido = true;

        if (correo.isEmpty()) {
            layoutCorreo.setError("Escribe tu correo electrónico");
            valido = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
            layoutCorreo.setError("El correo no es válido");
            valido = false;
        }

        if (password.isEmpty()) {
            layoutPassword.setError("Escribe tu contraseña");
            valido = false;
        } else if (password.length() < 6) {
            layoutPassword.setError("Mínimo 6 caracteres");
            valido = false;
        }

        if (valido) {
            Toast.makeText(this,
                    "¡Bienvenido a SebasShop! (demostración)",
                    Toast.LENGTH_LONG).show();
        }
    }

    private String texto(TextInputEditText campo) {
        return campo.getText() == null ? "" : campo.getText().toString();
    }
}
