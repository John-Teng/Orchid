package com.tengo.orchid.Presenter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;

import com.tengo.orchid.Model.PhotoThumbnail;
import com.tengo.orchid.View.MockUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by johnteng on 2018-03-12.
 */

public class PhotosPresenter implements PhotosDelegate {
    private List<PhotoThumbnail> mItems = new ArrayList<>();
    private Context mContext;

    public PhotosPresenter(@Nullable Context context) {
        mContext = context;
        if (context != null) {
            mItems = MockUtils.mockPhotoThumbnails(context);
        }
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
        return null;
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
