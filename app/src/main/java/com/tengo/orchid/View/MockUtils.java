package com.tengo.orchid.View;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;

import com.tengo.orchid.Model.PhotoThumbnail;
import com.tengo.orchid.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by johnteng on 2018-03-13.
 */
public class MockUtils {
    // Mocks PhotoThumbnail bitmaps by converting them from drawables
    public static List<PhotoThumbnail> mockPhotoThumbnails(Context context) {
        List<PhotoThumbnail> thumbnailList = new ArrayList<>();
        if (context == null) {
            return thumbnailList;
        }
        thumbnailList.add(new PhotoThumbnail(-1, ThumbnailUtils.extractThumbnail(BitmapFactory
                .decodeResource(context.getResources(), R.drawable.p1), 200, 200)));
        thumbnailList.add(new PhotoThumbnail(-2, ThumbnailUtils.extractThumbnail(BitmapFactory
                .decodeResource(context.getResources(), R.drawable.p2), 200, 200)));
        thumbnailList.add(new PhotoThumbnail(-3, ThumbnailUtils.extractThumbnail(BitmapFactory
                .decodeResource(context.getResources(), R.drawable.p3), 200, 200)));
        thumbnailList.add(new PhotoThumbnail(-4, ThumbnailUtils.extractThumbnail(BitmapFactory
                .decodeResource(context.getResources(), R.drawable.p4), 200, 200)));
        thumbnailList.add(new PhotoThumbnail(-5, ThumbnailUtils.extractThumbnail(BitmapFactory
                .decodeResource(context.getResources(), R.drawable.p5), 200, 200)));
        thumbnailList.add(new PhotoThumbnail(-6, ThumbnailUtils.extractThumbnail(BitmapFactory
                .decodeResource(context.getResources(), R.drawable.p6), 200, 200)));

        return thumbnailList;
    }

    public static Bitmap mockP1(Context context) {
        if (context == null) {
            return null;
        }
        return BitmapFactory.decodeResource(context.getResources(), R.drawable.p1);
    }

    public static Bitmap mockP2(Context context) {
        if (context == null) {
            return null;
        }
        return BitmapFactory.decodeResource(context.getResources(), R.drawable.p2);
    }

    public static Bitmap mockP3(Context context) {
        if (context == null) {
            return null;
        }
        return BitmapFactory.decodeResource(context.getResources(), R.drawable.p3);
    }

    public static Bitmap mockP4(Context context) {
        if (context == null) {
            return null;
        }
        return BitmapFactory.decodeResource(context.getResources(), R.drawable.p4);
    }

    public static Bitmap mockP5(Context context) {
        if (context == null) {
            return null;
        }
        return BitmapFactory.decodeResource(context.getResources(), R.drawable.p5);
    }

    public static Bitmap mockP6(Context context) {
        if (context == null) {
            return null;
        }
        return BitmapFactory.decodeResource(context.getResources(), R.drawable.p6);
    }
}
