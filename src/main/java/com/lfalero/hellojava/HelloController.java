package com.lfalero.hellojava;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("ux")
@Log4j2
public class HelloController {

    private HelloService service;

    public HelloController() {
        String svc = "bs-hello-java";
        String namespace = "ocp-utils";
        String API_BASE_URL = "http://"+svc+"."+namespace+".svc.cluster.local:8080";
        log.info("API_BASE_URL: " + API_BASE_URL);

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        service = retrofit.create(HelloService.class);
    }

    @GetMapping("/hello")
    public List<UxResponseDto> hello() throws IOException {

        Call<BsResponseDto> retrofitCall = service.getHello();
        log.info("API_URL: " + retrofitCall.request().url());
        Response<BsResponseDto> response = retrofitCall.execute();

        if (!response.isSuccessful()) {
            throw new IOException(response.errorBody() != null ? response.errorBody().string() : "Unknown error");
        }

        String host = System.getenv().getOrDefault("HOSTNAME", "unknown");
        String description = "Hello world from host API UX";

        List<UxResponseDto> responseDtos = new ArrayList<>();
        responseDtos.add(new UxResponseDto("UX", new BsResponseDto(host, description)));
        responseDtos.add(new UxResponseDto("BS", response.body()));
        return responseDtos;
    }
}
