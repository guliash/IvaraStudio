// Copyright (c) 2018 Yandex LLC. All rights reserved.
// Author: Olga Kim <tayrinn@yandex-team.ru>

package ru.volha.hustle.ivarastudio.data;

import android.support.annotation.NonNull;

public class News {

    @NonNull
    @NewsType
    public String newsType;

    public String title;

    public String text;

    public String imageUrl;

    public String detailsUrl;

    public News(@NonNull String newsType, String title, String text, String imageUrl, String detailsUrl) {
        this.newsType = newsType;
        this.title = title;
        this.text = text;
        this.imageUrl = imageUrl;
        this.detailsUrl = detailsUrl;
    }
}
