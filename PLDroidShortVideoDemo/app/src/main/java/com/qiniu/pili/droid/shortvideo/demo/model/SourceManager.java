package com.qiniu.pili.droid.shortvideo.demo.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    Set<String> set = sp.getStringSet(SOURCE_KEY, (Set<String>) Collections.EMPTY_SET);

    List<String> result = new ArrayList<>(set.size());
    result.addAll(set);
    result.add("http://shortvideo.pdex-service.com/Fr8TX0YWL4CzdN9Gj9W1mlCEa_wB");
    return result;
  }

  public boolean putSource(Context context, String data) {
    SharedPreferences sp = context.getSharedPreferences(SOURCE_FILE, Context.MODE_PRIVATE);

    Set<String> oldSet = sp.getStringSet(SOURCE_KEY, (Set<String>) Collections.EMPTY_SET);

    Set<String> newSet=new HashSet<>(oldSet);
    newSet.add(data);
    Editor edit = sp.edit();
    edit.putStringSet(SOURCE_KEY, newSet);
    return edit.commit();
  }


}
