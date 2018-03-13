package com.tengo.orchid.Presenter;

import android.graphics.Bitmap;

/**
 * Created by johnteng on 2018-03-12.
 */

public interface PhotosTabDelegate {
    Bitmap getThumbnail();
    int getRating();
    int getListSize();
}