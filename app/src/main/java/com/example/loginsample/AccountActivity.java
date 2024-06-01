package com.example.loginsample;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.gson.Gson;

public class AccountActivity extends AppCompatActivity implements AccountFragment.OnRegisterListener {

    public final static String ACCOUNT_RECORD = "ACCOUNT_RECORD";
    public final static Integer ACCOUNT_ACEPTAR = 100;
    public final static Integer ACCOUNT_CANCELAR = 200;
    private AccountEntity usuario;

    private TextView tvUserDetails;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        tvUserDetails = findViewById(R.id.tvUserDetails);
        fragmentManager = getSupportFragmentManager();

        if (savedInstanceState == null) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            AccountFragment registerFragment = new AccountFragment();
            fragmentTransaction.add(R.id.fragment_container, registerFragment);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onRegister(AccountEntity accountEntity) {
        this.usuario = accountEntity;
        String accountDetails = "Hola " + accountEntity.getFirstname() + " " + accountEntity.getLastname();
        tvUserDetails.setText(accountDetails);

        // Enviar los datos de vuelta al LoginActivity
        Intent resultIntent = new Intent();
        resultIntent.putExtra(ACCOUNT_RECORD, new Gson().toJson(accountEntity));
        setResult(ACCOUNT_ACEPTAR, resultIntent);
    }

    @Override
    public void onCancel() {
        setResult(ACCOUNT_CANCELAR);
        finish();
    }

    @Override
    public void onClose() {
        if (usuario != null) {
            Intent homeIntent = new Intent(AccountActivity.this, HomeActivity.class);
            homeIntent.putExtra(ACCOUNT_RECORD, new Gson().toJson(usuario));
            startActivity(homeIntent);
        }
        finish();
    }
}
