// Copyright (c) 2018 Yandex LLC. All rights reserved.
// Author: Olga Kim <tayrinn@yandex-team.ru>

package ru.volha.hustle.ivarastudio.schedule;

import dagger.Binds;
import dagger.Module;
import ru.volha.hustle.ivarastudio.BasePresenter;
import ru.volha.hustle.ivarastudio.di.FragmentScoped;

@Module
public abstract class ScheduleModule {

    @FragmentScoped
    @Binds
    abstract BasePresenter<ScheduleFragment> schedulePresenter(SchedulePresenter presenter);
}
