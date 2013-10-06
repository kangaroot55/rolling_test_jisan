package com.example.rowligpractice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SelectTemplate extends Activity{

	protected static final int SELECTED_TEMPLATE_1 = 1;
	protected static final int SELECTED_TEMPLATE_2 = 2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_seltemplate);
		
		Button temp1 = (Button)findViewById(R.id.template_01);
		Button temp2 = (Button)findViewById(R.id.template_02);
		
		temp1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				selectTemplate(SELECTED_TEMPLATE_1);
			}
			
		});
		
		temp2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				selectTemplate(SELECTED_TEMPLATE_2);
			}
			
		});
		
	}
	
	protected void selectTemplate(int TEMPLATE_NUM){
		Intent intent = new Intent(this, CreatePage.class);
		intent.putExtra("TEMPLATE_NUM", TEMPLATE_NUM);
		startActivity(intent);
	}
	
}
