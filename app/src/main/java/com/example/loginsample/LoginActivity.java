package com.example.loginsample;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import com.google.gson.Gson;

public class LoginActivity extends AppCompatActivity implements AccountFragment.OnRegisterListener {

    private static final String TAG = "LoginActivity";
    private AccountEntity usuario;

    private EditText edtUsername;
    private EditText edtPassword;
    private Button btnLogin;
    private Button btnAddAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnAddAccount = findViewById(R.id.btnAddAccount);

        btnLogin.setOnClickListener(v -> {
            if (usuario != null) {
                String username = usuario.getUsername();
                String password = usuario.getPassword();

                if (edtUsername.getText().toString().equals(username) && edtPassword.getText().toString().equals(password)) {
                    Toast.makeText(getApplicationContext(), "Bienvenido a mi App", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "Bienvenido a mi App");

                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    intent.putExtra(AccountActivity.ACCOUNT_RECORD, new Gson().toJson(usuario)); // Usa la constante correcta
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Error en la autenticación", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "Error en la autenticación");
                }
            } else {
                Toast.makeText(getApplicationContext(), "No hay registros", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "No hay registros");
            }
        });

        btnAddAccount.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), AccountActivity.class);
            activityResultLauncher.launch(intent);
        });
    }

    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult activityResult) {
                    if (activityResult.getResultCode() == AccountActivity.ACCOUNT_ACEPTAR) {
                        Intent data = activityResult.getData();
                        if (data != null && data.hasExtra(AccountActivity.ACCOUNT_RECORD)) {
                            String accountRecord = data.getStringExtra(AccountActivity.ACCOUNT_RECORD);
                            usuario = new Gson().fromJson(accountRecord, AccountEntity.class);
                            if (usuario != null) {
                                String firstname = usuario.getFirstname();
                                Toast.makeText(getApplicationContext(), "Nombre: " + firstname, Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Error en los datos", Toast.LENGTH_SHORT).show();
                        }
                    } else if (activityResult.getResultCode() == AccountActivity.ACCOUNT_CANCELAR) {
                        Toast.makeText(getApplicationContext(), "Cancelado", Toast.LENGTH_SHORT).show();
                    }
                }
            }
    );

    @Override
    public void onRegister(AccountEntity accountEntity) {
        // Aquí puedes implementar la lógica para manejar el registro del usuario
        // Puedes acceder a la información del usuario a través del objeto accountEntity recibido
        usuario = accountEntity;
    }

    @Override
    public void onCancel() {
        // Implementa aquí la lógica para cancelar el registro del usuario si es necesario
    }

    @Override
    public void onClose() {
        // Implementa aquí la lógica para cerrar la actividad o realizar alguna acción adicional
    }
}
