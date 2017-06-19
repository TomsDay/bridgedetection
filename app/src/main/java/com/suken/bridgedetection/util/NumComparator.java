package com.suken.bridgedetection.util;

import java.lang.reflect.Field;
import java.util.Comparator;

/**
 * Created by liangshuai on 2017/6/8.
 */

public class NumComparator<T> implements Comparator<T> {
    @Override
    public int compare(T o1, T o2) {
        Field fieldO1 = null;
        Field fieldO2 = null;
        try {
            fieldO1 = o1.getClass().getDeclaredField("commitNum");
            fieldO1.setAccessible(true);
            String o1num = fieldO1.get(o1).toString();

            fieldO1 = o2.getClass().getDeclaredField("commitNum");
            fieldO1.setAccessible(true);
            String o2num = fieldO1.get(o2).toString();
            return o2num.compareTo(o1num);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return 0;
    }
}
