package com.qiniu.pili.droid.shortvideo.demo.shoot.fragment;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.qiniu.pili.droid.shortvideo.demo.R;
import com.qiniu.pili.droid.shortvideo.demo.model.SourceManager;
import com.qiniu.pili.droid.shortvideo.demo.model.VideoModel;
import com.qiniu.pili.droid.shortvideo.demo.utils.PermissionChecker;
import com.qiniu.pili.droid.shortvideo.demo.utils.ToastUtils;
import com.qiniu.pili.droid.shortvideo.demo.view.VideoRecyclerViewAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p />
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class VideoFragment extends Fragment {

  private static final int mColumnCount = 2;
  private OnListFragmentInteractionListener mListener;

  private VideoRecyclerViewAdapter adapter;

  /**
   * Mandatory empty constructor for the fragment manager to instantiate the
   * fragment (e.g. upon screen orientation changes).
   */
  public VideoFragment() {
  }


  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

  }

  private boolean isPermissionOK() {
    PermissionChecker checker = new PermissionChecker(getActivity());
    boolean isPermissionOK =
        Build.VERSION.SDK_INT < Build.VERSION_CODES.M || checker.checkPermission();
    if (!isPermissionOK) {
      ToastUtils.s(getContext(), "Some permissions is not approved !!!");
    }
    return isPermissionOK;
  }

  @Override
  public void onResume() {
    super.onResume();
    if (isPermissionOK()){
      loadSource();
    }
  }

  private void loadSource() {
    List<String> sourceUrl = SourceManager.getInstance().getSource(getContext());

    List<VideoModel> videoModels = new ArrayList<>(sourceUrl.size());
    int index = 0;

    for (String item : sourceUrl) {
      videoModels.add(
          new VideoModel("初始视频" + index, "封面图片",
              item));
      index++;
    }
    adapter.setValues(videoModels);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_video_grid, container, false);

    // Set the adapter
    if (view instanceof RecyclerView) {
      Context context = view.getContext();
      RecyclerView recyclerView = (RecyclerView) view;
      if (mColumnCount <= 1) {
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
      } else {
        recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
      }
      adapter = new VideoRecyclerViewAdapter(mListener);
      recyclerView.setAdapter(adapter);
    }
    return view;
  }


  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    if (context instanceof OnListFragmentInteractionListener) {
      mListener = (OnListFragmentInteractionListener) context;
    } else {
      throw new RuntimeException(context.toString()
          + " must implement OnListFragmentInteractionListener");
    }
  }

  @Override
  public void onDetach() {
    super.onDetach();
    mListener = null;
  }

  /**
   * This interface must be implemented by activities that contain this
   * fragment to allow an interaction in this fragment to be communicated
   * to the activity and potentially other fragments contained in that
   * activity.
   * <p/>
   * See the Android Training lesson <a href=
   * "http://developer.android.com/training/basics/fragments/communicating.html"
   * >Communicating with Other Fragments</a> for more information.
   */
  public interface OnListFragmentInteractionListener {

    // TODO: Update argument type and name
    void onListFragmentInteraction(VideoModel item);
  }
}
