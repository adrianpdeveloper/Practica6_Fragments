package com.tokioschool.practica5_intentsexplicitos;

import static com.tokioschool.practica5_intentsexplicitos.Constants.KEY_NAME_STRING;
import static com.tokioschool.practica5_intentsexplicitos.Constants.KEY_PASSWORD_STRING;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.tokioschool.practica5_intentsexplicitos.R;
import com.tokioschool.practica5_intentsexplicitos.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private String name;
    private String password;

    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getNameAndPass();
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        enableButton();
        listeners();

    }

    private void getNameAndPass() {
        try {
            bundle = getIntent().getExtras().getBundle("bundle");
            name = bundle.getString(KEY_NAME_STRING);
            password = bundle.getString(KEY_PASSWORD_STRING);
            Log.i("Adrian",name);
        }catch (Exception e){
            Log.i("Mensaje", "No hay password ni contraseña");
        }

    }

    private void functionNotAvailableSnackbar() {
        Snackbar.make(binding.getRoot(), R.string.main_activity_snackbar_not_available_function, BaseTransientBottomBar.LENGTH_LONG).show();
    }

    private void listeners() {
        //GET NEW PASSWORD
        binding.activityMainTv2GetNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Todo: funcionaliodad get new password
                functionNotAvailableSnackbar();
            }
        });

        //CREATE NEW ACCOUNT
        binding.activityMainTv4CreateNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });

        binding.registerActivityLoginEdittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (binding.registerActivityLoginEdittext.getText().length()==0){
                    binding.registerActivityLoginIl.setError("Debes rellenar este campo");
                }else{
                    binding.registerActivityLoginIl.setError(null);
                }
                enableButton();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.registerActivityPasswordEdittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (binding.registerActivityPasswordEdittext.getText().length()==0){
                    binding.registerActivityPasswordIl.setError("Debes rellenar este campo");
                }else{
                    binding.registerActivityPasswordIl.setError(null);
                }
                enableButton();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.activityMainLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name != null && password != null && bundle != null){
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class).putExtra("bundle", bundle);
                    startActivity(intent);
                }else{
                    Snackbar.make(binding.getRoot(), R.string.main_activity_login_failed, BaseTransientBottomBar.LENGTH_LONG).show();
                }
            }
        });


    }

    private void enableButton() {
        if (binding.registerActivityPasswordIl.getError()!=null || binding.registerActivityLoginIl.getError()!=null || binding.registerActivityPasswordEdittext.getText().toString().isEmpty() || binding.registerActivityLoginEdittext.getText().toString().isEmpty()){
            binding.activityMainLoginBtn.setEnabled(false);
        }else{
            binding.activityMainLoginBtn.setEnabled(true);
        }
    }
}