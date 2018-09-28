package com.example.kshitij.wikisearch.ui;

import android.content.SearchRecentSuggestionsProvider;

import static android.content.SearchRecentSuggestionsProvider.DATABASE_MODE_QUERIES;

public class WikiHistoryProvider extends SearchRecentSuggestionsProvider {
    public final static String AUTHORITY = "com.example.kshitij.wikisearch.ui.WikiHistoryProvider";
    public final static int MODE = DATABASE_MODE_QUERIES;

    public WikiHistoryProvider() {
        setupSuggestions(AUTHORITY, MODE);
    }
}