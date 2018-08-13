package ru.volha.hustle.ivarastudio.di;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import ru.volha.hustle.ivarastudio.MainActivity;
import ru.volha.hustle.ivarastudio.schedule.ScheduleFragmentBindingModule;

// Copyright (c) 2018 Yandex LLC. All rights reserved.
// Author: Olga Kim <tayrinn@yandex-team.ru>
@Subcomponent(modules = {ScheduleFragmentBindingModule.class, MainActivityModule.class})
@ActivityScoped
public interface MainActivityComponent extends AndroidInjector<MainActivity> {

    @Subcomponent.Builder
    public abstract class Builder extends AndroidInjector.Builder<MainActivity> {
    }

}
