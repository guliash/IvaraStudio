// Copyright (c) 2018 Yandex LLC. All rights reserved.
// Author: Olga Kim <tayrinn@yandex-team.ru>

package ru.volha.hustle.ivarastudio.schedule;

import java.util.List;

import dagger.Module;
import dagger.Provides;
import ru.volha.hustle.ivarastudio.data.repository.Dance;
import ru.volha.hustle.ivarastudio.di.ChildFragmentScoped;

@Module
public class DanceModule {

    @Provides
    @ChildFragmentScoped
    List<Dance> dancers(List<Dance> dancers) {
        return dancers;
    }
}
