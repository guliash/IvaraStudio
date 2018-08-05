// Copyright (c) 2018 Yandex LLC. All rights reserved.
// Author: Olga Kim <tayrinn@yandex-team.ru>

package ru.volha.hustle.ivarastudio.data;

import android.support.annotation.StringDef;

@StringDef(value = {NewsType.GENERAL, NewsType.ALERT, NewsType.PERSONAL})
public @interface NewsType {
    String GENERAL = "general";
    String ALERT = "alert";
    String PERSONAL = "personal";
}

