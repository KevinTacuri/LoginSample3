package com.example.loginsample;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.loginsample.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private static final String PREFS_NAME = "UserPrefs";
    private static final String USUARIO = "username";
    private static final String CONTRA = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        EditText edtUsername = binding.edtUsername;
        EditText edtPassword = binding.edtPassword;
        Button btnLogin = binding.btnLogin;
        Button btnAddAccount = binding.btnAddAccount;

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener la información del usuario y contraseña
                String username = edtUsername.getText().toString();
                String password = edtPassword.getText().toString();

                // Verificar la autenticación
                if (authenticateUser(username, password)) {
                    Toast.makeText(getApplicationContext(), "Bienvenido a mi App", Toast.LENGTH_SHORT).show();

                    // Enviar el nombre de usuario a HomeActivity
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    intent.putExtra("username", username);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Error en la autenticación", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnAddAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AccountActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean authenticateUser(String username, String password) {
        // Obtener los datos guardados del usuario
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String storedUsername = prefs.getString(USUARIO, "");
        String storedPassword = prefs.getString(CONTRA, "");

        // Verificar si el usuario y la contraseña coinciden
        return username.equals(storedUsername) && password.equals(storedPassword);
    }
}
