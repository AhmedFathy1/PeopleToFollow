package com.example.android.af.follower;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * Hosts the AddEditPersonFragment
 */

public class AddEditNewPerson extends AppCompatActivity {
    /**
     * The position of the record to edit , -1 indicates adding a new person
     */
    private int position = -1;
    public final static String POSITION_KEY = "com.example.android.af.follower.POSITION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_person);
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.new_person_fragment_id);
        if (fragment == null) {
            fragment = new AddEditPersonFragment();
            fragmentManager.beginTransaction().add(R.id.new_person_fragment_id, fragment).commit();
        }
        position = getIntent().getIntExtra(POSITION_KEY, -1);
        ((AddEditPersonFragment) fragment).position = position;
    }
}
