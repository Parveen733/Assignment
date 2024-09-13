package com.demo.assignment.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.demo.assignment.UserTbl;

import java.util.List;
@Dao
public interface DbDao {
    @Insert
    void insert(UserTbl userTbl);

    @Insert
    void insert(List<UserTbl> userTbls); // Method for inserting a list of UserTbl

    @Update
    void updateUser(UserTbl user);

    @Query("SELECT * FROM users")
    LiveData<List<UserTbl>> getAllUsers();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUsers(List<UserTbl> users);

}
