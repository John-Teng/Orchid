package com.tengo.orchid.Presenter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;

import com.tengo.orchid.Model.PhotoThumbnail;
import com.tengo.orchid.View.MockUtils;
import com.tengo.orchid.View.PhotosTabFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by johnteng on 2018-03-12.
 */

public class GridPhotosPresenter implements PhotosTabFragment.GridPhotosPresenterDelegate {

    private List<PhotoThumbnail> mItems = new ArrayList<>();

    public GridPhotosPresenter(@Nullable Context context) {
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
    public int getRating(int position) {
        return 0;
    }

    @Override
    public int getListSize() {
        return mItems.size();
    }
}
