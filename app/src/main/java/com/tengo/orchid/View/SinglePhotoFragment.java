package com.tengo.orchid.View;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tengo.orchid.Presenter.SinglePhotoPresenter;
import com.tengo.orchid.R;
import com.tengo.orchid.View.Adapters.SinglePhotoPagerAdapter;

/**
 * Created by johnteng on 2018-03-10.
 */

public class SinglePhotoFragment extends android.support.v4.app.Fragment
        implements SinglePhotoPagerAdapter.SinglePhotoDelegate {

    public interface SinglePhotoPresenterDelegate {
        Bitmap getImage(int position);

        int getCount();
    }

    private static final int NUM_LOADED_IMAGES = 5;
    private ViewPager mViewPager;
    private SinglePhotoPagerAdapter mAdapter;
    private SinglePhotoPresenterDelegate mPresenterDelegate;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override // SHOULDNT BE NEEDED
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_single_photo, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View view = getView();
        if (view == null) {
            return;
        }
        mPresenterDelegate = new SinglePhotoPresenter(getContext());
        mViewPager = (ViewPager) view.findViewById(R.id.single_photo_viewpager);
        mViewPager.setOffscreenPageLimit(NUM_LOADED_IMAGES);
        mAdapter = new SinglePhotoPagerAdapter(this);
        mViewPager.setAdapter(mAdapter);

        Bundle args = getArguments();
        if (args != null) {
            int photoId = args.getInt(getString(R.string.param_photo_id));
            mViewPager.setCurrentItem(photoId);
        }
    }

    @Override
    public Bitmap getImage(int position) {
        return mPresenterDelegate.getImage(position);
    }

    @Override
    public int getCount() {
        return mPresenterDelegate.getCount();
    }
}
