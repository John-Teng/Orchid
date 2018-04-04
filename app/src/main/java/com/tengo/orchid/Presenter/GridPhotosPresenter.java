package com.tengo.orchid.Presenter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.tengo.orchid.Model.DataChangedDelegate;
import com.tengo.orchid.Model.PhotoRepository;
import com.tengo.orchid.Model.PhotoRepository.PhotoAddedDelegate;
import com.tengo.orchid.Model.PhotoRepository.RetrievePhotosDelegate;
import com.tengo.orchid.Model.ROOM.Entities.Photo;
import com.tengo.orchid.View.PhotosTabFragment.GridPhotosPresenterDelegate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by johnteng on 2018-03-12.
 */

public class GridPhotosPresenter implements GridPhotosPresenterDelegate, PhotoAddedDelegate, RetrievePhotosDelegate {

    private List<Photo> mItems = new ArrayList<>();
    private DataChangedDelegate mDataChangedDelegate;
    private Context mContext;

    public GridPhotosPresenter(@NonNull Context context, @NonNull DataChangedDelegate delegate) {
        mContext = context;
        mDataChangedDelegate = delegate;
        PhotoRepository.getInstance(mContext).getAllPhotos(this);
    }

    @Override
    public String getThumbnail(int position) {
        if (position > mItems.size() - 1) {
            return null;
        } else {
            return mItems.get(position).path;
        }
    }

    @Override
    public int getRating(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public void addPhoto(Uri uri) {
        // TODO get the correct beauty rating and age nums
        Photo newPhoto = new Photo(4, 3, uri.toString());
        PhotoRepository.getInstance(mContext).insertPhoto(newPhoto, this);
    }

    @Override
    public void addPhotos(List<Uri> uris) {
        // TODO make this a batch call, so the UIDelegate doesnt keep calling for adapter refresh
        for (Uri uri : uris) {
            addPhoto(uri);
        }
    }

    @Override
    public void onPhotoInserted(Photo insertedPhoto) {
        mItems.add(insertedPhoto);
        if (mDataChangedDelegate != null) {
            mDataChangedDelegate.onDataChanged();
        }
    }

    @Override
    public void onPhotosRetrieved(List<Photo> photos) {
        mItems.clear();
        mItems.addAll(photos);
        if (mDataChangedDelegate != null) {
            mDataChangedDelegate.onDataChanged();
        }
    }
}
