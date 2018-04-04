package com.tengo.orchid.Presenter;

import android.content.Context;
import android.support.annotation.NonNull;

import com.tengo.orchid.Model.DataChangedDelegate;
import com.tengo.orchid.Model.PhotoRepository;
import com.tengo.orchid.Model.PhotoRepository.RetrievePhotosDelegate;
import com.tengo.orchid.Model.ROOM.Entities.Photo;
import com.tengo.orchid.View.SinglePhotoFragment.SinglePhotoPresenterDelegate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by johnteng on 2018-03-19.
 */

public class SinglePhotoPresenter implements SinglePhotoPresenterDelegate, RetrievePhotosDelegate {
    private Context mContext;
    private DataChangedDelegate mDataChangedDelegate;
    private List<Photo> mPhotos = new ArrayList<>();

    public SinglePhotoPresenter(@NonNull Context context, @NonNull DataChangedDelegate delegate) {
        mContext = context;
        mDataChangedDelegate = delegate;
        // CACHE REPOSITORY DATA
        PhotoRepository.getInstance(mContext).getAllPhotos(this);
    }

    @Override
    public String getImage(int position) {
        if (mContext == null || position > mPhotos.size()) {
            return null;
        }
        return mPhotos.get(position).path;
    }

    @Override
    public int getCount() {
        return mPhotos.size();
    }

    @Override
    public void onPhotosRetrieved(List<Photo> photos) {
        mPhotos.clear();
        mPhotos.addAll(photos);
        // Need to sort this list because "ORDER BY" is broken in room
        Collections.sort(mPhotos, new Comparator<Photo>() {
            @Override
            public int compare(Photo photo, Photo t1) {
                return photo.id - t1.id;
            }
        });
        if (mDataChangedDelegate != null) {
            mDataChangedDelegate.onDataChanged();
        }
    }
}
