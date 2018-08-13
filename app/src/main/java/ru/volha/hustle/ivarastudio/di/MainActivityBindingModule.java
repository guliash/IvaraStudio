package ru.volha.hustle.ivarastudio.di;

import android.app.Activity;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;
import ru.volha.hustle.ivarastudio.MainActivity;

/**
 * We want Dagger.Android to create a Subcomponent which has a parent Component of whichever module ActivityBindingModule is on,
 * in our case that will be AppComponent. The beautiful part about this setup is that you never need to tell AppComponent that it is going to have all these subcomponents
 * nor do you need to tell these subcomponents that AppComponent exists.
 * We are also telling Dagger.Android that this generated SubComponent needs to include the specified modules and be aware of a scope annotation @ActivityScoped
 * When Dagger.Android annotation processor runs it will create 4 subcomponents for us.
 */
@Module(subcomponents = MainActivityComponent.class)
public abstract class MainActivityBindingModule {

    @Binds
    @IntoMap
    @ActivityKey(MainActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindMainActivityInjector(MainActivityComponent.Builder builder);


//    @ActivityScoped
//    @ContributesAndroidInjector(modules = {
//            UserProfileModule.class, NewsModule.class, ScheduleModule.class, ContactsModule.class,
//            VideoModule.class, PlacesModule.class, SettingsModule.class})
//    abstract MainActivity mainActivity();

}
