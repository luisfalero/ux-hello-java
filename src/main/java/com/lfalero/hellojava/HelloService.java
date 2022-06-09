package com.lfalero.hellojava;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface HelloService {

    @GET("bs/hello")
    @Headers({"Accept: application/json"})
    Call<BsResponseDto> getHello();
}
