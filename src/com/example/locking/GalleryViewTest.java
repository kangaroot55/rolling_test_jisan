package com.example.locking;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

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