package com.example.mua_mua_thu.retrofitandroid.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.mua_mua_thu.retrofitandroid.MainActivity;
import com.example.mua_mua_thu.retrofitandroid.R;

public class Test1Fragment extends Fragment {
    private View rootView;
    private Button btnRegister;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.layout_login, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnRegister = rootView.findViewById(R.id.btn_register);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterFragment houseDetailFragment = new RegisterFragment();


                Bundle bundle = new Bundle();
                bundle.putInt("Key", 1);
                houseDetailFragment.setArguments(bundle);
                ((MainActivity) getActivity()).showFragment1(houseDetailFragment);


            }
        });
    }
}
