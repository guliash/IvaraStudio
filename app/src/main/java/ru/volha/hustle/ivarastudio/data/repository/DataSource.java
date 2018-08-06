// Copyright (c) 2018 Yandex LLC. All rights reserved.
// Author: Olga Kim <tayrinn@yandex-team.ru>

package ru.volha.hustle.ivarastudio.data.repository;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;
import ru.volha.hustle.ivarastudio.data.News;
import ru.volha.hustle.ivarastudio.data.User;

public interface DataSource {

    Single<User> getUserInfo(String login, String pwd);
    void saveUser(User user);

    Flowable<List<News>> getNews(boolean forceUpdate);

    void saveNews(List<News> news);
}
