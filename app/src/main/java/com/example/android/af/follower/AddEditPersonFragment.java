package com.example.android.af.follower;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.TextView;


/**
 * Add or edit a person
 */

public class AddEditPersonFragment extends Fragment {

    private EditText mUserName;
    private EditText mGoogleId;
    private EditText mTwitterId;
    private EditText mInstagramId;
    private ConstraintLayout mGroup;
    DatabaseHandler databaseHandler;
    Animation shake;
    View view;
    /**
     * The position of the record to edit , -1 indicates adding a new person
     */
    public int position = -1;
    private PersonData pd;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.add_edit_fragment, container, false);
        setHasOptionsMenu(true);
        mUserName = view.findViewById(R.id.user_name);
        mGoogleId = view.findViewById(R.id.google_plus_id);
        mTwitterId = view.findViewById(R.id.twitter_id);
        mInstagramId = view.findViewById(R.id.instagram_id);
        mGroup = view.findViewById(R.id.Group);
        databaseHandler = new DatabaseHandler(getContext());
        if (position != -1) {
            pd = PeopleList.getInstance().getList().get(position);
            mUserName.setText(pd.getUserName());
            mGoogleId.setText(pd.getGoogleId());
            mTwitterId.setText(pd.getTwitterId());
            mInstagramId.setText(pd.getInstagram());
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.edit_person);
        } else pd = new PersonData();
        shake = AnimationUtils.loadAnimation(getContext(), R.anim.shake_animation);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.new_person_menu, menu);
    }

    /**
     *
     * @param str a string to be validated
     * @return true if the string isn't null, empty, composed only of white spaces or contains non English characters , otherwise returns false
     */
    private boolean validateData(String str) {
        return str != null && !str.isEmpty() && (str.trim().length() > 0) && (str.replaceAll("\\s+", "").matches("\\w+"));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.new_person_done) {
            String userName = mUserName.getText().toString();
            String googleId = mGoogleId.getText().toString();
            String twitterId = mTwitterId.getText().toString();
            String instagramId = mInstagramId.getText().toString();
            boolean googleValid = validateData(googleId), twitterValid = validateData(twitterId), instagramValid = validateData(instagramId);
            boolean userNameValid = validateData(userName);
            if (userNameValid && (googleValid || twitterValid || instagramValid)) {
                // after user click done image , add new person row
                pd = position == -1 ? new PersonData(userName, googleId, twitterId, instagramId) : new PersonData(pd.getID(), userName, googleId, twitterId, instagramId);
                if (position == -1) databaseHandler.addNewPerson(pd);
                else databaseHandler.updatePerson(pd);
                getActivity().finish();

            } else {
                mGroup.startAnimation(shake);
                String Message = "";

                if (!userNameValid && !(googleValid || twitterValid || instagramValid)) Message = "Enter a valid User name and social handle ";
                else if (!userNameValid) Message = "Invalid User name! ";
                else if (!(googleValid || twitterValid || instagramValid)) Message = "Enter at least 1 Valid Social handle";


                Snackbar sn = Snackbar.make(view, Message, Snackbar.LENGTH_SHORT);
                View v = sn.getView();
                TextView tv = v.findViewById(android.support.design.R.id.snackbar_text);
                tv.setTextColor(getResources().getColor(R.color.colorAccent));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                } else {
                    tv.setGravity(Gravity.CENTER_HORIZONTAL);
                }
                sn.show();


            }
        }
        return true;
    }

}
