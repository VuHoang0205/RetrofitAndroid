package com.example.mua_mua_thu.retrofitandroid;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mua_mua_thu.retrofitandroid.fragment.LoginFragment;
import com.example.mua_mua_thu.retrofitandroid.fragment.RegisterFragment;
import com.example.mua_mua_thu.retrofitandroid.fragment.Test1Fragment;

public class MainActivity extends AppCompatActivity{
    LoginFragment loginFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        showFragment1(new Test1Fragment());

    }

    private void init() {
        loginFragment = new LoginFragment();
    }

    public void showFragment1(Fragment fragment) {
        FragmentManager manager =getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(android.R.id.content, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void showFragment2(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(android.R.id.content, fragment).commit();
    }

}
