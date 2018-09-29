package com.example.kshitij.wikisearch.ui;

import android.app.SearchManager;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.provider.SearchRecentSuggestions;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.kshitij.wikisearch.R;
import com.example.kshitij.wikisearch.pojo.Page;
import com.example.kshitij.wikisearch.pojo.PageEntity;
import com.example.kshitij.wikisearch.ui.adapter.WikiAdapter;
import com.example.kshitij.wikisearch.viewmodels.WikiViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    WikiViewModel model;
    WikiAdapter adapter;
    ProgressBar progressBar;
    List<PageEntity> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        progressBar = findViewById(R.id.progress);
        adapter = new WikiAdapter(this, new ArrayList<PageEntity>(), new AdapterClickInterface() {
            @Override
            public void getPosition(int i) {
                Log.e("title", list.get(i).getTitle());
                Intent intent = new Intent(MainActivity.this, DetailWebViewActivity.class);
                intent.putExtra("title", list.get(i).getTitle());
                startActivity(intent);
            }
        });
        RecyclerView recyclerView = findViewById(R.id.wikirecylerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        model = ViewModelProviders.of(this).get(WikiViewModel.class);

        model.getList().observe(this, new Observer<List<PageEntity>>() {
            @Override
            public void onChanged(@Nullable List<PageEntity> pageEntities) {
                Log.e("List is:", pageEntities.toString());
                adapter.setList(pageEntities);


                list = pageEntities;
                progressBar.setVisibility(View.GONE);

            }
        });

        model.pages.observe(this, new Observer<List<PageEntity>>() {
            @Override
            public void onChanged(@Nullable List<PageEntity> pages) {
                Log.e("List is", pages.toString());
                adapter.setList(pages);


                list = pages;
                progressBar.setVisibility(View.GONE);
            }
        });
        model.error.observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.search_menu, menu);


        MenuItem item = menu.findItem(R.id.search_icon_wiki);

        SearchManager manager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        android.support.v7.widget.SearchView searchView = (android.support.v7.widget.SearchView) item.getActionView();
        ;


        searchView.setSearchableInfo(manager.getSearchableInfo(MainActivity.this.getComponentName()));

        searchView.setIconified(false);
        return true;

    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_SEARCH)) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            SearchRecentSuggestions suggestions = new SearchRecentSuggestions(this,
                    WikiHistoryProvider.AUTHORITY, WikiHistoryProvider.MODE);
            suggestions.saveRecentQuery(query, null);
            model.getSearchResults(query, MainActivity.this);
            progressBar.setVisibility(View.VISIBLE);
        }
    }
}
