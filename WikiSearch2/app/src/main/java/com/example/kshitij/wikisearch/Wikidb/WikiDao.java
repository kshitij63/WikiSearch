package com.example.kshitij.wikisearch.Wikidb;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.kshitij.wikisearch.pojo.PageEntity;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;
@Dao
public interface WikiDao {


    @Query("SELECT DISTINCT * FROM PageEntity WHERE tag=:tag")
    List<PageEntity> getPageListByTag(String tag);

    @Insert(onConflict = REPLACE)
    void insert(PageEntity entity);

}
