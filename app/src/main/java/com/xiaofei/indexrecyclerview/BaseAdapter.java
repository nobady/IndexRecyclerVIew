package com.xiaofei.indexrecyclerview;

import android.support.v7.widget.RecyclerView;

import com.xiaofei.indexrecyclerview.pinyin.CharacterParser;
import com.xiaofei.indexrecyclerview.pinyin.PinyinComparator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by xiaofei on 2016/3/30 10:07.
 */
public abstract class BaseAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    private ArrayList<ModelData> itemsData = new ArrayList<> ();
    private CharacterParser characterParser = CharacterParser.getInstance();
    private PinyinComparator pinyinComparator = new PinyinComparator();
    public BaseAdapter () {
        setHasStableIds (true);
    }

    //对集合进行排序
    public void sortList(){
        for(int i = 0;i<itemsData.size ();i++){
            String selling = characterParser.getSelling (itemsData.get (i).getName ());
            String sortString = selling.substring (0, 1).toUpperCase ();
            if(sortString.matches ("[A-Z]")){
                itemsData.get (i).setSortLetters (sortString.toUpperCase ());
            }else {
                itemsData.get (i).setSortLetters ("#");
            }
        }
        Collections.sort (itemsData,pinyinComparator);
    }

    public ArrayList<ModelData> getItemsData () {
        return itemsData;
    }

    public int getPositionForSection(char section) {
        for (int i = 0; i < getItemCount(); i++) {
            String sortStr = itemsData.get(i).getSortLetters();
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == section) {
                return i;
            }
        }
        return -1;

    }

    public void add(ModelData data){
        itemsData.add (data);
        sortList();
        notifyDataSetChanged ();
    }

    public void add(ModelData data,int index){
        itemsData.add (index,data);
        sortList();
        notifyDataSetChanged ();
    }

    public void setItemsData(ArrayList<ModelData> objs){
        if(objs!=null){
            itemsData = objs;
            sortList();
            notifyDataSetChanged ();
        }
    }

    public void addAll(Collection<? extends ModelData> objs){
        if(objs!=null){
            itemsData.addAll (objs);
            sortList();
            notifyDataSetChanged ();
        }
    }

    public void addAll(ModelData... objs){
        addAll (Arrays.asList (objs));
    }
    /**
     * 清除数据
     * @param isNotify  true 表示通知adapter
     */
    public void clear(boolean isNotify){
        itemsData.clear ();
        if(isNotify){
            sortList();
            notifyDataSetChanged ();
        }
    }



    public void remove(ModelData data){
        itemsData.remove (data);
        sortList();
        notifyDataSetChanged ();
    }

    @Override
    public int getItemCount () {
        return itemsData.size ();
    }

    public ModelData getItem(int positon){
        return itemsData.get (positon);
    }

    @Override
    public long getItemId (int position) {
        return getItem (position).hashCode ();
    }

}
