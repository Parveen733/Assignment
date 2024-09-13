package com.demo.assignment.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.demo.assignment.UserTbl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {UserTbl.class}, version = 1)
public abstract class DbConfig extends RoomDatabase {

//    public abstract DbDao dbDao();
//
//    private static volatile DbConfig INSTANCE;
//
//    public static DbConfig getDatabase(final Context context) {
//        if (INSTANCE == null) {
//            synchronized (DbConfig.class) {
//                if (INSTANCE == null) {
//                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
//                                    DbConfig.class, "app_database")
//                            .build();
//                }
//            }
//        }
//        return INSTANCE;
//    }
public abstract DbDao dbDao();

    // Singleton instance of the database
    private static volatile DbConfig INSTANCE;

    // Create an ExecutorService with a fixed number of threads
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    // Method to get the singleton instance of the database
    public static DbConfig getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (DbConfig.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    DbConfig.class, "user_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
