package com.tengo.orchid.Presenter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.tengo.orchid.Model.PhotoRepository;
import com.tengo.orchid.Model.PhotoThumbnail;
import com.tengo.orchid.Model.ROOM.Entities.Photo;
import com.tengo.orchid.R;
import com.tengo.orchid.View.MockUtils;
import com.tengo.orchid.View.PhotosTabFragment;
import com.tengo.orchid.View.PhotosTabFragment.UICompletionDelegate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by johnteng on 2018-03-12.
 */

public class GridPhotosPresenter implements PhotosTabFragment.GridPhotosPresenterDelegate {
    public interface InsertionDelegate {
        void onPhotoInserted(Photo insertedPhoto);
    }

    private List<PhotoThumbnail> mItems = new ArrayList<>();
    private Context mContext;

    public GridPhotosPresenter(@NonNull Context context) {
        // TODO populate the list with db values
        mContext = context;
        mItems = MockUtils.mockPhotoThumbnails(mContext);
//        List<Photo> list = PhotoRepository.getInstance(mContext).getAllPhotos();
//        mapPhotosToThumbnails(list);
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

    private void updateThumbnailList(@NonNull List<Photo> newList) {
        // Figure out the ones we need and add them as PhotoThumbnails
        boolean match;
        for (Photo listItem : newList) {
            match = false;
            for (PhotoThumbnail thumbnail : mItems) {
                if (thumbnail.getId() == listItem.id) {
                    match = true;
                }
            }
            if (match) {
                continue;
            } else {
                addPhotoThumbnailFromPhoto(listItem);
            }
            if (mItems.size() == newList.size()) {
                // If we already added all new items, we can break early
                break;
            }
        }
    }

    private void addPhotoThumbnailFromPhoto(Photo photo) {
        try {
            // Should only be added if the bitmap is accessible
            Bitmap thumbnail = ThumbnailUtils.extractThumbnail(MediaStore.Images.Media.getBitmap(
                    mContext.getContentResolver(), Uri.parse(photo.path)), 200, 200);
            mItems.add(new PhotoThumbnail(photo.id, thumbnail));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getListSize() {
        return mItems.size();
    }

    @Override
    public void addPhoto(Uri uri, @Nullable final UICompletionDelegate delegate) {
        // TODO get the correct beauty rating and age nums
        Photo newPhoto = new Photo(4, 3, uri.toString());
        PhotoRepository.getInstance(mContext).insertPhoto(newPhoto, new InsertionDelegate() {
            @Override
            public void onPhotoInserted(Photo insertedPhoto) {
                addPhotoThumbnailFromPhoto(insertedPhoto);
                if (delegate != null) {
                    delegate.onCompleted(true);
                }
            }
        });
    }

    @Override
    public void addPhotos(List<Uri> uris, @Nullable UICompletionDelegate UICompletionDelegate) {
        // TODO make this a batch call, so the UIDelegate doesnt keep calling for adapter refresh
        for (Uri uri : uris) {
            addPhoto(uri, UICompletionDelegate);
        }
    }
}
