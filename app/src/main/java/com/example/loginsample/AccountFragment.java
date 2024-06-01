package com.example.loginsample;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


import androidx.fragment.app.Fragment;

import com.google.gson.Gson;

public class AccountFragment extends Fragment {

    private OnRegisterListener listener;
    private EditText edtFirstname, edtLastname, edtEmail, edtPhone, edtUsername, edtPassword;

    public interface OnRegisterListener {
        void onRegister(AccountEntity accountEntity);
        void onCancel();
        void onClose();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnRegisterListener) {
            listener = (OnRegisterListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnRegisterListener");
        }
    }

    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        EditText edtFirstname = view.findViewById(R.id.edtFirstname);
        EditText edtLastname = view.findViewById(R.id.edtLastname);
        EditText edtEmail = view.findViewById(R.id.edtEmail);
        EditText edtPhone = view.findViewById(R.id.edtPhone);
        EditText edtUsername2 = view.findViewById(R.id.edtUsername2);
        EditText edtPassword2 = view.findViewById(R.id.edtPassword2);

        Button btnAceptar = view.findViewById(R.id.btnAceptar);
        Button btnCancelar = view.findViewById(R.id.btnCancelar);
        Button btnCerrar = view.findViewById(R.id.btnCerrar);


        btnAceptar.setOnClickListener(v -> {
            AccountEntity accountEntity = new AccountEntity();
            accountEntity.setFirstname(edtFirstname.getText().toString());
            accountEntity.setLastname(edtLastname.getText().toString());
            accountEntity.setEmail(edtEmail.getText().toString());
            accountEntity.setPhone(edtPhone.getText().toString());
            accountEntity.setUsername(edtUsername2.getText().toString());
            accountEntity.setPassword(edtPassword2.getText().toString());

            if (listener != null) {
                listener.onRegister(accountEntity);
            }
        });

        btnCancelar.setOnClickListener(v -> {
            if (listener != null) {
                listener.onCancel();
            }
        });

        btnCerrar.setOnClickListener(v -> {
            if (listener != null) {
                listener.onClose();
            }
        });

        return view;
    }
}
