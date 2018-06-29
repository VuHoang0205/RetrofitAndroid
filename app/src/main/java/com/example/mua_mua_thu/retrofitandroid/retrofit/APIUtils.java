package com.example.mua_mua_thu.retrofitandroid.retrofit;

public class APIUtils {
    public static final String BaseUrl = "http://192.168.2.116/quanlysinhvien/";

    public static DataClient getData() {
        // nhan va gui du lieu chua du lieu trong DataClient
        return RetrofitClient.getClient(BaseUrl).create(DataClient.class);
    }
}
