package com.suken.bridgedetection.util;

import android.text.TextUtils;


import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import static com.yuntongxun.ecdemo.ui.chatting.ImageGalleryFragment.i;

/**
 * Created by liangshuai on 2017/6/7.
 */

public class LetterUtil<T> {

    /**
     * 根据输入框中的值来过滤数据并更新ListView
     * @param filterStr
     */
    public  List<T> filterData(String filterStr,List<T> data) {
        //实例化汉字转拼音类
        CharacterParser characterParser = CharacterParser.getInstance();


        List<T> sourceDateList = filledData(data,characterParser);


        ArrayList<T> filterDateList = new ArrayList<T>();

        if (TextUtils.isEmpty(filterStr)) {
            return data;
        } else {
            filterDateList.clear();
            for (T sortModel : sourceDateList) {
                Field fieldzm = null;
                try {
                    fieldzm = sortModel.getClass().getDeclaredField("zmname");
                    fieldzm.setAccessible(true);
                    String name = fieldzm.get(sortModel).toString();
                    if (name.toUpperCase().contains(filterStr.toUpperCase())
                            || characterParser.getSelling(name).toUpperCase()
                            .startsWith(filterStr.toUpperCase())) {
                        filterDateList.add(sortModel);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        }

//        // 根据a-z进行排序
//        Collections.sort(filterDateList, pinyinComparator);
//        adapter.setData(filterDateList);
        return filterDateList;
    }



    /**
     * 为ListView填充数据
     * @param data
     * @return
     */


    private  List<T> filledData(List<T>  data,CharacterParser characterParser){

        for(T t:data){
            try {
                Field fieldzm = t.getClass().getDeclaredField("zmname");
                fieldzm.setAccessible(true);
                //汉字转换成拼音
                String pinyin = characterParser.getSelling(fieldzm.get(t).toString());
                String sortString = pinyin.substring(0, 1).toUpperCase();


                Field fields = t.getClass().getDeclaredField("sortLetters");
                fields.setAccessible(true);
                // 正则表达式，判断首字母是否是英文字母
                if(sortString.matches("[A-Z]")){
                    fields.set(t,sortString.toUpperCase());
                }else{
                    fields.set(t,"#");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
        return data;

    }

}
