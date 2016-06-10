package com.example.android.ribbit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android.ribbit.Constants.Constants;
import com.example.android.ribbit.Constants.User;
import com.firebase.client.Firebase;
import com.firebase.ui.FirebaseListAdapter;

public class EditFriendsActivity extends AppCompatActivity {

    Firebase mRef;
    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_friends);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRef = new Firebase(Constants.FIREBASE_URL_USER);
        mListView = (ListView)findViewById(R.id.list_view);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

    }

    @Override
    protected void onStart() {
        super.onStart();

        ListAdapter adapter = new FirebaseListAdapter<User>(this,User.class,android.R.layout.simple_list_item_checked,mRef) {
            @Override
            protected void populateView(View view, User user, int i) {
                ((TextView)view.findViewById(android.R.id.text1)).setText(user.getName());
            }
        };
        /*ListAdapter adapter = new FirebaseListAdapter<String>(this,String.class,android.R.layout.simple_list_item_checked,mRef) {
            @Override
            protected void populateView(View view, String s, int i) {
                ((TextView)view.findViewById(android.R.id.text1)).setText(s);
                Toast.makeText(EditFriendsActivity.this, "xxxxxxxxxx", Toast.LENGTH_SHORT).show();
            }
        };*/
        mListView.setAdapter(adapter);

    }

}
