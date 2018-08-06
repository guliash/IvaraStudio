// Copyright (c) 2018 Yandex LLC. All rights reserved.
// Author: Olga Kim <tayrinn@yandex-team.ru>

package ru.volha.hustle.ivarastudio.news;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ru.volha.hustle.ivarastudio.BasePresenter;
import ru.volha.hustle.ivarastudio.data.repository.Repository;

public class NewsPresenter implements BasePresenter<NewsFragment> {

    @NonNull
    private final Repository mRepository;
    @NonNull
    private CompositeDisposable mCompositeDisposable;
    private NewsFragment mView;

    @Inject
    public NewsPresenter(Repository repository) {
        mRepository = repository;
        mCompositeDisposable = new CompositeDisposable();
    }

    public void loadNews(boolean forceUpdate, boolean showLoadingUi) {
        if (showLoadingUi) {
            mView.setLoadingIndicator(true);
        }

        Disposable disposable = mRepository.getNews(forceUpdate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(news -> mView.updateNews(news, news));
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void bind(NewsFragment view) {
        mView = view;
    }

    @Override
    public void start() {
        loadNews(true, true);
    }

    @Override
    public void end() {
        mCompositeDisposable.clear();
    }
}
