package com.example.loginsample;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.loginsample.databinding.ActivityAccountBinding;

import com.example.loginsample.databinding.ActivityLoginBinding;

public class AccountActivity extends AppCompatActivity {

    private ActivityAccountBinding binding;
    private static final String PREFS_NAME = "UserPrefs";
    private static final String USUARIO = "username";
    private static final String CONTRA = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAccountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding = ActivityAccountBinding.inflate((getLayoutInflater()));
        setContentView(binding.getRoot());
        EditText edtFirstname = binding.edtFirstname;
        EditText edtLastname = binding.edtLastname;
        EditText edtEmail = binding.edtEmail;
        EditText edtPhone = binding.edtPhone;
        EditText edtUsername = binding.edtUsername2;
        EditText edtPassword = binding.edtPassword2;
        Button btnOK = binding.btnOK ;
        Button btnCancel = binding.btnCancel;

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFormComplete()) {

                    AccountEntity account = new AccountEntity();

                    // Se crea un objeto AccountEntity y establecer los valores
                    account.setFirstname(edtFirstname.getText().toString());
                    account.setLastname(edtLastname.getText().toString());
                    account.setEmail(edtEmail.getText().toString());
                    account.setPhone(edtPhone.getText().toString());
                    account.setUsername(edtUsername.getText().toString());
                    account.setPassword(edtPassword.getText().toString());

                    // SE obtiene la información del usuario y contraseña
                    String username = binding.edtUsername2.getText().toString();
                    String password = binding.edtPassword2.getText().toString();

                    // Guardar los datos del usuario en SharedPreferences
                    SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString(USUARIO, username);
                    editor.putString(CONTRA, password);
                    editor.apply();

                    // Ir a LoginActivity
                    Intent intent = new Intent(AccountActivity.this, LoginActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "Complete todos los campos", Toast.LENGTH_SHORT).show();

                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private boolean isFormComplete() {
        return !binding.edtFirstname.getText().toString().isEmpty() &&
                !binding.edtLastname.getText().toString().isEmpty() &&
                !binding.edtEmail.getText().toString().isEmpty() &&
                !binding.edtPhone.getText().toString().isEmpty() &&
                !binding.edtUsername2.getText().toString().isEmpty() &&
                !binding.edtPassword2.getText().toString().isEmpty();
    }
}
