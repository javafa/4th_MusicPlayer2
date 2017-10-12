package com.veryworks.android.musicplayer2;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.veryworks.android.musicplayer2.domain.Music;

import java.util.List;

/**
 * Created by pc on 10/12/2017.
 */

public class ListFragmentAdapter
        extends RecyclerView.Adapter<ListFragmentAdapter.Holder>{
    List<Music.Item> data;
    public ListFragmentAdapter(List<Music.Item> data) {
        this.data = data;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Music.Item item = data.get(position);
        holder.id.setText(item.id);
        holder.content.setText(item.title);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class Holder extends RecyclerView.ViewHolder{
        TextView id;
        TextView content;
        public Holder(View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id);
            content = itemView.findViewById(R.id.content);
        }
    }
}
