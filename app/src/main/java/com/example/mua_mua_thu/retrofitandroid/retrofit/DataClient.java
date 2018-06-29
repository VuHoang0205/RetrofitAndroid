package com.example.mua_mua_thu.retrofitandroid.retrofit;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface DataClient {
    // quan ly du lieu day len server
    // gui 1 loai du lieu khong phải kieu text su dung multipart
    @Multipart
    @POST("uploadImage.php")
    Call<String> uploadPhoto(@Part MultipartBody.Part photo);

    // Gui du lieu dang text
    @FormUrlEncoded
    @POST("insert.php")
    Call<String> insertData(@Field("taikhoan") String taikhoan,
                            @Field("matkhau") String matkhau,
                            @Field("hinhanh") String hinhanh);

}
