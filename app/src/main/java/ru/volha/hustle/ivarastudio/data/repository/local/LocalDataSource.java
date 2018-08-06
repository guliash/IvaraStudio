// Copyright (c) 2018 Yandex LLC. All rights reserved.
// Author: Olga Kim <tayrinn@yandex-team.ru>

package ru.volha.hustle.ivarastudio.data.repository.local;

import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Single;
import ru.volha.hustle.ivarastudio.data.News;
import ru.volha.hustle.ivarastudio.data.NewsType;
import ru.volha.hustle.ivarastudio.data.User;
import ru.volha.hustle.ivarastudio.data.repository.DataSource;

public class LocalDataSource implements DataSource {

    private static final String USER = "user_json";

    private final SharedPreferences mSharedPreferences;
    private final Gson mGson;

    @Inject
    public LocalDataSource(SharedPreferences sharedPreferences) {
        mSharedPreferences = sharedPreferences;
        mGson = new Gson();
    }

//
//    @Override
//    public Single<User> getUserInfo() {
//        String json = mSharedPreferences.getString(USER, null);
//        return json == null ? null : Single.just(mGson.fromJson(json, User.class));
//    }

    @Override
    public Single<User> getUserInfo(String login, String pwd) {
        return null;
    }

    @Override
    public void saveUser(User user) {
        if (user == null) {
            return;
        }
        mSharedPreferences
                .edit()
                .putString(USER, mGson.toJson(user))
                .apply();
    }

    @Override
    public Flowable<List<News>> getNews(boolean forceUpdate) {
        List<News> news = new ArrayList<>();
        News general = new News(NewsType.GENERAL, "Новые наборы по дискофоксу!",
                "Тот, кто придумал дискофокс, был очень суеверным и считал 3 счастливым числом \uD83D\uDE04\n" +
                        "Вот и мы решили поделиться свеженькой подборкой групп для желающих научиться денсить на трешку",
                "https://pp.userapi.com/c844417/v844417978/ab91e/f45YHntg-cY.jpg",
                "https://vk.com/ivara?w=wall-7037976_11934");

        News personal = new News(NewsType.PERSONAL, "Ученики начинающей группы закончили курс прододжающие", "", "", "");
        News alert = new News(NewsType.ALERT, "Внимание! Занятия переносятся в группе <b>Дамиры и Раисы</b>", "", "", "");

        news.add(general);
        news.add(personal);
        news.add(alert);
        news.add(general);
        news.add(personal);
        news.add(alert);
        news.add(general);
        news.add(personal);
        news.add(alert);
        news.add(general);
        news.add(personal);
        news.add(alert);
        news.add(general);
        news.add(personal);
        news.add(alert);
        return Flowable.just(news);
    }

    @Override
    public void saveNews(List<News> news) {

    }
}
