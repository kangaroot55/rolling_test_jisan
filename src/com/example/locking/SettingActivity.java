package com.example.locking;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.example.locking.Friends_2.SeparatedListAdapter;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class SettingActivity extends Activity {

	public final static String EMAIL = "email";
	public final static String NAME = "name";
	public final static String PROFILE_PIC = "profilepic";

	public Map<String, ?> createItem(String email, String name, String profilepic) {
		Map<String, String> item = new HashMap<String, String>();
		item.put(EMAIL, email);
		item.put(NAME, name);
		item.put(PROFILE_PIC, profilepic);
		return item;
	}

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
			
		ActionBar actionBar = getActionBar();
		actionBar.setTitle("설정");

		List<Map<String, ?>> myapplist = new LinkedList<Map<String, ?>>();
		List<Map<String, ?>> fblist = new LinkedList<Map<String, ?>>();
		myapplist.add(createItem("app1@naver.com","앱친구","server.1"));
        myapplist.add(createItem("jisan@naver.com","송지산","server.3"));
        
        fblist.add(createItem("what@naver.com","뭐","server.1"));
        fblist.add(createItem("jisan@naver.com","송지산","server.3"));
        fblist.add(createItem("seunghak@gmail.com","이승학","server.15"));
        fblist.add(createItem("janghyuk@gmail.com","장혁","server.22"));
        fblist.add(createItem("jisan@naver.com","송지산","server.3"));
        fblist.add(createItem("seunghak@gmail.com","이승학","server.15"));
        fblist.add(createItem("janghyuk@gmail.com","장혁","server.22"));
        fblist.add(createItem("jisan@naver.com","송지산","server.3"));
        fblist.add(createItem("seunghak@gmail.com","이승학","server.15"));
        fblist.add(createItem("janghyuk@gmail.com","장혁","server.22"));

        // create our list and custom adapter
		SeparatedListAdapter adapter = new SeparatedListAdapter(this);
		adapter.addSection("프로필 설정", new SimpleAdapter(this, myapplist, R.layout.friends_2_item, new String[] {
				EMAIL, NAME, PROFILE_PIC }, new int[] { 0, R.id.tv_friend_2_item_name, 0}));
		adapter.addSection("앱 설정", new SimpleAdapter(this, fblist, R.layout.friends_2_item, new String[] {
				EMAIL, NAME, PROFILE_PIC }, new int[] { 0, R.id.tv_friend_2_item_name, 0}));

		ListView list = (ListView)findViewById(R.id.lv_setting);
		list.setAdapter(adapter);
		
	}
	
	public class SeparatedListAdapter extends BaseAdapter {

		public final Map<String, Adapter> sections = new LinkedHashMap<String, Adapter>();

		public final ArrayAdapter<String> headers;

		public final static int TYPE_SECTION_HEADER = 0;

		public SeparatedListAdapter(Context context) {
			headers = new ArrayAdapter<String>(context, R.layout.list_header);
		}

		public void addSection(String section, Adapter adapter) {
			this.headers.add(section);
			this.sections.put(section, adapter);
		}

		public Object getItem(int position) {
			for (Object section : this.sections.keySet()) {
				Adapter adapter = sections.get(section);
				int size = adapter.getCount() + 1;

				// check if position inside this section
				if (position == 0)
					return section;
				if (position < size)
					return adapter.getItem(position - 1);

				// otherwise jump into next section
				position -= size;
			}
			return null;
		}

		public int getCount() {
			// total together all sections, plus one for each section header
			int total = 0;
			for (Adapter adapter : this.sections.values())
				total += adapter.getCount() + 1;
			return total;
		}

		public int getViewTypeCount() {
			// assume that headers count as one, then total all sections
			int total = 1;
			for (Adapter adapter : this.sections.values())
				total += adapter.getViewTypeCount();
			return total;
		}

		public int getItemViewType(int position) {
			int type = 1;
			for (Object section : this.sections.keySet()) {
				Adapter adapter = sections.get(section);
				int size = adapter.getCount() + 1;

				// check if position inside this section
				if (position == 0)
					return TYPE_SECTION_HEADER;
				if (position < size)
					return type + adapter.getItemViewType(position - 1);

				// otherwise jump into next section
				position -= size;
				type += adapter.getViewTypeCount();
			}
			return -1;
		}

		public boolean areAllItemsSelectable() {
			return false;
		}

		public boolean isEnabled(int position) {
			return (getItemViewType(position) != TYPE_SECTION_HEADER);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			int sectionnum = 0;
			for (Object section : this.sections.keySet()) {
				Adapter adapter = sections.get(section);
				int size = adapter.getCount() + 1;

				// check if position inside this section
				if (position == 0)
					return headers.getView(sectionnum, convertView, parent);
				if (position < size){
					
					return adapter.getView(position - 1, convertView, parent);
				}
					
				// otherwise jump into next section
				position -= size;
				sectionnum++;
			}
			return null;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}
		
	}

}
