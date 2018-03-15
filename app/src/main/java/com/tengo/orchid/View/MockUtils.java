package com.tengo.orchid.View;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;

import com.tengo.orchid.Model.PhotoThumbnail;
import com.tengo.orchid.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by johnteng on 2018-03-13.
 */

public class MockUtils {
    // Mocks PhotoThumbnail bitmaps by converting them from drawables
    public static List<PhotoThumbnail> mockPhotoThumbnails(@NonNull Context context) {
        List<PhotoThumbnail> thumbnailList = new ArrayList<>();
        if (context == null) {
            return thumbnailList;
        }
        thumbnailList.add(new PhotoThumbnail(BitmapFactory
                .decodeResource(context.getResources(), R.drawable.ic_add_image)));
        thumbnailList.add(new PhotoThumbnail(BitmapFactory
                .decodeResource(context.getResources(), R.drawable.ic_profile)));
        thumbnailList.add(new PhotoThumbnail(BitmapFactory
                .decodeResource(context.getResources(), R.drawable.ic_add_image)));
        thumbnailList.add(new PhotoThumbnail(BitmapFactory
                .decodeResource(context.getResources(), R.drawable.ic_album)));
        thumbnailList.add(new PhotoThumbnail(BitmapFactory
                .decodeResource(context.getResources(), R.drawable.ic_image)));
        thumbnailList.add(new PhotoThumbnail(BitmapFactory
                .decodeResource(context.getResources(), R.drawable.ic_take_picture)));

        return thumbnailList;
    }
}
