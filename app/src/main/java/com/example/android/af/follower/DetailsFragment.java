package com.example.android.af.follower;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * Shows a person details
 */

public class DetailsFragment extends Fragment {
    private TextView mUserName;
    private TextView mGoogleId;
    private TextView mTwitterId;
    private TextView mInstagramId;
    public int position;
    /**
     * Currently selected person
     */
    private final String KEY_PERSON_POSITION = "com.example.android.af.follower.PERSON_POSITION";
    /**
     * Currently selected social handle
     */
    private final String KEY_SOCIAL_POSITION = "com.example.android.af.follower.SOCIAL_POSITION";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.person_details_fragment, container, false);
        mUserName = view.findViewById(R.id.details_username);
        mGoogleId = view.findViewById(R.id.details_google_id);
        mTwitterId = view.findViewById(R.id.details_twitter_id);
        mInstagramId = view.findViewById(R.id.details_instagram_id);
        mGoogleId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), WebViewActivity.class);
                intent.putExtra(KEY_PERSON_POSITION, position);
                intent.putExtra(KEY_SOCIAL_POSITION, 0);
                startActivity(intent);
            }
        });
        mTwitterId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), WebViewActivity.class);
                intent.putExtra(KEY_PERSON_POSITION, position);
                intent.putExtra(KEY_SOCIAL_POSITION, 1);
                startActivity(intent);
            }
        });
        mInstagramId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), WebViewActivity.class);
                intent.putExtra(KEY_PERSON_POSITION, position);
                intent.putExtra(KEY_SOCIAL_POSITION, 2);
                startActivity(intent);
            }
        });

        PersonData _person_data = PeopleList.getInstance().getList().get(position);

        mUserName.setText(_person_data.getUserName());
        mGoogleId.setText(_person_data.getGoogleId());
        mTwitterId.setText(_person_data.getTwitterId());
        mInstagramId.setText(_person_data.getInstagram());
        return view;
    }

}
