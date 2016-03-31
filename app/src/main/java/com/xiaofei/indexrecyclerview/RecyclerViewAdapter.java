package com.xiaofei.indexrecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter;

/**
 * Created by xiaofei on 2016/3/30 11:04.
 */
public abstract class RecyclerViewAdapter<M> extends BaseAdapter<RecyclerView.ViewHolder> implements
        StickyRecyclerHeadersAdapter<RecyclerView.ViewHolder>{

    @Override
    public long getHeaderId (int position) {
        return getItem (position).getSortLetters ().charAt (0);
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder (ViewGroup parent) {
        View view = LayoutInflater.from (parent.getContext ()).inflate (R.layout.view_header,parent,false);
        return new RecyclerView.ViewHolder (view) {
        };
    }

    @Override
    public void onBindHeaderViewHolder (RecyclerView.ViewHolder holder, int position) {
        TextView itemView = (TextView) holder.itemView;
        itemView.setText (String.valueOf (getItem (position).getSortLetters ().charAt (0)));
    }
}
