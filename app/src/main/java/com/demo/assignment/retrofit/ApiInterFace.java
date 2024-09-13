package com.demo.assignment.retrofit;

import com.demo.assignment.ResponseFetchUserList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterFace {
    @GET("users")
    Call<ResponseFetchUserList> getUsers(@Query("page") int page); // Use pagination if needed

}
