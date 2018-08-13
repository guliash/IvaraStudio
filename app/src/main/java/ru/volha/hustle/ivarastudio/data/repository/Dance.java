// Copyright (c) 2018 Yandex LLC. All rights reserved.
// Author: Olga Kim <tayrinn@yandex-team.ru>

package ru.volha.hustle.ivarastudio.data.repository;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import ru.volha.hustle.ivarastudio.data.Group;

public class Dance implements Parcelable {

    public String danceType;
    public List<Group> groups; //TODO parcelable

    public Dance() {}

    protected Dance(Parcel in) {
        danceType = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(danceType);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Dance> CREATOR = new Creator<Dance>() {
        @Override
        public Dance createFromParcel(Parcel in) {
            return new Dance(in);
        }

        @Override
        public Dance[] newArray(int size) {
            return new Dance[size];
        }
    };
}
