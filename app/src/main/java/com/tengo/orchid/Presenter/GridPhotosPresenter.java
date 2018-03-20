package com.tengo.orchid.Presenter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;

import com.tengo.orchid.Model.PhotoThumbnail;
import com.tengo.orchid.View.Adapters.GridPhotoAdapter;
import com.tengo.orchid.View.MockUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by johnteng on 2018-03-12.
 */

public class GridPhotosPresenter implements GridPhotoAdapter.GridPhotosPresenterDelegate {

    private List<PhotoThumbnail> mItems = new ArrayList<>();
    private Context mContext;

    public GridPhotosPresenter(@Nullable Context context) {
        mContext = context;
        mItems = MockUtils.mockPhotoThumbnails(context);
    }

    @Override
    public Bitmap getThumbnail(int position) {
        if (position > mItems.size() - 1) {
            return null;
        } else {
            return mItems.get(position).getThumbnail();
        }
    }

    @Override
    public Bitmap getImage(int position) {
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

    @Override
    public int getRating(int position) {
        return 0;
    }

    @Override
    public int getListSize() {
        return mItems.size();
    }
}
