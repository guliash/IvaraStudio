// Copyright (c) 2018 Yandex LLC. All rights reserved.
// Author: Olga Kim <tayrinn@yandex-team.ru>

package ru.volha.hustle.ivarastudio.data.repository.local;

import android.content.SharedPreferences;

import com.google.gson.Gson;

import javax.inject.Inject;

import io.reactivex.Single;
import ru.volha.hustle.ivarastudio.data.User;
import ru.volha.hustle.ivarastudio.data.repository.DataSource;

public class LocalDataSource implements DataSource {

    private static final String USER = "user_json";

    private final SharedPreferences mSharedPreferences;
    private final Gson mGson;

    @Inject
    public LocalDataSource(SharedPreferences sharedPreferences) {
        mSharedPreferences = sharedPreferences;
        mGson = new Gson();
    }


    @Override
    public Single<User> getUserInfo() {
        String json = mSharedPreferences.getString(USER, null);
        return json == null ? null : Single.just(mGson.fromJson(json, User.class));
    }

    @Override
    public Single<User> getAndSaveUserInfo(String login, String pwd) {
        return null;
    }

    @Override
    public void saveUser(User user) {
        if (user == null) {
            return;
        }
        mSharedPreferences
                .edit()
                .putString(USER, mGson.toJson(user))
                .apply();
    }
}
