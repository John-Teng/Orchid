package com.tengo.orchid.Model;

import android.graphics.Bitmap;

/**
 * Created by johnteng on 2018-03-13.
 */

public class PhotoThumbnail {
    private Bitmap mThumbnail;

    public PhotoThumbnail(Bitmap thumbnail) {
        mThumbnail = thumbnail;
    }
    public Bitmap getThumbnail() {
        return mThumbnail;
    }
}
