package com.example.loginsample;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.widget.TextView;


public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/
        String firstname = getIntent().getStringExtra("firstname");
        String lastname = getIntent().getStringExtra("lastname");
        String email = getIntent().getStringExtra("email");
        String phone = getIntent().getStringExtra("phone");
        String username = getIntent().getStringExtra("username");

        Log.d("HomeActivity", "Username: "+username);

        TextView textViewWelcome = findViewById(R.id.textViewWelcome);
        textViewWelcome.setText("Bienvenido " + firstname);

       TextView textViewFirstname = findViewById(R.id.textViewFirstname);
        textViewFirstname.setText("Nombre: " + firstname);

        TextView textViewLastname = findViewById(R.id.textViewLastname);
        textViewLastname.setText("Apellido: " + lastname);

        TextView textViewEmail = findViewById(R.id.textViewEmail);
        textViewEmail.setText("Email: " + email);

        TextView textViewPhone = findViewById(R.id.textViewPhone);
        textViewPhone.setText("Teléfono: " + phone);

        TextView textViewUsername = findViewById(R.id.textViewUsername);
        textViewUsername.setText("Usuario: " + username);
    }
}
