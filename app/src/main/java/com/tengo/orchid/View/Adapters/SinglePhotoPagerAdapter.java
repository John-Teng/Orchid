package com.tengo.orchid.View.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.tengo.orchid.R;

/**
 * Created by johnteng on 2018-03-13.
 */

public class SinglePhotoPagerAdapter extends PagerAdapter {

    public interface SinglePhotoDelegate {
        Bitmap getImage(int position);

        int getCount();
    }

    private SinglePhotoDelegate mDelegate;

    public SinglePhotoPagerAdapter(@Nullable SinglePhotoDelegate delegate) {
        mDelegate = delegate;
    }

    public void setDelegate(@Nullable SinglePhotoDelegate delegate) {
        mDelegate = delegate;
    }

    @Override
    public int getCount() {
        if (mDelegate == null) {
            return 0;
        }
        return mDelegate.getCount();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) container.getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewLayout = inflater.inflate(R.layout.single_photo, container,
                false);
        ImageView imageView = (ImageView) viewLayout.findViewById(R.id.photo);
        if (mDelegate != null) {
            imageView.setImageBitmap(mDelegate.getImage(position));
        }
        container.addView(viewLayout);
        return viewLayout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
