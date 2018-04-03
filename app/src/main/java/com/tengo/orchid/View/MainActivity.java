package com.tengo.orchid.View;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.tengo.orchid.R;

public class MainActivity extends AppCompatActivity {
    private LinearLayout[] mContainerArray = new LinearLayout[NUM_TABS];
    private Fragment[] mFragmentArray = new Fragment[NUM_TABS];
    private int mTabPointer = 0;

    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 0;
    private static final int NUM_TABS = 3;
    private static final int PHOTOS_TAB = 0;
    private static final int ALBUMS_TAB = 1;
    private static final int PROFILES_TAB = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted must exit
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);

        } else {
            setupApp();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[],
                                           int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    setupApp();
                } else {
                    // TODO find graceful way to terminate app
                }
            }
        }
    }

    private void setupApp() {
        mContainerArray[PHOTOS_TAB] = (LinearLayout) findViewById(R.id.photos_container);
        mContainerArray[ALBUMS_TAB] = (LinearLayout) findViewById(R.id.albums_container);
        mContainerArray[PROFILES_TAB] = (LinearLayout) findViewById(R.id.profiles_container);

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_photos:
                                onTabTouched(PHOTOS_TAB);
                                break;
                            case R.id.action_albums:
                                onTabTouched(ALBUMS_TAB);
                                break;
                            case R.id.action_profiles:
                                onTabTouched(PROFILES_TAB);
                                break;
                        }
                        return true;
                    }
                });

        createFragment(PHOTOS_TAB);
        AnimationUtils.fadeInToVisible(mContainerArray[mTabPointer]);
    }

    private void onTabTouched(int index) {
        if (mFragmentArray[index] == mFragmentArray[mTabPointer]) {
            // Same tab touched
            return;
        }
        transitionTab(index);
    }

    private void transitionTab(int index) {
        AnimationUtils.fadeOutToGone(mContainerArray[mTabPointer]);
        if (mFragmentArray[index] == null) {
            createFragment(index);
        }
        AnimationUtils.fadeInToVisible(mContainerArray[index]);
        mTabPointer = index;
    }

    private void createFragment(int index) {
        Fragment newFragment;
        // Create new fragment
        switch (index) {
            case PHOTOS_TAB:
                newFragment = new PhotosTabFragment();
                break;
            case ALBUMS_TAB:
                newFragment = new AlbumsTabFragment();
                break;
            case PROFILES_TAB:
                newFragment = new ProfilesTabFragment();
                break;
            default:
                return;
        }
        mFragmentArray[index] = newFragment;
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(mContainerArray[index].getId(), newFragment);
        ft.commit();
    }
}
