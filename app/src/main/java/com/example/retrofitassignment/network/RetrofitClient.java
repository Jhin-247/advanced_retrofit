package com.example.retrofitassignment.network;

import static com.example.retrofitassignment.network.RetrofitAPIService.BASE_URL;

import com.example.retrofitassignment.BuildConfig;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static RetrofitClient sInstance;
    private final RetrofitAPIService mService;

    private RetrofitClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG)
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .client(client)
                .build();
        mService = retrofit.create(RetrofitAPIService.class);
    }

    public static RetrofitClient getInstance() {
        synchronized (RetrofitClient.class) {
            if (sInstance == null) {
                sInstance = new RetrofitClient();
            }
            return sInstance;
        }
    }

    public RetrofitAPIService getService() {
        return mService;
    }

}
