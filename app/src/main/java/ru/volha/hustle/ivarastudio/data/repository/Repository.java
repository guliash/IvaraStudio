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


//    @Override
//    public Single<User> getUserInfo() {
//        return mLocalDataSource.getUserInfo();
//    }

    @Override
    public Single<User> getUserInfo(String login, String pwd) {
        return mRemoteDataSource
                .getUserInfo(login, pwd)
                .doOnSuccess(mLocalDataSource::saveUser);
    }

    @Override
    public void saveUser(User user) {
        mLocalDataSource.saveUser(user);
    }

    @Override
    public Flowable<List<News>> getNews(boolean forceUpdate) {
        Flowable<List<News>> remote = getRemoteNews();
        if (forceUpdate) {
            return remote;
        }
        Flowable<List<News>> local = mLocalDataSource.getNews(forceUpdate);
        return Flowable.concat(local, remote)
                .filter(news -> !news.isEmpty())
                .firstOrError()
                .toFlowable();
    }

    @Override
    public void saveNews(List<News> news) {

    }

    private Flowable<List<News>> getRemoteNews() {
        return mRemoteDataSource.getNews(true)
                .doOnNext(mLocalDataSource::saveNews);
    }
}
