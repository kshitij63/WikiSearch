package com.example.kshitij.wikisearch.viewmodels;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.example.kshitij.wikisearch.Constants;
import com.example.kshitij.wikisearch.network.WikiRetrofit;
import com.example.kshitij.wikisearch.pojo.Page;
import com.example.kshitij.wikisearch.pojo.WikiSearchObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WikiViewModel extends ViewModel {
    public MutableLiveData<List<Page>> pages = new MutableLiveData<>();
    public MutableLiveData<String> error = new MutableLiveData<>();



    public void getSearchResults(String s, Context context) {
        WikiRetrofit.getInterface().getList(s).enqueue(new Callback<WikiSearchObject>() {
            @Override
            public void onResponse(Call<WikiSearchObject> call, Response<WikiSearchObject> response) {
                if (response.isSuccessful() && response.body().getQuery() != null) {
                    pages.postValue(response.body().getQuery().getPages());

                } else {
                    error.postValue(Constants.ERROR_RESULT);
                }


            }

            @Override
            public void onFailure(Call<WikiSearchObject> call, Throwable t) {
                error.postValue(Constants.ERROR_NETWORK);
            }
        });
    }



}
