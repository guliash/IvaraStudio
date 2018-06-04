// Copyright (c) 2018 Yandex LLC. All rights reserved.
// Author: Olga Kim <tayrinn@yandex-team.ru>

package ru.volha.hustle.ivarastudio.data.repository.remote;

import io.reactivex.Flowable;
import ru.volha.hustle.ivarastudio.data.User;
import ru.volha.hustle.ivarastudio.data.repository.DataSource;

public class RemoteDataSource implements DataSource {

    @Override
    public Flowable<User> getUserInfo() {
        return null;
    }
}
