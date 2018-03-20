package com.tengo.orchid.View;

import android.content.Context;
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

public class SinglePhotoFragment extends android.support.v4.app.Fragment {
    private ViewPager mViewPager;
    private SinglePhotoPagerAdapter mAdapter;
    private SinglePhotoPresenter mPresenter;

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
        return null;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View view = getView();
        if (view == null) {
            return;
        }

        mPresenter = new SinglePhotoPresenter(getContext());
        mViewPager = (ViewPager) view.findViewById(R.id.single_photo_viewpager);
        mAdapter = new SinglePhotoPagerAdapter(mPresenter);
        mViewPager.setAdapter(mAdapter);
    }


}
