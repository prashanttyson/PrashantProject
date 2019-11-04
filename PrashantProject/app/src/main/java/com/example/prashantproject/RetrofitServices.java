package com.example.prashantproject;

import android.content.Context;

import com.example.prashantproject.Api.ApiInterface;
import com.example.prashantproject.Api.ApiUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class RetrofitServices {
    private static final String BASE_URL = ApiUtils.BASE_URL;

    private static Context ctx;

    private static ApiInterface REST_CLIENT;
    public static Retrofit retrofit = null;

    public RetrofitServices(Context ctx) {
        RetrofitServices.ctx = ctx;
        getRetrofitBuilder();

    }

    public static ApiInterface get() {
        if (null != REST_CLIENT) {
            return REST_CLIENT;
        } else {
            getRetrofitBuilder();
            return REST_CLIENT;
        }
    }

    static Gson gson = new GsonBuilder().setLenient().create();

    private static Retrofit getRetrofitBuilder() {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).client(getOkHttpClient(ctx)).addConverterFactory(GsonConverterFactory.create()).addConverterFactory(GsonConverterFactory.create(gson))

                .build();

        REST_CLIENT = retrofit.create(ApiInterface.class);

        return retrofit;
    }

    public static OkHttpClient getOkHttpClient(Context context) {

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder okClientBuilder = new OkHttpClient.Builder();
        okClientBuilder.addInterceptor(httpLoggingInterceptor);
        okClientBuilder.addInterceptor(headerAuthorizationInterceptor);
        //okClientBuilder.interceptors().add(httpLoggingInterceptor);

        okClientBuilder.connectTimeout(30, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS).writeTimeout(30, TimeUnit.SECONDS).retryOnConnectionFailure(true);





        return okClientBuilder.build();
    }


    private static Interceptor headerAuthorizationInterceptor = new Interceptor() {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Request request1 = request.newBuilder().header("Content-Type", "application/x-www-form-urlencoded").method(request.method(), request.body()).build();

            return chain.proceed(request1);
        }
    };

}

