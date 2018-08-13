// Copyright (c) 2018 Yandex LLC. All rights reserved.
// Author: Olga Kim <tayrinn@yandex-team.ru>

package ru.volha.hustle.ivarastudio.schedule;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import ru.volha.hustle.ivarastudio.di.FragmentScoped;

@Subcomponent(modules = ScheduleModule.class)
@FragmentScoped
public interface ScheduleComponent extends AndroidInjector<ScheduleFragment> {

    @Subcomponent.Builder
    public abstract class Builder extends AndroidInjector.Builder<ScheduleFragment> {
    }

}
