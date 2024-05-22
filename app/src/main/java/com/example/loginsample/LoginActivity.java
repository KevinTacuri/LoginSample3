package com.example.loginsample;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import com.example.loginsample.databinding.ActivityLoginBinding;
import com.google.gson.Gson;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private static final String TAG = "MainActivity";
    private  String accountEntityString;
    private AccountEntity usuario;


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
                if(usuario != null){
                    String username = usuario.getUsername();
                    String password = usuario.getPassword();


                    if (edtUsername.getText().toString().equals(username) && edtPassword.getText().toString().equals(password)) {
                        Toast.makeText(getApplicationContext(), "Bienvenido a mi App", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "Bienvenido a mi App");

                        Intent intent = new Intent(LoginActivity.this,HomeActivity.class);

                        intent.putExtra("firstname", usuario.getFirstname());
                        intent.putExtra("lastname", usuario.getLastname());
                        intent.putExtra("email", usuario.getEmail());
                        intent.putExtra("phone", usuario.getPhone());
                        intent.putExtra("username", usuario.getUsername());

                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Error en la autenticacion",Toast.LENGTH_SHORT).show();
                        Log.d(TAG,"Error en la autenticacion");
                    }



                }else{
                    Toast.makeText(getApplicationContext(),"No hay registros",Toast.LENGTH_SHORT).show();
                    Log.d(TAG,"No hay registros");
                }
            }
        });

        btnAddAccount.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(),AccountActivity.class);
            activityResultLauncher.launch(intent);
        });
    }

    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult activityResult) {
                    if(activityResult.getResultCode()==AccountActivity.ACCOUNT_ACEPTAR) {
                        Intent data = activityResult.getData();
                        if (data != null && data.hasExtra(AccountActivity.ACCOUNT_RECORD)) {
                            String account_record = data.getStringExtra(AccountActivity.ACCOUNT_RECORD);
                            Gson gson = new Gson();
                            usuario = gson.fromJson(account_record, AccountEntity.class);
                            if (usuario != null) {
                                String firstname = usuario.getFirstname();
                                Toast.makeText(getApplicationContext(), "Nombre: " + firstname, Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Error de los datos", Toast.LENGTH_SHORT).show();
                        }
                    } else if (activityResult.getResultCode() == AccountActivity.ACCOUNT_CANCELAR) {
                        Toast.makeText(getApplicationContext(), "Cancelado", Toast.LENGTH_SHORT).show();
                    }
                }
            }
    );
}