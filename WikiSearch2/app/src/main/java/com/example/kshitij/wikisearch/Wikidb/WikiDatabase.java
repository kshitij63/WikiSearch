package com.example.kshitij.wikisearch.Wikidb;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.kshitij.wikisearch.pojo.PageEntity;

@Database(entities = {PageEntity.class}, version = 2)
public abstract class WikiDatabase extends RoomDatabase {

    public abstract WikiDao getWikiDao();

    private static volatile WikiDatabase INSTANCE;

    public static WikiDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (WikiDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WikiDatabase.class, "wiki_database")
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
