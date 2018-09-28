package com.example.kshitij.wikisearch.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kshitij.wikisearch.R;
import com.example.kshitij.wikisearch.pojo.Page;
import com.example.kshitij.wikisearch.ui.AdapterClickInterface;
import com.squareup.picasso.Picasso;

import java.util.List;

public class WikiAdapter extends RecyclerView.Adapter<WikiAdapter.WikiHolder> {

    private List<Page> list;
    private Context context;
    private AdapterClickInterface clickInterface;

    public WikiAdapter(Context context, List<Page> list,
                       AdapterClickInterface adapterClickInterface) {
        this.context = context;
        this.list = list;
        clickInterface = adapterClickInterface;
    }

    @NonNull
    @Override
    public WikiHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.single_layout_wiki, viewGroup, false);
        WikiHolder holder = new WikiHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull WikiHolder wikiHolder, final int i) {
        Page page = list.get(i);
        wikiHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickInterface.getPosition(i);

            }
        });
        TextView title = wikiHolder.itemView.findViewById(R.id.wiki_title);
        AppCompatImageView imageView = wikiHolder.itemView.findViewById(R.id.wiki_image);
        if (page.getThumbnail()!=null) {
            Picasso.get().load(page.getThumbnail().getSource()).into(imageView);
        } else Picasso.get().load(R.drawable.na).into(imageView);
        title.setText(page.getTitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class WikiHolder extends RecyclerView.ViewHolder {

        public WikiHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public void setList(List<Page> list) {
        this.list = list;
        notifyDataSetChanged();
    }


}
