package com.xiaofei.indexrecyclerview.pinyin;


import com.xiaofei.indexrecyclerview.ModelData;

import java.util.Comparator;

/**
 * 
 * @author xiaanming
 *
 */
public class PinyinComparator implements Comparator<ModelData> {

	@Override
	public int compare (ModelData lhs, ModelData rhs) {
		if(lhs.getSortLetters ().equals ("#")||rhs.getSortLetters ().equals ("#")){
			return -1;
		}
		return lhs.getSortLetters ().compareTo (rhs.getSortLetters ());
	}
}
