package com.eri.wechat2.utils;

import android.content.Context;

import com.eri.wechat2.ui.entity.HotelEntity;
import com.google.gson.Gson;

public class JsonUtils {

    public static HotelEntity analysisJsonFile(Context context, String fileName){
        String content = FileUtils.readJsonFile(context,fileName);
        Gson gson = new Gson();
        HotelEntity entity = gson.fromJson(content, HotelEntity.class);
        return  entity;

    }
}
