// Copyright (c) 2018 Yandex LLC. All rights reserved.
// Author: Olga Kim <tayrinn@yandex-team.ru>

package ru.volha.hustle.ivarastudio;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import ru.volha.hustle.ivarastudio.di.DaggerAppComponent;

public class App extends DaggerApplication{
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }
}
