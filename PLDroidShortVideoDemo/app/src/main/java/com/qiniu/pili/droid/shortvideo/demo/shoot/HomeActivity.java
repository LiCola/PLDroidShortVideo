package com.qiniu.pili.droid.shortvideo.demo.shoot;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.pili.pldroid.player.AVOptions;
import com.qiniu.pili.droid.shortvideo.demo.R;
import com.qiniu.pili.droid.shortvideo.demo.model.VideoModel;
import com.qiniu.pili.droid.shortvideo.demo.play.PLMediaPlayerActivity;
import com.qiniu.pili.droid.shortvideo.demo.shoot.fragment.VideoFragment.OnListFragmentInteractionListener;
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
    String videopath = item.url;
    if (!"".equals(videopath)) {
      jumpToPlayerActivity(videopath);
    }
  }


  private boolean isPermissionOK() {
    PermissionChecker checker = new PermissionChecker(this);
    boolean isPermissionOK =
        Build.VERSION.SDK_INT < Build.VERSION_CODES.M || checker.checkPermission();
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
    intent.putExtra(VideoRecordActivity.PREVIEW_SIZE_RATIO,
        RecordSettings.PREVIEW_SIZE_RATIO_TIPS_ARRAY.length - 1);
    intent.putExtra(VideoRecordActivity.PREVIEW_SIZE_LEVEL,
        RecordSettings.PREVIEW_SIZE_LEVEL_TIPS_ARRAY.length - 1);
    intent.putExtra(VideoRecordActivity.ENCODING_MODE,
        RecordSettings.ENCODING_MODE_LEVEL_TIPS_ARRAY.length - 2);
    intent.putExtra(VideoRecordActivity.ENCODING_SIZE_LEVEL,
        RecordSettings.ENCODING_SIZE_LEVEL_TIPS_ARRAY.length - 1);
    intent.putExtra(VideoRecordActivity.ENCODING_BITRATE_LEVEL,
        RecordSettings.ENCODING_BITRATE_LEVEL_TIPS_ARRAY.length - 1);
    intent.putExtra(VideoRecordActivity.AUDIO_CHANNEL_NUM,
        RecordSettings.AUDIO_CHANNEL_NUM_TIPS_ARRAY.length - 1);
    startActivity(intent);
  }


  public void jumpToPlayerActivity(String videoPath) {
    Intent intent = new Intent(this, PLMediaPlayerActivity.class);
    intent.putExtra("videoPath", videoPath);

    intent.putExtra("mediaCodec", AVOptions.MEDIA_CODEC_HW_DECODE);
    intent.putExtra("mediaCodec", AVOptions.MEDIA_CODEC_SW_DECODE);
    intent.putExtra("mediaCodec", AVOptions.MEDIA_CODEC_AUTO);
    intent.putExtra("liveStreaming", 0);//点播

    intent.putExtra("cache", true);
    intent.putExtra("loop", true);
    intent.putExtra("video-data-callback", true);
    intent.putExtra("audio-data-callback", true);
    intent.putExtra("disable-log", true);
    startActivity(intent);
  }

//  @Override
//  public boolean onCreateOptionsMenu(Menu menu) {
//    getMenuInflater().inflate(R.menu.menu_home, menu);
//    return true;
//  }

//  @Override
//  public boolean onOptionsItemSelected(MenuItem item) {
//    // Handle action bar item clicks here. The action bar will
//    // automatically handle clicks on the Home/Up button, so long
//    // as you specify a parent activity in AndroidManifest.xml.
//    int id = item.getItemId();
//
//    //noinspection SimplifiableIfStatement
//    if (id == R.id.action_settings) {
//
//      startActivity(new Intent(this, MainActivity.class));
//
//      return true;
//    }
//
//    return super.onOptionsItemSelected(item);
//  }
}
