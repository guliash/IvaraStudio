// Copyright (c) 2018 Yandex LLC. All rights reserved.
// Author: Olga Kim <tayrinn@yandex-team.ru>

package ru.volha.hustle.ivarastudio.data.repository.remote;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import ru.volha.hustle.ivarastudio.data.Group;
import ru.volha.hustle.ivarastudio.data.News;
import ru.volha.hustle.ivarastudio.data.repository.remote.response.UserResponse;

public interface RemoteApi {

    String BASE_URL = "http://213.79.122.37/IvaraTest/hs/mobile/";

    @POST("authorization")
    Single<UserResponse> getUser(@Field("Login") String login, @Field("Password") String pwd);

    @POST("addUser")
    Single<UserResponse> createNewUser(@Field("Login") String login, @Field("Password") String pwd);

    @GET("news")
    Flowable<List<News>> getNews();

    @GET("schedule")
    Flowable<List<Group>> getGroups();

}
