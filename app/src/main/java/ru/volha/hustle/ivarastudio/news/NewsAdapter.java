// Copyright (c) 2018 Yandex LLC. All rights reserved.
// Author: Olga Kim <tayrinn@yandex-team.ru>

package ru.volha.hustle.ivarastudio.news;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.volha.hustle.ivarastudio.R;
import ru.volha.hustle.ivarastudio.data.News;
import ru.volha.hustle.ivarastudio.data.NewsType;
import ru.volha.hustle.ivarastudio.util.TopCropImageView;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.BaseNewsViewHolder> {

    private final static int TYPE_SHORT = 1;
    private final static int TYPE_BIG = 2;
    private List<News> mNewsList = new ArrayList<>();
    private Activity mActivity;

    public NewsAdapter(Activity activity) {
        mActivity = activity;
    }

    public void updateNews(List<News> newsList) {
        if (newsList != null) {
            mNewsList = newsList;
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public BaseNewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (viewType == TYPE_BIG) {
            return new BigNewsViewHolder(inflater.inflate(R.layout.news_item_big, parent, false));
        }
        return new BaseNewsViewHolder(inflater.inflate(R.layout.news_item_short, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BaseNewsViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_BIG) {
            ((BigNewsViewHolder) holder).bind(mNewsList.get(position));
        } else {
            holder.bind(mNewsList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mNewsList.size();
    }

    @Override
    public int getItemViewType(int position) {
        News news = mNewsList.get(position);
        switch (news.type) {
            case NewsType.GENERAL:
                return TYPE_BIG;
            default:
                return TYPE_SHORT;
        }
    }

    class BaseNewsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.news_title)
        TextView mTitle;
        News mNews;

        BaseNewsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(News news) {
            mNews = news;
            mTitle.setText(SpannableString.valueOf(Html.fromHtml(news.title)));
            @ColorRes int color = R.color.tabsColor;
            if (news.type.equals(NewsType.ALERT)) {
                color = R.color.colorAccentSecondary;
            }
            itemView.setBackgroundColor(mTitle.getContext().getResources().getColor(color));
        }

        @OnClick(R.id.news_card)
        void onClick() {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(mNews.detailsUrl));
            mActivity.startActivity(intent);
        }
    }

    class BigNewsViewHolder extends BaseNewsViewHolder {

        @BindView(R.id.news_text)
        TextView mText;

        @BindView(R.id.news_image)
        TopCropImageView mImage;

        BigNewsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(News news) {
            super.bind(news);
            mText.setText(SpannableString.valueOf(Html.fromHtml(news.text)));
            Picasso.get().load(news.pictureUrl).into(mImage);
        }
    }
}
