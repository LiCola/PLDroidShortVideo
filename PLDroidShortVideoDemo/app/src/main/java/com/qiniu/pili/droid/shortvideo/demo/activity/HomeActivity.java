package com.qiniu.pili.droid.shortvideo.demo.activity;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.qiniu.pili.droid.shortvideo.demo.R;
import com.qiniu.pili.droid.shortvideo.demo.activity.fragment.VideoFragment.OnListFragmentInteractionListener;
import com.qiniu.pili.droid.shortvideo.demo.model.VideoModel;
import com.qiniu.pili.droid.shortvideo.demo.utils.PermissionChecker;
import com.qiniu.pili.droid.shortvideo.demo.utils.RecordSettings;
import com.qiniu.pili.droid.shortvideo.demo.utils.ToastUtils;

public class HomeActivity extends AppCompatActivity implements OnListFragmentInteractionListener {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);
  }

  @Override
  public void onListFragmentInteraction(VideoModel item) {

  }


  private boolean isPermissionOK() {
    PermissionChecker checker = new PermissionChecker(this);
    boolean isPermissionOK = Build.VERSION.SDK_INT < Build.VERSION_CODES.M || checker.checkPermission();
    if (!isPermissionOK) {
      ToastUtils.s(this, "Some permissions is not approved !!!");
    }
    return isPermissionOK;
  }

  public void onClickCapture(View v) {
    if (isPermissionOK()) {
      jumpToCaptureActivity();
    }
  }

  public void jumpToCaptureActivity() {
    Intent intent = new Intent(HomeActivity.this, VideoRecordActivity.class);
    intent.putExtra(VideoRecordActivity.PREVIEW_SIZE_RATIO, RecordSettings.PREVIEW_SIZE_RATIO_TIPS_ARRAY.length-1);
    intent.putExtra(VideoRecordActivity.PREVIEW_SIZE_LEVEL, RecordSettings.PREVIEW_SIZE_LEVEL_TIPS_ARRAY.length-1);
    intent.putExtra(VideoRecordActivity.ENCODING_MODE, RecordSettings.ENCODING_MODE_LEVEL_TIPS_ARRAY.length-1);
    intent.putExtra(VideoRecordActivity.ENCODING_SIZE_LEVEL, RecordSettings.ENCODING_SIZE_LEVEL_TIPS_ARRAY.length-1);
    intent.putExtra(VideoRecordActivity.ENCODING_BITRATE_LEVEL, RecordSettings.ENCODING_BITRATE_LEVEL_TIPS_ARRAY.length-1);
    intent.putExtra(VideoRecordActivity.AUDIO_CHANNEL_NUM, RecordSettings.AUDIO_CHANNEL_NUM_TIPS_ARRAY.length-1);
    startActivity(intent);
  }
}
