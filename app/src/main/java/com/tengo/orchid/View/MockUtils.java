package com.tengo.orchid.View;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
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
        thumbnailList.add(new PhotoThumbnail(ThumbnailUtils.extractThumbnail(BitmapFactory
                .decodeResource(context.getResources(), R.drawable.p1), 200, 200)));
        thumbnailList.add(new PhotoThumbnail(ThumbnailUtils.extractThumbnail(BitmapFactory
                .decodeResource(context.getResources(), R.drawable.p2), 200, 200)));
        thumbnailList.add(new PhotoThumbnail(ThumbnailUtils.extractThumbnail(BitmapFactory
                .decodeResource(context.getResources(), R.drawable.p3), 200, 200)));
        thumbnailList.add(new PhotoThumbnail(ThumbnailUtils.extractThumbnail(BitmapFactory
                .decodeResource(context.getResources(), R.drawable.p4), 200, 200)));
        thumbnailList.add(new PhotoThumbnail(ThumbnailUtils.extractThumbnail(BitmapFactory
                .decodeResource(context.getResources(), R.drawable.p5), 200, 200)));
        thumbnailList.add(new PhotoThumbnail(ThumbnailUtils.extractThumbnail(BitmapFactory
                .decodeResource(context.getResources(), R.drawable.p6), 200, 200)));

        return thumbnailList;
    }
}
