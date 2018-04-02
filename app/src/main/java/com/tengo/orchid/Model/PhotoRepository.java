package com.tengo.orchid.Model;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.tengo.orchid.Model.ROOM.AppDatabase;
import com.tengo.orchid.Model.ROOM.DAOs.PhotoDao;
import com.tengo.orchid.Model.ROOM.Entities.Photo;
import com.tengo.orchid.Presenter.GridPhotosPresenter.InsertionDelegate;
import com.tengo.orchid.View.PhotosTabFragment.UICompletionDelegate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by johnteng on 2018-03-26.
 */

public class PhotoRepository {

    public interface dbQueryDelegate {
        void getPhotos(List<Photo> list);
    }

    private PhotoDao mPhotoDao;

    private static PhotoRepository sInstance;

    public static PhotoRepository getInstance(@NonNull Context context) {
        if (sInstance == null) {
            synchronized (PhotoRepository.class) {
                if (sInstance == null) {
                    sInstance = new PhotoRepository(context);
                }
            }
        }
        return sInstance;
    }

    private PhotoRepository(@NonNull Context context) {
        AppDatabase db = AppDatabase.getDatabase(context);
        mPhotoDao = db.getPhotoDao();
    }

    public List<Photo> getAllPhotos() {
        final List<Photo> photoList = new ArrayList<>();
        new retrieveAsyncTask(mPhotoDao, new dbQueryDelegate() {
            @Override
            public void getPhotos(List<Photo> list) {
                photoList.addAll(list);
            }
        }).execute();
        return photoList;
    }

    public void insertPhoto(Photo newPhoto, @Nullable InsertionDelegate insertionDelegate) {
        new insertAsyncTask(mPhotoDao, insertionDelegate).execute(newPhoto);
    }

    public void updatePhoto(Photo updatedPhoto, @Nullable UICompletionDelegate delegate) {
        // TODO wrap this in an asynctask object
        mPhotoDao.update(updatedPhoto);
    }

    public void deletePhoto(Photo deletePhoto, @Nullable UICompletionDelegate delegate) {
        // TODO wrap this in an asynctask object
        mPhotoDao.delete(deletePhoto);
    }

    private static class retrieveAsyncTask extends AsyncTask<Void, Void, List<Photo>> {
        private PhotoDao mAsyncTaskDao;
        private dbQueryDelegate mDelegate;

        retrieveAsyncTask(PhotoDao dao, @Nullable dbQueryDelegate delegate) {
            mAsyncTaskDao = dao;
            mDelegate = delegate;
        }

        @Override
        protected List<Photo> doInBackground(final Void... params) {
            return mAsyncTaskDao.getAllPhotos();
        }

        @Override
        protected void onPostExecute(List<Photo> list) {
            if (mDelegate != null) {
                mDelegate.getPhotos(list);
            }
        }
    }

    private static class insertAsyncTask extends AsyncTask<Photo, Void, Photo> {
        private PhotoDao mAsyncTaskDao;
        private InsertionDelegate mInsertionDelegate;

        insertAsyncTask(PhotoDao dao, @Nullable InsertionDelegate insertionDelegate) {
            mAsyncTaskDao = dao;
            mInsertionDelegate = insertionDelegate;
        }

        @Override
        protected Photo doInBackground(final Photo... params) {
            mAsyncTaskDao.insert(params[0]);
            return params[0];
        }

        @Override
        protected void onPostExecute(Photo newPhoto) {
            if (newPhoto == null) {
                // TODO handle this case
                return;
            }
            if (mInsertionDelegate != null) {
                mInsertionDelegate.onPhotoInserted(newPhoto);
            }
        }
    }
}
