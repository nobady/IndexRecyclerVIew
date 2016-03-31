package com.xiaofei.indexrecyclerview;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration;

/**
 * Created by xiaofei on 2016/3/30 14:26.
 */
public class IndexRecyclerView extends FrameLayout {

    private RecyclerView recyclerView;
    private TextView textView;
    private SideBar sideBar;
    private RecyclerViewAdapter mAdapter;

    public IndexRecyclerView (Context context) {
        super (context);
        init (context);
    }

    public IndexRecyclerView (Context context, AttributeSet attrs) {
        super (context, attrs);
        init (context);
    }

    public IndexRecyclerView (Context context, AttributeSet attrs, int defStyleAttr) {
        super (context, attrs, defStyleAttr);
        init (context);
    }

    public void setAdapter(RecyclerViewAdapter recyclerViewAdapter){
        mAdapter = recyclerViewAdapter;
        recyclerView.setAdapter (recyclerViewAdapter);
        final StickyRecyclerHeadersDecoration headersDecor = new StickyRecyclerHeadersDecoration(recyclerViewAdapter);
        recyclerView.addItemDecoration(headersDecor);

        mAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                headersDecor.invalidateHeaders();
            }
        });
    }

    private void init(Context context){
        LayoutInflater.from (context).inflate (R.layout.layout_index_recyclerview,this);

        recyclerView = (RecyclerView) findViewById (R.id.recyclerView);
        textView = (TextView) findViewById (R.id.contact_dialog);
        sideBar = (SideBar) findViewById (R.id.sidebar);

        sideBar.setTextView (textView);
        final LinearLayoutManager lManager = new LinearLayoutManager (context);
        recyclerView.setLayoutManager (lManager);

        recyclerView.setItemAnimator (new DefaultItemAnimator ());

        sideBar.setOnTouchingLetterChangedListener (new SideBar.OnTouchingLetterChangedListener () {
            @Override
            public void onTouchingLetterChanged (String s) {

                int position = mAdapter.getPositionForSection(s.charAt(0));
                if (position != -1) {
                    recyclerView.scrollToPosition(position);
                }
            }
        });
    }


    //给recyclerview设置监听器
    public void setOnItemClickListener(Context context, RecyclerItemClickListener.OnItemClickListener listener ){
        recyclerView.addOnItemTouchListener (new RecyclerItemClickListener (context,listener));
    }

}
