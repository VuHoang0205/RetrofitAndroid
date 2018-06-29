package com.example.mua_mua_thu.retrofitandroid.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.mua_mua_thu.retrofitandroid.ChangeData;
import com.example.mua_mua_thu.retrofitandroid.MainActivity;
import com.example.mua_mua_thu.retrofitandroid.R;

public class LoginFragment extends Fragment {
    private View view;
    private EditText edtUser, edtPassword;
    private Button btnLogin, btnRegister;
    private ChangeData changeData;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ChangeData) {
            changeData = (ChangeData) context;
        } else {
            throw new RuntimeException(context.toString() + "Can phai implement");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_login, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        register();
    }

    private void init() {
        edtUser = view.findViewById(R.id.edt_user);
        edtPassword = view.findViewById(R.id.edt_pasword);
        btnLogin = view.findViewById(R.id.btn_login);
        btnRegister = view.findViewById(R.id.btn_register);
    }

    private void register() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeData.changeData();
                RegisterFragment registerFragment = new RegisterFragment();
                ((MainActivity) getActivity()).showFragment1(registerFragment);

            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterFragment registerFragment = new RegisterFragment();
                ((MainActivity) getActivity()).showFragment1(registerFragment);
                Log.d("vu", "Chuyển màn hình thành công ");
            }
        });
    }

}
