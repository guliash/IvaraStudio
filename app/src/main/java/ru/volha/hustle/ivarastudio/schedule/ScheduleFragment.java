// Copyright (c) 2018 Yandex LLC. All rights reserved.
// Author: Olga Kim <tayrinn@yandex-team.ru>

package ru.volha.hustle.ivarastudio.schedule;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.DaggerFragment;
import ru.volha.hustle.ivarastudio.BaseView;
import ru.volha.hustle.ivarastudio.R;
import ru.volha.hustle.ivarastudio.data.repository.Dance;
import ru.volha.hustle.ivarastudio.di.ActivityScoped;

@ActivityScoped
public class ScheduleFragment extends DaggerFragment implements BaseView<SchedulePresenter> {

    @Inject
    SchedulePresenter mPresenter;
    @BindView(R.id.dancePager)
    ViewPager mPager;
    @BindView(R.id.danceTabs)
    TabLayout mTabLayout;

    private Unbinder mUnbinder;
    private DanceTypesAdapter mAdapter;

    @Inject
    public ScheduleFragment() {
        // dagger requires
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_schedule, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUnbinder = ButterKnife.bind(this, view);
        mAdapter = new DanceTypesAdapter(getChildFragmentManager());
        mPager.setAdapter(mAdapter);
    }

    void updateSchedule(List<Dance> dances) {

    }

    @Override
    public void setPresenter(SchedulePresenter presenter) {
        presenter.bind(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.bind(this);
        mPresenter.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.end();
        mUnbinder.unbind();
    }


    public void setLoadingIndicator(boolean show) {

    }

    private static class DanceTypesAdapter extends FragmentPagerAdapter {

        private List<Dance> mDances = new ArrayList<>();

        public DanceTypesAdapter(FragmentManager fm) {
            super(fm);
        }

        void updateSchedule(List<Dance> dances) {
            mDances = dances;
            notifyDataSetChanged();
        }

        @Override
        public Fragment getItem(int position) {
            return null;
        }

        @Override
        public int getCount() {
            return 1;
        }
    }
}
