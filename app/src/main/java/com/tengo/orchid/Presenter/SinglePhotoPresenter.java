package com.tengo.orchid.Presenter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;

import com.tengo.orchid.View.MockUtils;
import com.tengo.orchid.View.SinglePhotoFragment;

/**
 * Created by johnteng on 2018-03-19.
 */

public class SinglePhotoPresenter implements SinglePhotoFragment.SinglePhotoPresenterDelegate {
    Context mContext;

    public SinglePhotoPresenter(@Nullable Context context) {
        mContext = context;
    }

    @Override
    public Bitmap getImage(int position) {
        if (mContext == null) {
            return null;
        }
        switch (position) {
            case 0:
                return MockUtils.mockP1(mContext);
            case 1:
                return MockUtils.mockP2(mContext);
            case 2:
                return MockUtils.mockP3(mContext);
            case 3:
                return MockUtils.mockP4(mContext);
            case 4:
                return MockUtils.mockP5(mContext);
            case 5:
                return MockUtils.mockP6(mContext);
            default:
                return null;
        }
    }
}
