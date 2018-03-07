package com.qiniu.pili.droid.shortvideo.demo.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.qiniu.pili.droid.shortvideo.demo.R;
import com.qiniu.pili.droid.shortvideo.demo.activity.fragment.VideoFragment.OnListFragmentInteractionListener;
import com.qiniu.pili.droid.shortvideo.demo.model.VideoModel;
import java.util.List;

/**
 */
public class VideoRecyclerViewAdapter extends
    RecyclerView.Adapter<VideoRecyclerViewAdapter.ViewHolder> {

  private final List<VideoModel> mValues;
  private final OnListFragmentInteractionListener mListener;

  public VideoRecyclerViewAdapter(List<VideoModel> items,
      OnListFragmentInteractionListener listener) {
    mValues = items;
    mListener = listener;
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.fragment_video, parent, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(final ViewHolder holder, int position) {
    holder.mItem = mValues.get(position);
//    holder.mIdView.setText(mValues.get(position).id);
//    holder.mContentView.setText(mValues.get(position).content);

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
    public final TextView txtTitle;
    public final ImageView imgCover;
    public VideoModel mItem;

    public ViewHolder(View view) {
      super(view);
      mView = view;
      txtTitle = view.findViewById(R.id.txt_title);
      imgCover = view.findViewById(R.id.img_cover);
    }
  }
}
