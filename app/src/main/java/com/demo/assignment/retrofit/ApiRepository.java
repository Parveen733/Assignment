package com.demo.assignment.retrofit;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.demo.assignment.ResponseFetchUserList;
import com.demo.assignment.UserTbl;
import com.demo.assignment.database.DbConfig;
import com.demo.assignment.database.DbDao;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiRepository {

    private ApiInterFace apiInterFace;
    private DbDao userDao;

    public ApiRepository(Application application) {
        DbConfig db = DbConfig.getDatabase(application);
        userDao = db.dbDao();
        apiInterFace = ApiClient.getClient().create(ApiInterFace.class); // Retrofit client
    }

    public LiveData<List<UserTbl>> getAllUsersFromDb() {
        return userDao.getAllUsers();
    }

    public void fetchDataFromApiAndStoreInDb(int page) {
        apiInterFace.getUsers(page).enqueue(new Callback<ResponseFetchUserList>() {
            @Override
            public void onResponse(Call<ResponseFetchUserList> call, Response<ResponseFetchUserList> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<ResponseFetchUserList.User> apiUsers = response.body().getData();
                    List<UserTbl> userTblList = new ArrayList<>();

                    for (ResponseFetchUserList.User apiUser : apiUsers) {
                        UserTbl userTbl = new UserTbl();
                        userTbl.setId(apiUser.getId());
                        userTbl.setEmail(apiUser.getEmail());
                        userTbl.setFirstName(apiUser.getFirstName());
                        userTbl.setLastName(apiUser.getLastName());
                        userTbl.setAvatar(apiUser.getAvatar());
                        userTblList.add(userTbl);
                    }

                    // Insert users into Room database
                    DbConfig.databaseWriteExecutor.execute(() -> userDao.insertUsers(userTblList));
                }
            }

            @Override
            public void onFailure(Call<ResponseFetchUserList> call, Throwable t) {
                // Handle failure
            }
        });
    }

}
