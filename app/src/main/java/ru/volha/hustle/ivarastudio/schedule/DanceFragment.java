// Copyright (c) 2018 Yandex LLC. All rights reserved.
// Author: Olga Kim <tayrinn@yandex-team.ru>

package ru.volha.hustle.ivarastudio.schedule;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import javax.inject.Inject;

import ru.volha.hustle.ivarastudio.data.repository.Dance;
import ru.volha.hustle.ivarastudio.data.repository.Repository;

public class DanceFragment extends Fragment {

    private static final String DANCE_ARG = "dance";

    @Inject
    Repository mRepository;
    @Inject
    Dance dance;

    @Inject
    public DanceFragment() {
    }

    public static DanceFragment newInstance(Dance dance) {
        Bundle args = new Bundle();
        args.putParcelable(DANCE_ARG, dance);

        DanceFragment fragment = new DanceFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((ScheduleFragment) getParentFragment()).getmScheduleComponent()
                .danceComponentBuilder()
                .bindDance(getArguments().getParcelable(DANCE_ARG))
                .build()
                .inject(this);
    }
}
