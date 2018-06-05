// Copyright (c) 2018 Yandex LLC. All rights reserved.
// Author: Olga Kim <tayrinn@yandex-team.ru>

package ru.volha.hustle.ivarastudio.data.repository.remote;

import javax.inject.Inject;

import io.reactivex.Single;
import ru.volha.hustle.ivarastudio.data.User;
import ru.volha.hustle.ivarastudio.data.repository.DataSource;

public class RemoteDataSource implements DataSource {

    private final RemoteApi mApi;

    @Inject
    public RemoteDataSource(RemoteApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public Single<User> getUserInfo() {
        return null;
    }

    @Override
    public Single<User> getAndSaveUserInfo(String login, String pwd) {
        return mApi.getUser(login, pwd)
                .map(userResponse -> new User(userResponse.ID, login, pwd));
    }

    @Override
    public void saveUser(User user) {
        // save only in local
    }
}
