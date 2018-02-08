package com.example.android.af.follower;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

/**
 * Shows RecyclerView of People
 */

public class MainFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private PeopleAdapter mAdapter;

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int idx = item.getGroupId();
        PersonData pd = PeopleList.getInstance().getList().get(idx);
        DatabaseHandler db = new DatabaseHandler(getContext());

        if (item.getOrder() == 0) {
            Intent intent = new Intent(getContext(), AddEditNewPerson.class);
            intent.putExtra(AddEditNewPerson.POSITION_KEY, idx);
            getContext().startActivity(intent);
        } else {
            db.deletePerson(pd);
            PeopleList.getInstance().getList().remove(idx);
        }
        mAdapter.notifyDataSetChanged();
        return true;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.main_fragment, container, false);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        mAdapter = new PeopleAdapter();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);

        //add separated line between list items
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));

        // set the adapter
        mRecyclerView.setAdapter(mAdapter);

        FloatingActionButton mFabButton = (FloatingActionButton) v.findViewById(R.id.fab);
        mFabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //open New Person Activity
                Intent intent = new Intent(getContext(), AddEditNewPerson.class);
                startActivity(intent);
            }
        });
        return v;
    }


    // Make a list of people
    public void preparePeopleData() {
        DatabaseHandler databaseHandler = new DatabaseHandler(this.getContext());
        // List to get all people from database
        databaseHandler.getAllPeople();
        // set the new changes of data
        mAdapter.notifyDataSetChanged();

    }

    @Override
    public void onStart() {
        super.onStart();
        /**
         * invoke preparePeopleData() to always keep the adapter up-to-date
         */
        preparePeopleData();
    }


}
