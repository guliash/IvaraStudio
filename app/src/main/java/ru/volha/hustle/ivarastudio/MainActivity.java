package ru.volha.hustle.ivarastudio;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.Lazy;
import dagger.android.support.DaggerAppCompatActivity;
import ru.volha.hustle.ivarastudio.contacts.ContactsFragment;
import ru.volha.hustle.ivarastudio.news.NewsFragment;
import ru.volha.hustle.ivarastudio.places.PlacesFragment;
import ru.volha.hustle.ivarastudio.schedule.ScheduleFragment;
import ru.volha.hustle.ivarastudio.settings.SettingsFragment;
import ru.volha.hustle.ivarastudio.user_profile.UserProfileFragment;
import ru.volha.hustle.ivarastudio.util.CropCircleTransformation;
import ru.volha.hustle.ivarastudio.video.VideoFragment;

public class MainActivity extends DaggerAppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Inject
    Lazy<UserProfileFragment> mUserProfileFragmentProvider;
    @Inject
    Lazy<NewsFragment> mNewsFragmentProvider;
    @Inject
    Lazy<ScheduleFragment> mScheduleFragmentProvider;
    @Inject
    Lazy<ContactsFragment> mContactsFragmentProvider;
    @Inject
    Lazy<VideoFragment> mVideoFragmentProvider;
    @Inject
    Lazy<PlacesFragment> mPlacesFragmentProvider;
    @Inject
    Lazy<SettingsFragment> mSettingsFragmentProvider;

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.nav_view)
    NavigationView mNavigationView;

    View mProfileView;
    ImageView mUserAvatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.app_name, R.string.app_name) {

                public void onDrawerClosed(View view) {
                    supportInvalidateOptionsMenu();
                }

                public void onDrawerOpened(View drawerView) {
                    supportInvalidateOptionsMenu();
                }
            };
            mDrawerToggle.setDrawerIndicatorEnabled(true);
            mDrawerLayout.addDrawerListener(mDrawerToggle);
            mNavigationView.setNavigationItemSelectedListener(this);
            mDrawerToggle.syncState();
        }
        if (savedInstanceState == null) {
            openNewsFragment();
            mNavigationView.setCheckedItem(R.id.menu_main);
        }
        mProfileView = mNavigationView.getHeaderView(0);
        mUserAvatar = mProfileView.findViewById(R.id.user_avatar);
        mProfileView.setOnClickListener(v -> {
            openUserProfileFragment();
            mDrawerLayout.closeDrawers();
        });
        loginAndInit();
    }

    public void loginAndInit() {
        Picasso.get().load("https://pp.userapi.com/c624223/v624223722/173c9/GOZ19TJSIrI.jpg")
                .transform(new CropCircleTransformation())
                .into(mUserAvatar);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_main:
                openNewsFragment();
                break;
            case R.id.menu_schedule:
                openScheduleFragment();
                break;
            case R.id.menu_video:
                openVideoFragment();
                break;
            case R.id.menu_places:
                openPlacesFragment();
                break;
            case R.id.menu_phone:
                openContactsFragment();
                break;
            case R.id.menu_settings:
                openSettingsFragment();
                break;
        }
        mNavigationView.setCheckedItem(item.getItemId());
        mDrawerLayout.closeDrawers();
        return false;
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void openUserProfileFragment() {
        UserProfileFragment userProfileFragment = (UserProfileFragment) getSupportFragmentManager()
                .findFragmentByTag("fragment_user_profile");
        if (userProfileFragment == null) {
            userProfileFragment = mUserProfileFragmentProvider.get();
        }
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contentFrame, userProfileFragment, "fragment_user_profile")
                .commit();
    }

    private void openNewsFragment() {
        NewsFragment fragment = (NewsFragment) getSupportFragmentManager()
                .findFragmentByTag("fragment_news");
        if (fragment == null) {
            fragment = mNewsFragmentProvider.get();
        }
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contentFrame, fragment, "fragment_news")
                .commit();
    }

    private void openScheduleFragment() {
        ScheduleFragment fragment = (ScheduleFragment) getSupportFragmentManager()
                .findFragmentByTag("fragment_schedule");
        if (fragment == null) {
            fragment = mScheduleFragmentProvider.get();
        }
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contentFrame, fragment, "fragment_schedule")
                .commit();
    }

    private void openContactsFragment() {
        ContactsFragment fragment = (ContactsFragment) getSupportFragmentManager()
                .findFragmentByTag("fragment_contacts");
        if (fragment == null) {
            fragment = mContactsFragmentProvider.get();
        }
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contentFrame, fragment, "fragment_contacts")
                .commit();
    }

    private void openVideoFragment() {
        VideoFragment fragment = (VideoFragment) getSupportFragmentManager()
                .findFragmentByTag("fragment_video");
        if (fragment == null) {
            fragment = mVideoFragmentProvider.get();
        }
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contentFrame, fragment, "fragment_video")
                .commit();
    }

    private void openPlacesFragment() {
        PlacesFragment fragment = (PlacesFragment) getSupportFragmentManager()
                .findFragmentByTag("fragment_places");
        if (fragment == null) {
            fragment = mPlacesFragmentProvider.get();
        }
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contentFrame, fragment, "fragment_places")
                .commit();
    }

    private void openSettingsFragment() {
        SettingsFragment fragment = (SettingsFragment) getSupportFragmentManager()
                .findFragmentByTag("fragment_settings");
        if (fragment == null) {
            fragment = mSettingsFragmentProvider.get();
        }
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contentFrame, fragment, "fragment_settings")
                .commit();
    }

}
