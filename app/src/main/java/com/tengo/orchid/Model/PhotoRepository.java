package com.tengo.orchid.Model;

import android.content.Context;
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

    public PhotoRepository(@NonNull Context context) {
        AppDatabase db = AppDatabase.getDatabase(context);
        mPhotoDao = db.getPhotoDao();
    }

    public List<Photo> getAllPhotos() {
        return mPhotoDao.getAllPhotos();
    }

    // TODO Accesses to ROOM cannot be done on UI thread, use Async Task here
    public void insertPhoto(Photo newPhoto) {
        mPhotoDao.insert(newPhoto);
    }

    public void updatePhoto(Photo updatedPhoto) {
        mPhotoDao.update(updatedPhoto);
    }

    public void deletePhoto(Photo deletePhoto) {
        mPhotoDao.delete(deletePhoto);
    }


}
