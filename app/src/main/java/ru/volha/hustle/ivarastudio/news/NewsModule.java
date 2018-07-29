// Copyright (c) 2018 Yandex LLC. All rights reserved.
// Author: Olga Kim <tayrinn@yandex-team.ru>

package ru.volha.hustle.ivarastudio.news;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import ru.volha.hustle.ivarastudio.di.FragmentScoped;

@Module
public abstract class NewsModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract NewsFragment newsFragment();
}
