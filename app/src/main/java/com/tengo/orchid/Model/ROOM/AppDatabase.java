package com.tengo.orchid.Model.ROOM;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.tengo.orchid.Model.ROOM.DAOs.PhotoDao;
import com.tengo.orchid.Model.ROOM.Entities.Photo;

/**
 * Created by johnteng on 2018-03-25.
 */

@android.arch.persistence.room.Database(entities = {Photo.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase sInstance;

    public abstract PhotoDao getPhotoDao();

    public static AppDatabase getDatabase(final Context context) {
        if (sInstance == null) {
            synchronized (AppDatabase.class) {
                if (sInstance == null) {
                    sInstance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "app_database")
                            .build();
                }
            }
        }
        return sInstance;
    }
}
