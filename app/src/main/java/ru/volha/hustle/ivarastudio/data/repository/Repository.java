// Copyright (c) 2018 Yandex LLC. All rights reserved.
// Author: Olga Kim <tayrinn@yandex-team.ru>

package ru.volha.hustle.ivarastudio.data.repository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;
import io.reactivex.Single;
import ru.volha.hustle.ivarastudio.data.News;
import ru.volha.hustle.ivarastudio.data.User;

@Singleton
public class Repository implements DataSource {

    private final DataSource mRemoteDataSource;
    private final DataSource mLocalDataSource;

    @Inject
    Repository(@Remote DataSource remoteDataSource,
               @Local DataSource localDataSource) {
        mRemoteDataSource = remoteDataSource;
        mLocalDataSource = localDataSource;
    }


    @Override
    public Single<User> getUserInfo() {
        return mLocalDataSource.getUserInfo();
    }

    @Override
    public Single<User> getAndSaveUserInfo(String login, String pwd) {
        return mRemoteDataSource
                .getAndSaveUserInfo(login, pwd)
                .doOnSuccess(mLocalDataSource::saveUser);
    }

    @Override
    public void saveUser(User user) {
        mLocalDataSource.saveUser(user);
    }

    @Override
    public Flowable<List<News>> getNews() {
        return mLocalDataSource.getNews();
    }
}
