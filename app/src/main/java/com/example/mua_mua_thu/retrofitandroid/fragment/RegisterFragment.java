package com.example.mua_mua_thu.retrofitandroid.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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
import android.widget.Toast;

import com.example.mua_mua_thu.retrofitandroid.MainActivity;
import com.example.mua_mua_thu.retrofitandroid.R;
import com.example.mua_mua_thu.retrofitandroid.retrofit.APIUtils;
import com.example.mua_mua_thu.retrofitandroid.retrofit.DataClient;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

public class RegisterFragment extends Fragment {
    private static final int REQUEST_CODE_HINH_ANH = 100;
    private View view;
    private ImageView imageView;
    private EditText edtUser, edtPassword;
    private Button btnXacNhan, btnCancel;
    private String realpath = "";
    private String taikhoan, matkhau;
    private RelativeLayout relativeLayout;
    private LoginFragment loginFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_register, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        register();
    }

    private void init() {
        imageView = view.findViewById(R.id.imageView);
        edtUser = view.findViewById(R.id.edt_user2);
        edtPassword = view.findViewById(R.id.edt_pasword_2);
        btnXacNhan = view.findViewById(R.id.btn_login_2);
        btnCancel = view.findViewById(R.id.btn_huy_2);
        relativeLayout = view.findViewById(R.id.rll);
        Bundle bundle = getArguments();
        if (bundle!=null) {
           int n = bundle.getInt("Key");
           changeColor();

        }


    }

    private void register() {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/");
                startActivityForResult(intent, REQUEST_CODE_HINH_ANH);
            }
        });
        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taikhoan = edtUser.getText().toString();
                matkhau = edtPassword.getText().toString();
                if (taikhoan.length() > 0 && matkhau.length() > 0) {
                    File file = new File(realpath);
                    String file_path = file.getAbsolutePath();
                    Log.d("vu", "onClick: " + file_path);
                    String[] mangTenFile = file_path.split("\\.");
                    file_path = mangTenFile[0] + System.currentTimeMillis() + "." + mangTenFile[1];
                    Log.d("vu", "onClick: " + file_path);
                    RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                    MultipartBody.Part body = MultipartBody.Part.createFormData("upload_file", file_path, requestBody);
                    DataClient dataClient = APIUtils.getData();
                    // hung du lieu ve
                    Call<String> callback = dataClient.uploadPhoto(body);
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            if (response != null) {
                                String message = response.body();
                                Log.d("vu", "onResponse: " + message);
                                if (message.length() > 0) {
                                    DataClient insertData = APIUtils.getData();
                                    Call<String> callback = insertData.insertData(taikhoan, matkhau, APIUtils.BaseUrl
                                            + "image/" + message);
                                    callback.enqueue(new Callback<String>() {
                                        @Override
                                        public void onResponse(Call<String> call, Response<String> response) {
                                            String result = response.body();
                                            Log.d("insert", "onResponse: " + result);
                                            if (result.equals("Success")) {
                                                Toast.makeText(getContext(), "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                                                ((MainActivity) getActivity()).showFragment2(new LoginFragment());

                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<String> call, Throwable t) {
                                            Log.d("error", "onFailure: " + t.getMessage());
                                        }
                                    });
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Log.d("vu2", "onFailure: " + t.getMessage());
                        }
                    });
                } else {
                    Toast.makeText(getContext(), "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_HINH_ANH && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            realpath = getRealPathFromURI(uri);
            try {
                InputStream inputStream = getActivity().getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public String getRealPathFromURI(Uri contentUri) {
        String path = null;
        String[] proj = {MediaStore.MediaColumns.DATA};
        Cursor cursor = getActivity().getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
            path = cursor.getString(column_index);
        }
        cursor.close();
        return path;
    }

    public void changeColor() {
        edtUser.setText("Hoàng Tuấn Vũ");
    }
}
