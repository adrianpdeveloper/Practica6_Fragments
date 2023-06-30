package com.tokioschool.practica5_intentsexplicitos;

import static com.tokioschool.practica5_intentsexplicitos.Constants.KEY_NAME_STRING;
import static com.tokioschool.practica5_intentsexplicitos.Constants.KEY_PASSWORD_STRING;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.tokioschool.practica5_intentsexplicitos.databinding.ActivityRegisterBinding;


public class RegisterActivity extends AppCompatActivity {
    ActivityRegisterBinding binding;
    String[] ageOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.mainActivityToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setup();
        abrirCamara();
        verCondiciones();
        corregirTexto();


    }

    private void enableButton() {
        if (binding.mainActivityNameInputlayout.getError()==null && binding.mainActivityPasswordInputlayout.getError()==null && binding.mainActivityAgeAutocomplete.getText().toString().equalsIgnoreCase(getString(R.string.mayor_de_edad))){
            binding.mainActivityButton.setEnabled(true);
            binding.mainActivityButton.setBackgroundColor(getResources().getColor(R.color.register_activity_btn_yellow_enabled,null));
        }else{
            binding.mainActivityButton.setEnabled(false);
            binding.mainActivityButton.setBackgroundColor(getResources().getColor(R.color.register_activity_btn_yellow_disabled,null));
        }
    }

    private void corregirTexto() {

        binding.mainActivityNameEdittext.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (binding.mainActivityNameEdittext.getText().toString().contains("@") || binding.mainActivityNameEdittext.getText().toString().contains("!") ){
                    binding.mainActivityNameInputlayout.setError(getResources().getString(R.string.set_error_name_surname_input));
                }else{
                    binding.mainActivityNameInputlayout.setError(null);
                }
                enableButton();
            }
        });


        binding.mainActivityPasswordEdittext.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (binding.mainActivityPasswordEdittext.getText().toString().contains("@") || binding.mainActivityPasswordEdittext.getText().toString().contains("!") ){
                    binding.mainActivityPasswordInputlayout.setError(getResources().getString(R.string.set_error_name_surname_input));
                }else{
                    binding.mainActivityPasswordInputlayout.setError(null);
                }
                enableButton();
            }
        });

        binding.mainActivityAgeAutocomplete.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (binding.mainActivityAgeAutocomplete.getText().toString().equalsIgnoreCase(getString(R.string.mayor_de_edad))){
                    binding.mainActivityAgeInputlayout.setError(null);
                }else{
                    binding.mainActivityAgeInputlayout.setError(getResources().getString(R.string.set_error_age_input));
                }
                    enableButton();
            }
        });

        binding.mainActivityToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        binding.mainActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString(KEY_NAME_STRING,binding.mainActivityNameEdittext.getText().toString());
                bundle.putString(KEY_PASSWORD_STRING,binding.mainActivityPasswordEdittext.getText().toString());

                Intent intent = new Intent(getApplicationContext(), MainActivity.class).putExtra("bundle",bundle);
                startActivity(intent);
            }
        });


    }

    private void verCondiciones() {
        binding.mainActivityConditionsTv.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://developers.google.com/ml-kit/terms"));
            startActivity(intent);
        });
    }

    private void abrirCamara() {
        binding.mainActivityCameraBtn.setOnClickListener(view -> {
             Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
             startActivity(intent);
        });
    }

    private void setup() {
        ageOptions = getResources().getStringArray(R.array.register_activity_age_inputtext_options);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, ageOptions);
        binding.mainActivityAgeAutocomplete.setAdapter(adapter);
    }




}