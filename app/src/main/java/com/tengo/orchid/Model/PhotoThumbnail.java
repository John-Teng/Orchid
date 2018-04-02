package com.tengo.orchid.Model;

import android.graphics.Bitmap;

/**
 * Created by johnteng on 2018-03-13.
 */

public class PhotoThumbnail {
    private final int mId;
    private final Bitmap mThumbnail;

    public PhotoThumbnail(int id, Bitmap thumbnail) {
        mId = id;
        mThumbnail = thumbnail;
    }

    public Bitmap getThumbnail() {
        return mThumbnail;
    }

    public int getId(){
        return mId;
    }
}
