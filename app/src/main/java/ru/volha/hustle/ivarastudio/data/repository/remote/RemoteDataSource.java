// Copyright (c) 2018 Yandex LLC. All rights reserved.
// Author: Olga Kim <tayrinn@yandex-team.ru>

package ru.volha.hustle.ivarastudio.data.repository.remote;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Single;
import ru.volha.hustle.ivarastudio.data.News;
import ru.volha.hustle.ivarastudio.data.User;
import ru.volha.hustle.ivarastudio.data.repository.Dance;
import ru.volha.hustle.ivarastudio.data.repository.DataSource;

public class RemoteDataSource implements DataSource {

    private final RemoteApi mApi;

    @Inject
    public RemoteDataSource(RemoteApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public Single<User> getUserInfo(String login, String pwd) {
        return mApi.getUser(login, pwd)
                .map(userResponse -> new User(userResponse.ID, login, pwd));
    }

    @Override
    public void saveUser(User user) {
        // save only in local
    }

    @Override
    public Flowable<List<News>> getNews(boolean forceUpdate) {
        return mApi.getNews();
    }

    @Override
    public void saveNews(List<News> news) {
        //not implemented
    }

    @Override
    public Flowable<List<Dance>> getSchedule(boolean forceUpdate) {
        return mApi.getGroups()
                .map(groups -> {
                    Dance dance = new Dance();
                    dance.danceType = "ALL";
                    dance.groups = groups;
                    return dance;
                })
                .toList()
                .toFlowable();
    }

    @Override
    public void saveSchedule(List<Dance> groups) {
        // not implemented
    }
}
