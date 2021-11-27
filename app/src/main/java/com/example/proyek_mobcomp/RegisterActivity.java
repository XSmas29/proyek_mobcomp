package com.example.proyek_mobcomp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.RadioGroup;

import com.example.proyek_mobcomp.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {

    ActivityRegisterBinding bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        bind = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        bind.rgRole.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.rbCustomer){
                    bind.etRegisterToko.setText("");
                    bind.etRegisterToko.setEnabled(false);
                }
                else{
                    bind.etRegisterToko.setEnabled(true);
                }
            }
        });
    }

    public void ToLogin(View v){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void Register(View v){
        bind.etRegisterConfirm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        if (bind.etRegisterUsername.getText().toString().isEmpty() || bind.etRegisterEmail.getText().toString().isEmpty() || bind.etRegisterNama.getText().toString().isEmpty() || bind.etRegisterRekening.getText().toString().isEmpty() || bind.etRegisterPassword.getText().toString().isEmpty() || bind.etRegisterConfirm.getText().toString().isEmpty()){

        }
    }
}