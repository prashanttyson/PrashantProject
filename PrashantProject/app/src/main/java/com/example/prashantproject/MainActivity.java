package com.example.prashantproject;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prashantproject.Api.DetailsResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity  {

    RecyclerView recyclerView;
    private String TAG;
    private Context context;
    private List<DetailsResponse> detailsResponse = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) {
        }
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);



        setData();
        details();
        setList();
    }

    private void setList() {
        DetailsAdapter detailsAdapter = new DetailsAdapter(MainActivity.this, detailsResponse);
        recyclerView.setAdapter(detailsAdapter);
    }

    private void setData() {
        context = this;
        TAG = context.getClass().getSimpleName();

    }



    private void details() {


        final DetailsResponse responseObject = new DetailsResponse();
        try {
            RetrofitServices.get().details().enqueue(new Callback<List<DetailsResponse>>() {
                        @Override
                        public void onResponse(Call<List<DetailsResponse>> call, Response<List<DetailsResponse>> response) {
                            Log.e(TAG, "onResponse");
                            if (response.isSuccessful()) {
                                Log.e(TAG, "onResponse is Successful");

                                if (response.body() != null) {
                                    Log.e(TAG, "onResponse have something!");
                                    detailsResponse = response.body();
                                    setList();

                                } else {
                                    Log.e(TAG, "Response is null!");
                                }
                            } else {
                                Log.e(TAG, "Response not success!");
                            }
                        }

                        @Override
                        public void onFailure(Call<List<DetailsResponse>> call, Throwable t) {

                            Log.e(TAG, "onFailure Invoked!");
                            Log.e(TAG, "onFailure: " + t.getMessage());

                        }
                    });

        } catch (Exception ex) {

            ex.printStackTrace();
        }

    }


}
