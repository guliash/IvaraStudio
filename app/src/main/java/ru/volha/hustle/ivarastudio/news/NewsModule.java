// Copyright (c) 2018 Yandex LLC. All rights reserved.
// Author: Olga Kim <tayrinn@yandex-team.ru>

package ru.volha.hustle.ivarastudio.news;

import dagger.Binds;
import dagger.Module;
import ru.volha.hustle.ivarastudio.BasePresenter;
import ru.volha.hustle.ivarastudio.di.FragmentScoped;

@Module
public abstract class NewsModule {

    @FragmentScoped
    @Binds
    abstract BasePresenter<NewsFragment> newsPresenter(NewsPresenter presenter);
}
