package com.example.locking;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.PopupMenu;

public class GalleryViewTest extends Activity implements AnimationListener{
	
	private MyGallery mGallery;
	
	Animation a;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gallery_main);
		
		mGallery = (MyGallery)findViewById(R.id.Gallery01);
		mGallery.setAdapter(new ImageAdapter(this));
		mGallery.setOnItemClickListener(new OnItemClickListener(){

			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
				 Animation animation = AnimationUtils.loadAnimation(GalleryViewTest.this, R.anim.anim);
				 animation.setAnimationListener(GalleryViewTest.this);
				 view.startAnimation(animation);
				 startActivity(new Intent(GalleryViewTest.this, CanvasTestActivity.class));
		    		
			}
		});
		
		
		Button go_friendadd = (Button)findViewById(R.id.btn_friendadd);
        go_friendadd.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				startActivity(new Intent(GalleryViewTest.this, FriendsHost.class));
    		
			}	
			
		}
		);
        
        Button go_popupmenu = (Button)findViewById(R.id.btn_menu);
        go_popupmenu.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View v) {

				PopupMenu popup = new PopupMenu(GalleryViewTest.this, findViewById(R.id.btn_menu) );
				
				popup.getMenuInflater().inflate(R.menu.appmain_popup, popup.getMenu());
				
				popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
					
					@Override
					public boolean onMenuItemClick(MenuItem item) {

						switch(item.getItemId()){
							case R.id.item_setting:
								startActivity(new Intent(GalleryViewTest.this, SettingActivity.class));
								break;
							case R.id.item_logout:
								finish();
								break;
						}
						
						return false;
					}
				});
				

				popup.show();	
			}
			
		});

        
	}
	
	@Override
	public void onAnimationEnd(Animation animation) {
		Log.i("GalleryViewTest" , "onAnimationEnd");
	}
	@Override
	public void onAnimationRepeat(Animation animation) {}
	@Override
	public void onAnimationStart(Animation animation) {}
}