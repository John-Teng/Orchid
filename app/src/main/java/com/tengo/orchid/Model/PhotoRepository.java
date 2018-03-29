package com.tengo.orchid.Model;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.tengo.orchid.Model.ROOM.AppDatabase;
import com.tengo.orchid.Model.ROOM.DAOs.PhotoDao;
import com.tengo.orchid.Model.ROOM.Entities.Photo;

import java.util.List;

/**
 * Created by johnteng on 2018-03-26.
 */

public class PhotoRepository {
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
        return mPhotoDao.getAllPhotos();
    }

    public void insertPhoto(Photo newPhoto) {
        new insertAsyncTask(mPhotoDao).execute(newPhoto);
    }

    public void updatePhoto(Photo updatedPhoto) {
        mPhotoDao.update(updatedPhoto);
    }

    public void deletePhoto(Photo deletePhoto) {
        mPhotoDao.delete(deletePhoto);
    }

    private static class insertAsyncTask extends AsyncTask<Photo, Void, Void> {

        private PhotoDao mAsyncTaskDao;

        insertAsyncTask(PhotoDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Photo... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }


}
