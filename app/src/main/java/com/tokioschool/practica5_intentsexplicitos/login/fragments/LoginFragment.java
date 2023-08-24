package com.tokioschool.practica5_intentsexplicitos.login.fragments;

import static com.tokioschool.practica5_intentsexplicitos.Constants.KEY_USER;
import static com.tokioschool.practica5_intentsexplicitos.Constants.KEY_USER_BUNDLE;
import static com.tokioschool.practica5_intentsexplicitos.Constants.REQUEST_KEY_PARENT_FRAGMENT;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.tokioschool.practica5_intentsexplicitos.databinding.FragmentLoginBinding;
import com.tokioschool.practica5_intentsexplicitos.home.HomeActivity;
import com.tokioschool.practica5_intentsexplicitos.R;
import com.tokioschool.practica5_intentsexplicitos.login.LoginActivity;
import com.tokioschool.practica5_intentsexplicitos.model.User;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;
    private String name;
    private String password;
    private Bundle bundle;
    User user;
    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getNameAndPass();
        enableButton();
        listeners();
        esconderToolbar();
    }

    private void esconderToolbar() {
        LoginActivity loginActivity = (LoginActivity) getActivity();
        loginActivity.esconderToolbar();
    }

    private void getNameAndPass() {
        try {
            getParentFragmentManager().setFragmentResultListener(REQUEST_KEY_PARENT_FRAGMENT, this, new FragmentResultListener() {
                @Override
                public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                    user = result.getParcelable(KEY_USER);
                    if (user !=null) {
                        bundle = new Bundle();
                        name = user.getUsername();
                        password = user.getPassword();
                        Log.i("USER",name);
                        Log.i("PASSWORD", password);
                        bundle.putParcelable(KEY_USER,user);
                    }else {
                        Log.e("Adrian","NULO");
                    }
                }
            });

            //bundle = getIntent().getExtras().getBundle("bundle");

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
                functionNotAvailableSnackbar();
            }
        });

        //CREATE NEW ACCOUNT
        binding.activityMainTv4CreateNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.login_activity_frame, new RegisterFragment())
                        .addToBackStack(null)
                        .commitAllowingStateLoss();
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
                    Intent intent = new Intent(getContext(), HomeActivity.class).putExtra(KEY_USER_BUNDLE, bundle);
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