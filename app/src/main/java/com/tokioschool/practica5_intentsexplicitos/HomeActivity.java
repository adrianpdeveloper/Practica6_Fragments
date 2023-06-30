package com.tokioschool.practica5_intentsexplicitos;

import static com.tokioschool.practica5_intentsexplicitos.Constants.KEY_NAME_STRING;
import static com.tokioschool.practica5_intentsexplicitos.Constants.KEY_PASSWORD_STRING;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    private String name;
    private String password;

    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getNameAndPass();
    }

    private void getNameAndPass() {
        try {
            bundle = getIntent().getExtras().getBundle("bundle");
            name = bundle.getString(KEY_NAME_STRING);
            password = bundle.getString(KEY_PASSWORD_STRING);
            Log.i("Nombre","Nombre: "+name);
            Log.i("Pass","Contraseña: "+password);
        }catch (Exception e){
            Log.i("Mensaje", "No hay password ni contraseña");
        }

    }
}