package com.tengo.orchid.Presenter;

import android.graphics.Bitmap;

/**
 * Created by johnteng on 2018-03-12.
 */

public interface PhotosDelegate {
    Bitmap getThumbnail(int position);
    Bitmap getImage(int position);
    int getRating(int position);
    int getListSize();
}