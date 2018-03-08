package com.qiniu.pili.droid.shortvideo.demo.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by LiCola on 2018/3/7.
 */

public class SourceManager {

  private static SourceManager INSTANCE = new SourceManager();

  private static final String SOURCE_FILE = "video_sp_file";
  private static final String SOURCE_KEY = "video_source";

  public static SourceManager getInstance() {
    return INSTANCE;
  }

  public List<String> getSource(Context context) {
    SharedPreferences sp = context.getSharedPreferences(SOURCE_FILE, Context.MODE_PRIVATE);

    String sources = sp.getString(SOURCE_KEY, "");

    if (sources.equals("")) {
      return Collections.EMPTY_LIST;
    }

    String[] split = sources.split(",");
    List<String> result = new ArrayList<>(split.length);
    result.addAll(Arrays.asList(split));

    return result;
  }

  public boolean putSource(Context context, String data) {
    SharedPreferences sp = context.getSharedPreferences(SOURCE_FILE, Context.MODE_PRIVATE);

    String newStringSplit = sp.getString(SOURCE_KEY, "");
    if (newStringSplit.equals("")) {
      newStringSplit = data;
    } else {
      newStringSplit = data + "," + newStringSplit;
    }

    Editor edit = sp.edit();
    edit.putString(SOURCE_KEY, newStringSplit);
    return edit.commit();
  }


}
