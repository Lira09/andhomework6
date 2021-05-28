package com.geek.homework6;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.MenuItem;
import android.widget.Toolbar;

import com.geek.homework6.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        toggle = new ActionBarDrawerToggle(this, binding.drawerLayout, R.string.drawer_open, R.string.drawer_close);
        setSupportActionBar(binding.toolbar);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        addNavView();
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return toggle != null && toggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        toggle.syncState();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        toggle.onConfigurationChanged(newConfig);
    }

    private void addNavView() {
        binding.navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            Fragment fragment = null;

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.inboxes:
                        fragment = new InboxesFragment();
                        break;
                    case R.id.inbox:
                        fragment = new InboxFragment();
                        break;
                    case R.id.done:
                        fragment = new DoneFragment();
                        break;
                    case R.id.drafts:
                        fragment = new DraftsFragment();
                        break;
                    case R.id.help:
                        fragment = new HelpFragment();
                        break;

                    case R.id.reminders:
                        fragment = new RemindFragment();
                        break;
                    case R.id.sent:
                        fragment = new SentFragment();
                        break;
                    case R.id.snooz:
                        fragment = new SnoozFragment();
                        break;
                    case R.id.settings:
                        fragment = new SettingsFragment();
                        break;
                    default:
                        fragment = new InboxesFragment();
                }
                MainActivity.this.setFragment(fragment);
                item.setChecked(true);
                MainActivity.this.setTitle(item.getTitle());
                binding.drawerLayout.closeDrawers();
                return false;
            }
        });
    }

    private void setFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.container_, fragment).commit();

    }

}
