package com.example.kshitij.wikisearch.network;

import com.example.kshitij.wikisearch.pojo.WikiSearchObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WikiInterface {

    @GET("api.php?action=query&format=json&prop=pageimages%7Cpageterms&generator=prefixsearch&redirects=1&formatversion=2&piprop=thumbnail&pithumbsize=50&pilimit=10&wbptterms=description&gpslimit=10")
    Call<WikiSearchObject> getList(@Query("gpssearch") String string);
}
