package ru.volha.hustle.ivarastudio;

import android.os.Bundle;

import javax.inject.Inject;

import dagger.Lazy;
import dagger.android.support.DaggerAppCompatActivity;
import ru.volha.hustle.ivarastudio.user_profile.UserProfileFragment;

public class MainActivity extends DaggerAppCompatActivity {

    @Inject
    Lazy<UserProfileFragment> mUserProfileFragmentProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            openUserProfileFragment();
        }
    }

    private void openUserProfileFragment() {
        UserProfileFragment userProfileFragment = (UserProfileFragment) getSupportFragmentManager()
                .findFragmentByTag("fragment_user_profile");
        if (userProfileFragment == null) {
            userProfileFragment = mUserProfileFragmentProvider.get();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contentFrame, userProfileFragment, "fragment_user_profile")
                    .commit();
        }
    }
}
