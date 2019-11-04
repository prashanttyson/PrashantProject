package com.example.prashantproject.Api;

import com.example.prashantproject.Api.DetailsResponse;

import java.util.List;
import java.util.Observable;

import retrofit2.Call;
import retrofit2.http.GET;


public interface ApiInterface {



        @GET("cardData")
        Call<List<DetailsResponse> >details();



}