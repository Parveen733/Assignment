package com.demo.assignment.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.demo.assignment.ResponseFetchUserList;
import com.demo.assignment.UserTbl;
import com.demo.assignment.retrofit.ApiRepository;

import java.util.List;

public class UserViewModel extends AndroidViewModel {
    private ApiRepository repository;
    private LiveData<List<UserTbl>> allUsers;

    public UserViewModel(@NonNull Application application) {
        super(application);
        repository = new ApiRepository(application);
        allUsers = repository.getAllUsersFromDb();
    }

    public LiveData<List<UserTbl>> getAllUsers() {
        return allUsers;
    }

    public void fetchUsersFromApi(int page) {
        repository.fetchDataFromApiAndStoreInDb(page);
    }

}
