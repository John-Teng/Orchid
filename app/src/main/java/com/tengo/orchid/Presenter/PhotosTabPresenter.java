package com.tengo.orchid.Presenter;

import android.graphics.Bitmap;

import com.tengo.orchid.Model.PhotoThumbnail;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by johnteng on 2018-03-12.
 */

public class PhotosTabPresenter implements PhotosTabDelegate {
    private List<PhotoThumbnail> mItems = new ArrayList<>();
    
    @Override
    public Bitmap getThumbnail() {
        return null;
    }

    @Override
    public int getRating() {
        return 0;
    }

    @Override
    public int getListSize() {
        return 0;
    }
}
