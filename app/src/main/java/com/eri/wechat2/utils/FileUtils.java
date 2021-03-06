package com.eri.wechat2.utils;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class FileUtils {

    public static String readJsonFile(Context context, String fileName) {

        StringBuilder builder = new StringBuilder();
        int id = context.getResources().getIdentifier(fileName, "raw", context.getPackageName());
        InputStream inputStream = context.getResources().openRawResource(id);
        InputStreamReader inputStreamReader;
        try {
            inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String content;
            while ((content = bufferedReader.readLine()) != null) {
                builder.append(content);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return builder.toString();


//        try {
//            int length = 0;
//            while (length == 0) {
//                length = inputStream.available();
//            }
//            byte[] bytes = new byte[length];
//            int key = inputStream.read(bytes);
//            content = new String(bytes);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return content;
    }


}
