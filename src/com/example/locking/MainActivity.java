package com.example.locking;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;


public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED | WindowManager.LayoutParams.FLAG_SHOW_WALLPAPER);
		
		
		Button go_gall = (Button)findViewById(R.id.button1);
		go_gall.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this, GalleryViewTest.class));
    		
			}	
			
		}
		);
		
			
		Button btn_start = (Button)findViewById(R.id.btn_start);
		btn_start.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				startService(new Intent(MainActivity.this, LockScreenService.class));
    		
			}	
			
		}
		);
		
		Button btn_stop = (Button)findViewById(R.id.btn_stop);
		btn_stop.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				stopService(new Intent(MainActivity.this, LockScreenService.class));
			}	
			
		}
		);
		
		Button btn_alive = (Button)findViewById(R.id.btn_alive);
		btn_alive.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				sendBroadcast(new Intent("com.locking.action.isAlive"));
			}	
			
		}
		);

	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}