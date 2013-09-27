package com.example.locking;


import android.app.ActionBar;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TabHost;

public class FriendsHost extends TabActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getActionBar();
        actionBar.hide();
        
        setContentView(R.layout.activity_friends);
        
        TabHost tabHost = getTabHost();
        
        tabHost.addTab(tabHost.newTabSpec("tab1")
                .setIndicator("내 친구")
                .setContent(new Intent(this, Friends_1.class)));
        tabHost.addTab(tabHost.newTabSpec("tab2")
                .setIndicator("친구 찾기")
                .setContent(new Intent(this, Friends_2.class)));
        tabHost.addTab(tabHost.newTabSpec("tab3")
                .setIndicator("요청")
                .setContent(new Intent(this, Friends_3.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)));

    }
    
}
