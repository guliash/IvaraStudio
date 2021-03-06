// Copyright (c) 2018 Yandex LLC. All rights reserved.
// Author: Olga Kim <tayrinn@yandex-team.ru>

package ru.volha.hustle.ivarastudio.news;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.DaggerFragment;
import ru.volha.hustle.ivarastudio.BaseView;
import ru.volha.hustle.ivarastudio.MainActivity;
import ru.volha.hustle.ivarastudio.R;
import ru.volha.hustle.ivarastudio.data.News;
import ru.volha.hustle.ivarastudio.di.ActivityScoped;

@ActivityScoped
public class NewsFragment extends DaggerFragment implements BaseView<NewsPresenter> {

    @Inject
    NewsPresenter mPresenter;
    @BindView(R.id.news_pager)
    ViewPager mPager;
    @BindView(R.id.news_tabs)
    TabLayout mTabLayout;
    private NewsPagerAdapter mNewsPagerAdapter;
    private Unbinder mUnbinder;

    @Inject
    public NewsFragment() {
        // dagger requires
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUnbinder = ButterKnife.bind(this, view);
        ((MainActivity) getActivity()).getSupportActionBar().setElevation(0);
        mNewsPagerAdapter = new NewsPagerAdapter(getChildFragmentManager());
        mPager.setAdapter(mNewsPagerAdapter);
        mTabLayout.setupWithViewPager(mPager);
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

    public void updateNews(List<News> mainNews, List<News> recommendedNews) {
        mNewsPagerAdapter.updateNews(mainNews, recommendedNews);
    }

    @Override
    public void setPresenter(NewsPresenter presenter) {
        presenter.bind(this);
    }

    public static class NewsListFragment extends Fragment {
        private NewsAdapter mAdapter;

        public void update(List<News> news) {
            mAdapter.updateNews(news);
        }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return inflater.inflate(R.layout.news_list, container, false);
        }

        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            mAdapter = new NewsAdapter(getActivity());
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(mAdapter);
        }
    }

    class NewsPagerAdapter extends FragmentPagerAdapter {

        NewsListFragment mMainFragment;
        NewsListFragment mRecommendedFragment;

        NewsPagerAdapter(FragmentManager fm) {
            super(fm);
            mMainFragment = new NewsListFragment();
            mRecommendedFragment = new NewsListFragment();
        }

        void updateNews(List<News> mainNews, List<News> recommendedNews) {
            mMainFragment.update(mainNews);
            mRecommendedFragment.update(recommendedNews);
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public Fragment getItem(int position) {
            return position == 0 ? mMainFragment : mRecommendedFragment;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return position == 0 ? getString(R.string.news_all) : getString(R.string.news_recommends);
        }
    }
}
