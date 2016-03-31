package com.xiaofei.indexrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by xiaofei on 2016/3/30 14:00.
 */
public class MainAdapter extends RecyclerViewAdapter<MainAdapter.MyViewHolder> {

    private Context context;

    public MainAdapter (Context context) {
        super();
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from (context).inflate (R.layout.view_content_item, null, false);
        return new MyViewHolder (inflate);
    }

    @Override
    public void onBindViewHolder (RecyclerView.ViewHolder holder, int position) {
        String name = getItem (position).getName ();
        ((MyViewHolder)holder).textView.setText (name);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private final TextView textView;

        public MyViewHolder (View itemView) {
            super (itemView);

            textView = (TextView) itemView.findViewById (R.id.tv);
        }
    }
}
