// Copyright (c) 2018 Yandex LLC. All rights reserved.
// Author: Olga Kim <tayrinn@yandex-team.ru>

package ru.volha.hustle.ivarastudio.schedule;

import dagger.BindsInstance;
import dagger.Subcomponent;
import ru.volha.hustle.ivarastudio.data.repository.Dance;
import ru.volha.hustle.ivarastudio.di.ChildFragmentScoped;

@Subcomponent
@ChildFragmentScoped
public interface DanceComponent {

    void inject(DanceFragment fragment);

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        Builder bindDance(Dance dance);

        DanceComponent build();
    }

}
