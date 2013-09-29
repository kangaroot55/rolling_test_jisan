package com.example.locking;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class Friends_1 extends Activity {
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_1);
        
        ArrayList <Profile> list = new ArrayList<Profile>();
        
        list.add(new Profile("what@naver.com","뭐","server.1"));
        list.add(new Profile("jisan@naver.com","송지산","server.3"));
        list.add(new Profile("seunghak@gmail.com","이승학","server.15"));
        list.add(new Profile("janghyuk@gmail.com","장혁","server.22"));
        list.add(new Profile("1@naver.com","jason","server.3"));
        list.add(new Profile("2@gmail.com","android","server.15"));
        list.add(new Profile("3@gmail.com","jobs","server.22"));
        
        MyFriendsAdapter adapter = new MyFriendsAdapter(this, R.layout.friends_1_item, list);
        
        ListView lv = (ListView)findViewById(R.id.lv_myfriends);
        lv.setAdapter(adapter);
	}
	
	class MyFriendsAdapter extends BaseAdapter {

		ArrayList<Profile> list;
		Context ctx;
		int itemLayout;

		MyFriendsAdapter(Context ctx, int itemLayout, ArrayList<Profile> list) {
			this.ctx = ctx;
			this.itemLayout = itemLayout;
			this.list = list;
		}

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Profile getItem(int position) {
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			final int pos = position;
			if (convertView == null) {
				LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = inflater.inflate(itemLayout, parent, false);
				TextView tv = (TextView) convertView.findViewById(R.id.tv_friend_1_item_name);
				tv.setText(list.get(position).get_name());
				TextView tv2 = (TextView) convertView.findViewById(R.id.tv_friend_1_item_email);
				tv2.setText(list.get(position).get_email());
				ImageView iv = (ImageView) convertView.findViewById(R.id.iv_friend_1_item_profilepic);
			}
			return convertView;
		}
	}

	
}
