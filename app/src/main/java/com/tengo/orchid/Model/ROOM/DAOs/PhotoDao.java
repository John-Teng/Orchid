package com.tengo.orchid.Model.ROOM.DAOs;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.tengo.orchid.Model.ROOM.Entities.Photo;

import java.util.List;

/**
 * Created by johnteng on 2018-03-25.
 */

@Dao
public interface PhotoDao {
    @Insert
    void insert(Photo photo);

    @Update
    void update(Photo photo);

    @Delete
    void delete(Photo photo);

    @Query("SELECT * FROM photo")
    List<Photo> getAllPhotos();
}
