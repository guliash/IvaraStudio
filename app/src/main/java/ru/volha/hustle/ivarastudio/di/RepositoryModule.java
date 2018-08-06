package ru.volha.hustle.ivarastudio.di;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.volha.hustle.ivarastudio.data.repository.DataSource;
import ru.volha.hustle.ivarastudio.data.repository.Local;
import ru.volha.hustle.ivarastudio.data.repository.Remote;
import ru.volha.hustle.ivarastudio.data.repository.local.LocalDataSource;
import ru.volha.hustle.ivarastudio.data.repository.remote.RemoteApi;
import ru.volha.hustle.ivarastudio.data.repository.remote.RemoteDataSource;

/**
 * This is used by Dagger to inject the required arguments into the {@link Repository}.
 */
@Module
abstract public class RepositoryModule {

    private static final String PREFERENCES_NAME = "ivara_sp";

    @Singleton
    @Provides
    static SharedPreferences provideSharedPreferences(Application context) {
        return context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    @Singleton
    @Provides
    static RemoteApi provideRemoteApi() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .authenticator((route, response) -> {
                    Request request = response.request();
                    if (request.header("Authorization") != null)
                        // Логин и пароль неверны
                        return null;
                    return request.newBuilder()
                            .header("Authorization", Credentials.basic("ws", "ws"))
                            .build();
                })
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RemoteApi.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        return retrofit.create(RemoteApi.class);
    }

    @Singleton
    @Binds
    @Local
    abstract DataSource provideLocalDataSource(LocalDataSource dataSource);

    @Singleton
    @Binds
    @Remote
    abstract DataSource provideRemoteDataSource(RemoteDataSource dataSource);

//    @Singleton
//    @Provides
//    static ToDoDatabase provideDb(Application context) {
//        return Room.databaseBuilder(context.getApplicationContext(), ToDoDatabase.class, "Tasks.db")
//                .build();
//    }
//
//    @Singleton
//    @Provides
//    static TasksDao provideTasksDao(ToDoDatabase db) {
//        return db.taskDao();
//    }
//
//    @Singleton
//    @Provides
//    static AppExecutors provideAppExecutors() {
//        return new AppExecutors(new DiskIOThreadExecutor(),
//                Executors.newFixedThreadPool(THREAD_COUNT),
//                new AppExecutors.MainThreadExecutor());
//    }
}
