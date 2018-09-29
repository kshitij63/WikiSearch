package com.example.kshitij.wikisearch.pojo;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class PageEntity {

    private String tag;

    @PrimaryKey
    @NonNull
    private String title;
    private String thumbnailUri;

    public String getThumbnailUri() {
        return thumbnailUri;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setThumbnailUri(String thumbnailUri) {
        this.thumbnailUri = thumbnailUri;
    }


}
