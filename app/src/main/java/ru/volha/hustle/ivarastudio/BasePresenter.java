// Copyright (c) 2018 Yandex LLC. All rights reserved.
// Author: Olga Kim <tayrinn@yandex-team.ru>

package ru.volha.hustle.ivarastudio;

public interface BasePresenter<T> {

    void bind(T view);
    void start();

    void end();

}