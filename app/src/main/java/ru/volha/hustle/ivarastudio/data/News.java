// Copyright (c) 2018 Yandex LLC. All rights reserved.
// Author: Olga Kim <tayrinn@yandex-team.ru>

package ru.volha.hustle.ivarastudio.data;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class News {

    @NonNull
    @NewsType
    public String type;

    public String title;

    public String text;

    @SerializedName("picture_url")
    public String pictureUrl;

    @SerializedName("details_url")
    public String detailsUrl;

    public News() {
    }

    public News(@NonNull String type, String title, String text, String pictureUrl, String detailsUrl) {
        this.type = type;
        this.title = title;
        this.text = text;
        this.pictureUrl = pictureUrl;
        this.detailsUrl = detailsUrl;
    }
}
