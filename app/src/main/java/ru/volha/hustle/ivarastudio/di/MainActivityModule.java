package ru.volha.hustle.ivarastudio.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import ru.volha.hustle.ivarastudio.MainActivity;
import ru.volha.hustle.ivarastudio.contacts.ContactsModule;
import ru.volha.hustle.ivarastudio.news.NewsFragment;
import ru.volha.hustle.ivarastudio.news.NewsModule;
import ru.volha.hustle.ivarastudio.places.PlacesModule;
import ru.volha.hustle.ivarastudio.schedule.ScheduleFragment;
import ru.volha.hustle.ivarastudio.schedule.ScheduleModule;
import ru.volha.hustle.ivarastudio.settings.SettingsModule;
import ru.volha.hustle.ivarastudio.user_profile.UserProfileModule;
import ru.volha.hustle.ivarastudio.video.VideoModule;

/**
 * We want Dagger.Android to create a Subcomponent which has a parent Component of whichever module ActivityBindingModule is on,
 * in our case that will be AppComponent. The beautiful part about this setup is that you never need to tell AppComponent that it is going to have all these subcomponents
 * nor do you need to tell these subcomponents that AppComponent exists.
 * We are also telling Dagger.Android that this generated SubComponent needs to include the specified modules and be aware of a scope annotation @ActivityScoped
 * When Dagger.Android annotation processor runs it will create 4 subcomponents for us.
 */
@Module
public abstract class MainActivityModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = {
            UserProfileModule.class, NewsModule.class, ScheduleModule.class, ContactsModule.class,
            VideoModule.class, PlacesModule.class, SettingsModule.class})
    abstract MainActivity mainActivity();

    @FragmentScoped
    @ContributesAndroidInjector
    abstract NewsFragment newsFragment();

    @FragmentScoped
    @ContributesAndroidInjector
    abstract ScheduleFragment scheduleFragment();

}
