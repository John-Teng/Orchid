package com.tengo.orchid.Model.ROOM;

import android.arch.persistence.room.RoomDatabase;

import com.tengo.orchid.Model.ROOM.DAOs.PhotoDao;
import com.tengo.orchid.Model.ROOM.Entities.Photo;

/**
 * Created by johnteng on 2018-03-25.
 */

@android.arch.persistence.room.Database(entities = {Photo.class}, version = 1)
public abstract class Database extends RoomDatabase {
    public abstract PhotoDao getPhotoDao();
}
