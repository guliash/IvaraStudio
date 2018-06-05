// Copyright (c) 2018 Yandex LLC. All rights reserved.
// Author: Olga Kim <tayrinn@yandex-team.ru>

package ru.volha.hustle.ivarastudio.data.repository;

import io.reactivex.Single;
import ru.volha.hustle.ivarastudio.data.User;

public interface DataSource {

    Single<User> getUserInfo();

    Single<User> getAndSaveUserInfo(String login, String pwd);

    void saveUser(User user);
}
