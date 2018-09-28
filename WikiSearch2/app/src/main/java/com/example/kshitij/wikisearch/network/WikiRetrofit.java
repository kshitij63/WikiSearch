package com.example.kshitij.wikisearch.network;

import com.example.kshitij.wikisearch.Constants;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WikiRetrofit {

    private WikiRetrofit() {

    }

    private static HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

    private static OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build();

    private static Retrofit retrofit = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_WIKI_SEARCH_URL)
            .client(client).build();


    public static WikiInterface getInterface() {
        return retrofit.create(WikiInterface.class);
    }
}
