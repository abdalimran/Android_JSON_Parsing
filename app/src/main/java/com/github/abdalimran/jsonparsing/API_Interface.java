package com.github.abdalimran.jsonparsing;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API_Interface {
    @GET("/json")
    Call<IPModelPOJO> getIPDetails();
}