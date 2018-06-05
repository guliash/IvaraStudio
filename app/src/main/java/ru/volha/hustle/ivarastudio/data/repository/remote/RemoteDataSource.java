// Copyright (c) 2018 Yandex LLC. All rights reserved.
// Author: Olga Kim <tayrinn@yandex-team.ru>

package ru.volha.hustle.ivarastudio.data.repository.remote;

import javax.inject.Inject;

import io.reactivex.Flowable;
import ru.volha.hustle.ivarastudio.data.User;
import ru.volha.hustle.ivarastudio.data.repository.DataSource;

public class RemoteDataSource implements DataSource {

    @Inject
    public RemoteDataSource() {
    }

    @Override
    public Flowable<User> getUserInfo() {
        return null;
    }

    @Override
    public Flowable<User> getAndSaveUserInfo(String login, String pwd) {
        return null;
    }

    @Override
    public void saveUser(User user) {

    }
}
