package ru.volha.hustle.ivarastudio.schedule;

import android.support.v4.app.Fragment;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.android.support.FragmentKey;
import dagger.multibindings.IntoMap;

// Copyright (c) 2018 Yandex LLC. All rights reserved.
// Author: Olga Kim <tayrinn@yandex-team.ru>
@Module(subcomponents = ScheduleComponent.class)
public abstract class ScheduleFragmentBindingModule {
    @Binds
    @IntoMap
    @FragmentKey(ScheduleFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindScheduleFragmentInjectorFactory(
            ScheduleComponent.Builder builder
    );
}
