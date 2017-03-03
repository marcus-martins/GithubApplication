package com.github.githubapplication.services.rest;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;

/**
 * Created by marcus on 02/03/17.
 */

public class RestServiceGenerator {

    private static final String BASE_URL = "https://api.github.com/";

    private static RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory
            .createWithScheduler(Schedulers.io());

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(rxAdapter);

    private static Retrofit retrofit = builder.build();

    public static <S> S createService(
            Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }

}
