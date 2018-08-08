// Copyright (c) 2018 Yandex LLC. All rights reserved.
// Author: Olga Kim <tayrinn@yandex-team.ru>

package ru.volha.hustle.ivarastudio.schedule;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ru.volha.hustle.ivarastudio.BasePresenter;
import ru.volha.hustle.ivarastudio.data.repository.Repository;

public class SchedulePresenter implements BasePresenter<ScheduleFragment> {

    @NonNull
    private final Repository mRepository;
    ScheduleFragment mView;
    @NonNull
    private CompositeDisposable mCompositeDisposable;

    @Inject
    public SchedulePresenter(@NonNull Repository repository) {
        mRepository = repository;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void bind(ScheduleFragment view) {
        mView = view;
    }

    private void showSchedule(boolean forceUpdate, boolean showLoadingUi) {
        Disposable disposable = mRepository.getSchedule(forceUpdate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(schedule -> mView.updateSchedule(schedule));
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void start() {

    }

    @Override
    public void end() {
        mCompositeDisposable.clear();
    }
}
