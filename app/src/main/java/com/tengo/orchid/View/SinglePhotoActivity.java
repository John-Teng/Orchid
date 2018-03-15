package com.tengo.orchid.View;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tengo.orchid.R;
import com.tengo.orchid.View.Adapters.SinglePhotoPagerAdapter;

public class SinglePhotoActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private SinglePhotoPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        mViewPager = (ViewPager)findViewById(R.id.single_photo_viewpager);
        mAdapter = new SinglePhotoPagerAdapter();
        mViewPager.setAdapter(mAdapter);
    }
}
