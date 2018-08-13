// Copyright (c) 2018 Yandex LLC. All rights reserved.
// Author: Olga Kim <tayrinn@yandex-team.ru>

package ru.volha.hustle.ivarastudio.schedule;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import java.util.List;

import javax.inject.Inject;

import ru.volha.hustle.ivarastudio.data.repository.Dance;
import ru.volha.hustle.ivarastudio.data.repository.Repository;

public class DanceFragment extends Fragment {

    @Inject
    Repository mRepository;
    @Inject
    List<Dance> mDanceList;

    @Inject
    public DanceFragment() {
    }

    public static DanceFragment newInstance(Dance dance) {

        Bundle args = new Bundle();

        DanceFragment fragment = new DanceFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
