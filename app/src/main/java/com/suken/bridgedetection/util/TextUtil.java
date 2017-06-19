package com.suken.bridgedetection.util;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.internal.LinkedTreeMap;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by fengjigang on 15/1/27.
 */
public class TextUtil {
    public static String convertTemp(String origin) {
        if (!TextUtils.isEmpty(origin)) {
            return origin.replace("度", "℃");
        }
        return "";
    }

    public static String trimBlankSpace(String data) {
        if (data.contains("  ")) {
            int index = data.indexOf("  ");
            StringBuilder before = new StringBuilder(data.replace("  ", "").substring(0, index));
            StringBuilder after = new StringBuilder(data.replace("  ", "").substring(index));
            return before + "  " + after;
        }
        return data;
    }

    /**
     * 判断字符串是否为null 或者 长度为0 或者 只包含空字符
     *
     * @param pString
     * @return
     */
    public static boolean isEmptyString(String pString) {
        if (pString == null) {
            return true;
        }
        if (pString.length() == 0 || pString.trim().length() == 0) {
            return true;
        }
        return false;
    }
    /**
     * 验证某对象是否为空
     *
     * @return
     */
    public static boolean isEmptyObjects(Object... objs) {
        if (objs == null)
            return true;
        for (Object obj : objs) {
            if (obj == null || "".equals(obj)) {
                return true;
            }
        }

        return false;
    }


    public static void setResourceSiteIcon(ImageView iv_source, String source_name) {

        if (TextUtils.isEmpty(source_name)) {
            return;
        }
    }

    public static String trimEnterInContent(String[] split) {
        StringBuilder _StringB = new StringBuilder();
        for (int i = 0; i < split.length; i++) {
            if (i != split.length - 1) {
                _StringB.append(split[i] + "\n");
            } else {
                _StringB.append(split[i]);
            }
        }
        return _StringB.toString();
    }

    /**
     * 获取inputStream中的数据
     *
     * @param in
     * @return
     */
    public static String getResponseContent(InputStream in) {
        StringBuffer sb = new StringBuffer();
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        try {
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            Logger.i("jigang", "gzip json=" + new String(sb.toString().getBytes(), "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * InputStream 转换成byte[]
     *
     * @param input
     * @return
     * @throws IOException
     */
    public static byte[] toByteArray(InputStream input) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int n = 0;
        while (-1 != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
        }
        return output.toByteArray();
    }

    //把 所有的半角符号转化为全角符号
    public static String ToDBC(String input) {
        char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 12288) {
                c[i] = (char) 32;
                continue;
            }
            if (c[i] > 65280 && c[i] < 65375)
                c[i] = (char) (c[i] - 65248);
        }
        return new String(c);
    }



    /**
     * 专辑列表获取对应的背景
     *
     * @return
     */
    public static int getSpecialBgPic(int positon) {
        return 0;
    }




    /**
     * 判断一个List 是否为null 或者是否长度为0
     *
     * @param list
     * @return
     */
    public static boolean isListEmpty(List list) {
        if (list == null) {
            return true;
        }
        if (list.size() == 0) {
            return true;
        }
        return false;
    }




}
