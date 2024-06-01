package com.example.loginsample;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

public class HomeActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        TextView firstname = findViewById(R.id.tvFirstname);
        TextView lastname = findViewById(R.id.tvLastname);
        TextView email = findViewById(R.id.tvEmail);
        TextView phone = findViewById(R.id.tvPhone);
        TextView username = findViewById(R.id.tvUsername);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(AccountActivity.ACCOUNT_RECORD)) {
            String userDataJson = intent.getStringExtra(AccountActivity.ACCOUNT_RECORD);
            if (userDataJson != null) {
                AccountEntity user = new Gson().fromJson(userDataJson, AccountEntity.class);

                firstname.setText("Nombre: " + user.getFirstname());
                lastname.setText("Apellido: " + user.getLastname());
                email.setText("Correo: " + user.getEmail());
                phone.setText("Teléfono: " + user.getPhone());
                username.setText("Usuario: " + user.getUsername());
            }
        }
    }
}
