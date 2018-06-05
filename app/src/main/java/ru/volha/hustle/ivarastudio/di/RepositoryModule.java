package ru.volha.hustle.ivarastudio.di;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
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
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RemoteApi.BASE_URL)
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
