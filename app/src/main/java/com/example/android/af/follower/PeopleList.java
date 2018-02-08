package com.example.android.af.follower;

import java.util.ArrayList;
import java.util.List;

/**
 * Singleton holds an Instance of people data on memory
 */

public class PeopleList {
    private static final PeopleList ourInstance = new PeopleList();
    private List<PersonData> mList;

    public List<PersonData> getList() {
        return mList;
    }

    public static PeopleList getInstance() {
        return ourInstance;
    }

    private PeopleList() {
        mList = new ArrayList<>();
    }
}
