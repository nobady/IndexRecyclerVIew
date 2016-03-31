package com.xiaofei.indexrecyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private IndexRecyclerView recyclerView;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);

        recyclerView = (IndexRecyclerView) findViewById (R.id.rv);

        MainAdapter mAdapter = new MainAdapter (this);
        mAdapter.addAll (getDummyDataSet ());


        recyclerView.setAdapter(mAdapter);
//        recyclerView.addItemDecoration(new DividerDecoration(this));

    }

    private ArrayList<ModelData> getDummyDataSet() {
        ArrayList<ModelData> modelDatas = new ArrayList<> ();
        String[] array = getResources ().getStringArray (R.array.animals);
        for(int i = 0;i<array.length;i++){
            ModelData data = new ModelData ();
            data.setName (array[i]);

            modelDatas.add (data);
        }
        return modelDatas;
    }
}
