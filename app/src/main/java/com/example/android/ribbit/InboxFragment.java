package com.example.android.ribbit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.ribbit.Constants.Constants;
import com.firebase.client.Firebase;
import com.firebase.ui.FirebaseListAdapter;

/**
 * Created by surya on 06-06-2016.
 */
public class InboxFragment extends Fragment {

    Firebase mRef;
    ListView mListView;

    public InboxFragment(){

    }

    public static InboxFragment newInstance() {
        InboxFragment fragment = new InboxFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_inbox, container, false);
//            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
//            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));

        mListView = (ListView)rootView.findViewById(R.id.list_view_inbox);

        mRef = new Firebase(Constants.FIREBASE_URL_USER_RELATION);

        ListAdapter adapter = new FirebaseListAdapter<User>(getActivity(),User.class,android.R.layout.simple_list_item_1,mRef) {
            @Override
            protected void populateView(View view, User user, int i) {
                ((TextView)view.findViewById(android.R.id.text1)).setText(user.getName());
            }
        };

        mListView.setAdapter(adapter);

        return rootView;

    }

    @Override
    public void onStart() {
        super.onStart();

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getActivity(),( (TextView)(mListView.getChildAt(position))).getText(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(),ChatActivity.class);
                startActivity(intent);
            }
        });

    }
}
