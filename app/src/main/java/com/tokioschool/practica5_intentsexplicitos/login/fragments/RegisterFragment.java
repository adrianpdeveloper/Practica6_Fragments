package com.tokioschool.practica5_intentsexplicitos.login.fragments;

import static com.tokioschool.practica5_intentsexplicitos.Constants.KEY_USER;
import static com.tokioschool.practica5_intentsexplicitos.Constants.REQUEST_KEY_PARENT_FRAGMENT;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.tokioschool.practica5_intentsexplicitos.R;
import com.tokioschool.practica5_intentsexplicitos.databinding.FragmentRegisterBinding;
import com.tokioschool.practica5_intentsexplicitos.login.LoginActivity;
import com.tokioschool.practica5_intentsexplicitos.model.User;

import java.util.ArrayList;

public class RegisterFragment extends Fragment {

    private FragmentRegisterBinding binding;
    String[] ageOptions;



    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(getLayoutInflater());
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        setup();
        abrirCamara();
        verCondiciones();
        corregirTexto();
        mostrarToolbar();
    }

    private void mostrarToolbar() {
        LoginActivity loginActivity = (LoginActivity) getActivity();
        loginActivity.mostrarToolbar();
    }

    private void enableButton() {
        if (binding.mainActivityNameInputlayout.getError()==null && binding.mainActivityPasswordInputlayout.getError()==null && binding.registerFragmentAgeAutocomplete.getText().toString().equalsIgnoreCase(getString(R.string.mayor_de_edad))){
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

        binding.registerFragmentAgeAutocomplete.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (binding.registerFragmentAgeAutocomplete.getText().toString().equalsIgnoreCase(getString(R.string.mayor_de_edad))){
                    binding.mainActivityAgeInputlayout.setError(null);
                }else{
                    binding.mainActivityAgeInputlayout.setError(getResources().getString(R.string.set_error_age_input));
                }
                enableButton();
            }
        });


        binding.mainActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                User user = new User();
                user.setUsername(binding.mainActivityNameEdittext.getText().toString());
                user.setPassword(binding.mainActivityPasswordEdittext.getText().toString());
                bundle.putParcelable(KEY_USER, user);

                getParentFragmentManager().setFragmentResult(REQUEST_KEY_PARENT_FRAGMENT, bundle);
                getParentFragmentManager().popBackStack();
            }
        });
        binding.registerFragmentAgeAutocomplete.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    binding.registerFragmentAgeAutocomplete.showDropDown();
                }
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
        Log.e("Setup","Setup");
        ageOptions = getResources().getStringArray(R.array.register_activity_age_inputtext_options);
        ArrayList<String> ageOptions2 = new ArrayList<String>();
        ageOptions2.add("Hola1");
        ageOptions2.add("Hola2");
        ageOptions2.add("Hola3");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_dropdown_item_1line, ageOptions);
        binding.registerFragmentAgeAutocomplete.setAdapter(adapter);
        binding.registerFragmentAgeAutocomplete.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (getView().hasFocus()){
                    binding.registerFragmentAgeAutocomplete.showDropDown();
                }
            }
        });

    }
}