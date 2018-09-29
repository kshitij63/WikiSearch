package com.example.kshitij.wikisearch.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.example.kshitij.wikisearch.Constants;
import com.example.kshitij.wikisearch.network.WikiRetrofit;
import com.example.kshitij.wikisearch.pojo.Page;
import com.example.kshitij.wikisearch.pojo.PageEntity;
import com.example.kshitij.wikisearch.pojo.WikiSearchObject;
import com.example.kshitij.wikisearch.repository.WikiRepository;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WikiViewModel extends AndroidViewModel {
    public MutableLiveData<List<PageEntity>> pages = new MutableLiveData<>();
    public MutableLiveData<String> error = new MutableLiveData<>();
    public WikiRepository repository;

    public WikiViewModel(Application application) {
        super(application);
        this.repository = new WikiRepository(application);
    }

    public LiveData<List<PageEntity>> getList() {
        return repository.getList();
    }


    public void getSearchResults(final String s, Context context) {
        repository.postPageList(s);
        WikiRetrofit.getInterface().getList(s).enqueue(new Callback<WikiSearchObject>() {
            @Override
            public void onResponse(Call<WikiSearchObject> call, Response<WikiSearchObject> response) {
                if (response.isSuccessful() && response.body().getQuery() != null) {
                    ArrayList<PageEntity> list=new ArrayList<>();
                    for (Page page : response.body().getQuery().getPages()) {
                        PageEntity entity = new PageEntity();
                        entity.setTag(s);

                        entity.setTitle(page.getTitle());
                        entity.setThumbnailUri(page.getThumbnail() != null ? page.getThumbnail().getSource() : "");
                        repository.insert(entity);
                        list.add(entity);
                    }
                    pages.postValue(list);

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
