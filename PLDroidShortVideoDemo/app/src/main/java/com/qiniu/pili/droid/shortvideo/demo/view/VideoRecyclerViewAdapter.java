package com.qiniu.pili.droid.shortvideo.demo.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.qiniu.pili.droid.shortvideo.PLMediaFile;
import com.qiniu.pili.droid.shortvideo.PLVideoFrame;
import com.qiniu.pili.droid.shortvideo.demo.R;
import com.qiniu.pili.droid.shortvideo.demo.model.VideoModel;
import com.qiniu.pili.droid.shortvideo.demo.shoot.fragment.VideoFragment.OnListFragmentInteractionListener;
import com.qiniu.pili.droid.shortvideo.demo.utils.PixelUtils;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 */
public class VideoRecyclerViewAdapter extends
    RecyclerView.Adapter<VideoRecyclerViewAdapter.ViewHolder> {

  private Context mContext;
  private List<VideoModel> mValues = Collections.EMPTY_LIST;
  private final OnListFragmentInteractionListener mListener;
  private Handler mHandler;
  private Executor executor = Executors.newCachedThreadPool();


  public VideoRecyclerViewAdapter(Context context,
      OnListFragmentInteractionListener listener) {
    mContext = context;
    mListener = listener;
    mHandler = new Handler();
  }

  public void setValues(List<VideoModel> items) {
    mValues = items;
    this.notifyDataSetChanged();
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.fragment_video, parent, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(final ViewHolder holder, int position) {
    final VideoModel model = mValues.get(position);
    holder.mItem = model;

    executor.execute(new Runnable() {
      @Override
      public void run() {
        int heightPx = PixelUtils.dp2px(mContext, 160);
        int widthPx = heightPx * 9 / 16;//录制时是 16：9
        PLMediaFile mMediaFile = new PLMediaFile(model.url);
        PLVideoFrame videoFrameByIndex = mMediaFile
            .getVideoFrameByIndex(0, true, widthPx, heightPx);
        if (videoFrameByIndex == null) {
          return;
        }
        final Bitmap bmp = videoFrameByIndex.toBitmap();
        mHandler.post(new Runnable() {
          @Override
          public void run() {
            holder.imgCover.setImageBitmap(bmp);
          }
        });
      }
    });

    holder.mView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (null != mListener) {
          // Notify the active callbacks interface (the activity, if the
          // fragment is attached to one) that an item has been selected.
          mListener.onListFragmentInteraction(holder.mItem);
        }
      }
    });
  }

  @Override
  public int getItemCount() {
    return mValues.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {

    public final View mView;
    public final ImageView imgCover;
    public VideoModel mItem;

    public ViewHolder(View view) {
      super(view);
      mView = view;
      imgCover = view.findViewById(R.id.img_cover);
    }
  }
}
