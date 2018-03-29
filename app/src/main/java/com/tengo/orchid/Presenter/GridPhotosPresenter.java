package com.tengo.orchid.Presenter;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.tengo.orchid.Model.PhotoRepository;
import com.tengo.orchid.Model.PhotoThumbnail;
import com.tengo.orchid.Model.ROOM.Entities.Photo;
import com.tengo.orchid.View.MockUtils;
import com.tengo.orchid.View.PhotosTabFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by johnteng on 2018-03-12.
 */

public class GridPhotosPresenter implements PhotosTabFragment.GridPhotosPresenterDelegate {

    private List<PhotoThumbnail> mItems = new ArrayList<>();
    private Context mContext;

    public GridPhotosPresenter(@NonNull Context context) {
        mItems = MockUtils.mockPhotoThumbnails(context);
        mContext = context;
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

    @Override
    public void addPhoto(Uri uri) {
        // TODO find better solution than using rng for photo ids
        final int random = new Random().nextInt(9999);
        Photo newPhoto = new Photo(random, 4, 3, uri.toString());
        PhotoRepository.getInstance(mContext).insertPhoto(newPhoto);
    }

    @Override
    public void addPhotos(List<Uri> uris) {
        for (Uri uri : uris) {
            addPhoto(uri);
        }
    }
}
