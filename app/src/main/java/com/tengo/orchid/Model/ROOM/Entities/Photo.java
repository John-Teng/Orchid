package com.tengo.orchid.Model.ROOM.Entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by johnteng on 2018-03-25.
 */

@Entity
public class Photo {
    @PrimaryKey(autoGenerate = true)
    public final int id = 0;
    public final int beautyRating;
    public final int age;
    public final String path;

    public Photo(int beautyRating, int age, String path) {
        this.beautyRating = beautyRating;
        this.age = age;
        this.path = path;
    }
}
