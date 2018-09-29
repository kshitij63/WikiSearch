package com.example.kshitij.wikisearch.repository;

import android.app.Application;
import android.app.ListActivity;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;

import com.example.kshitij.wikisearch.Wikidb.WikiDao;
import com.example.kshitij.wikisearch.Wikidb.WikiDatabase;
import com.example.kshitij.wikisearch.pojo.PageEntity;

import java.util.List;

public class WikiRepository {

    private WikiDao wikiDao;
    private MutableLiveData<List<PageEntity>> pageListMutable;

    public WikiRepository(Application application) {
        wikiDao = WikiDatabase.getDatabase(application).getWikiDao();
        pageListMutable = new MutableLiveData<>();

    }


    public void postPageList(String s) {
        pageListMutable.postValue(wikiDao.getPageListByTag(s));
        //return pageListMutable;

    }

    public LiveData<List<PageEntity>> getList() {
        return pageListMutable;
    }

    public void insert(PageEntity entity){
       new  insertAsyncTask(wikiDao).execute(entity);
    }


    private static class insertAsyncTask extends AsyncTask<PageEntity, Void, Void> {

        private WikiDao mAsyncTaskDao;

        insertAsyncTask(WikiDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final PageEntity... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}