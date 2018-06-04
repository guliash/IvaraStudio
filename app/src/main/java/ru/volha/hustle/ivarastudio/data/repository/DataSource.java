// Copyright (c) 2018 Yandex LLC. All rights reserved.
// Author: Olga Kim <tayrinn@yandex-team.ru>

package ru.volha.hustle.ivarastudio.data.repository;

import io.reactivex.Flowable;
import ru.volha.hustle.ivarastudio.data.User;

public interface DataSource {

    Flowable<User> getUserInfo();
}
