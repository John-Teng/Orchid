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

import com.tengo.orchid.Presenter.GridPhotosPresenter;
import com.tengo.orchid.Presenter.SinglePhotoPresenter;
import com.tengo.orchid.R;

/**
 * Created by johnteng on 2018-03-13.
 */

public class SinglePhotoPagerAdapter extends PagerAdapter {

    public interface SinglePhotoPresenterDelegate {
        Bitmap getImage(int position);
    }

    private SinglePhotoPresenterDelegate mPresenter;

    public SinglePhotoPagerAdapter(@Nullable SinglePhotoPresenterDelegate presenter) {
        mPresenter = presenter;
    }

    public void setPresenter(@Nullable SinglePhotoPresenterDelegate presenter) {
        mPresenter = presenter;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (LinearLayout) object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        LayoutInflater inflater = (LayoutInflater) container.getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewLayout = inflater.inflate(R.layout.gallery_photo, container,
                false);
        ImageView imageView = (ImageView) viewLayout.findViewById(R.id.single_photo);

        if (mPresenter != null) {
            imageView.setImageBitmap(mPresenter.getImage(position));
        }

        return viewLayout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
        object = null;
    }


}
