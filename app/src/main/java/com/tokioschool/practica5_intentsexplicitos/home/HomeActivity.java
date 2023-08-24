package com.tokioschool.practica5_intentsexplicitos.home;

import static com.tokioschool.practica5_intentsexplicitos.Constants.KEY_NAME_STRING;
import static com.tokioschool.practica5_intentsexplicitos.Constants.KEY_PASSWORD_STRING;
import static com.tokioschool.practica5_intentsexplicitos.Constants.KEY_USER;
import static com.tokioschool.practica5_intentsexplicitos.Constants.KEY_USER_BUNDLE;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.tokioschool.practica5_intentsexplicitos.R;
import com.tokioschool.practica5_intentsexplicitos.databinding.ActivityHomeBinding;
import com.tokioschool.practica5_intentsexplicitos.home.fragments.HomeFragment;
import com.tokioschool.practica5_intentsexplicitos.home.fragments.RentCarFragment;
import com.tokioschool.practica5_intentsexplicitos.login.fragments.LoginFragment;
import com.tokioschool.practica5_intentsexplicitos.model.User;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;
    private String name;
    private User user;
    private String password;

    private Bundle bundle;
    private HomeFragment homeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getNameAndPass();
        inflateFragment();
        listeners();
    }

    private void listeners() {
        binding.mainActivityToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.menu_coche){
                    Log.i("Fragment","PULSADO");
                    homeFragment = (HomeFragment) getSupportFragmentManager().findFragmentById(R.id.homefragment_frame);
                    if (homeFragment!=null){
                        Log.i("Fragment","NO NULO");
                        homeFragment.fragmentRentCar();
                    }
                }
                if (item.getItemId() == R.id.menu_disney){
                    String url = "https://www.disneylandparis.com/";
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                }
                return false;
            }
        });
    }

    private void getNameAndPass() {
        try {
            bundle = new Bundle();
            bundle = getIntent().getExtras().getBundle(KEY_USER_BUNDLE);
            user = bundle.getParcelable(KEY_USER);
            if (user!=null){
                name = user.getUsername();
                password = user.getPassword();
                Log.i("Nombre","Nombre: "+name);
                Log.i("Pass","Contraseña: "+password);
            }else {
                Log.e("NULO","NULO");
            }


        }catch (Exception e){
            Log.i("Mensaje", "No hay password ni contraseña");
        }

    }
    private void inflateFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(binding.homefragmentFrame.getId(), new HomeFragment())
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }

}