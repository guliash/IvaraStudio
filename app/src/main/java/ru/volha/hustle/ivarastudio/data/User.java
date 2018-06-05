// Copyright (c) 2018 Yandex LLC. All rights reserved.
// Author: Olga Kim <tayrinn@yandex-team.ru>
package ru.volha.hustle.ivarastudio.data;

public class User {

    String id;
    String login;
    String password;

    // empty constructor for gson
    public User() {
    }

    public User(String id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }
}
