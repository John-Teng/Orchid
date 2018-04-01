package com.tengo.orchid.Presenter;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.tengo.orchid.Model.PhotoRepository;
import com.tengo.orchid.Model.PhotoThumbnail;
import com.tengo.orchid.Model.ROOM.Entities.Photo;
import com.tengo.orchid.View.MockUtils;
import com.tengo.orchid.View.PhotosTabFragment;

import java.util.ArrayList;
import java.util.List;

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
        // TODO get the correct beauty rating and age nums
        Photo newPhoto = new Photo(4, 3, uri.toString());
        PhotoRepository.getInstance(mContext).insertPhoto(newPhoto);
    }

    @Override
    public void addPhotos(List<Uri> uris) {
        for (Uri uri : uris) {
            addPhoto(uri);
        }
    }
}
