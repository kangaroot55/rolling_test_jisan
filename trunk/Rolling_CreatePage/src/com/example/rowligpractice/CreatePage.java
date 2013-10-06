package com.example.rowligpractice;

import java.io.FileNotFoundException;
import java.io.IOException;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore.Images;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class CreatePage extends Activity {

	protected static final int TEMPLATE_1 = 1;
	protected static final int TEMPLATE_2 = 2;

	
	protected static final int REQ_IMAGE_SELECT_PIC_1 = 1;
	protected static final int REQ_IMAGE_SELECT_PIC_2 = 2;
	protected static final int REQ_IMAGE_SELECT_PIC_3 = 3;
	
	boolean isPageOpen = false;

	Animation translateLeftAnim;
	Animation translateRightAnim;
	
	
	ImageView pic_01;
	ImageView pic_02;
	ImageView pic_03;
	EditText text_01; 
	
	Button setting_btn;
	Button member_list_btn;
	
	LinearLayout slidingPage01;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		setContentView(R.layout.activity_createpage);
			
		
		Intent intent = getIntent();
		int seltemplatenum =  intent.getExtras().getInt("TEMPLATE_NUM");
		inflateLayout(seltemplatenum);
		
		pic_01 = (ImageView)findViewById(R.id.imageView1);
		pic_02 = (ImageView)findViewById(R.id.imageView2);
		pic_03 = (ImageView)findViewById(R.id.imageView3);
		text_01 = (EditText)findViewById(R.id.editText1);
		setting_btn = (Button) findViewById(R.id.setting_btn);
		member_list_btn = (Button)findViewById(R.id.member_list_btn);
		
		slidingPage01 = (LinearLayout) findViewById(R.id.member_list_sliding);

        translateLeftAnim = AnimationUtils.loadAnimation(this, R.anim.translate_left);
        translateRightAnim = AnimationUtils.loadAnimation(this, R.anim.translate_right);

        SlidingPageAnimationListener animListener = new SlidingPageAnimationListener();
        translateLeftAnim.setAnimationListener(animListener);
        translateRightAnim.setAnimationListener(animListener);
		
		
				
		pic_01.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				getPhotofromGallery(REQ_IMAGE_SELECT_PIC_1);
			}
			
		});
		
		pic_02.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				getPhotofromGallery(REQ_IMAGE_SELECT_PIC_2);
			}
			
		});
		
		pic_03.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				getPhotofromGallery(REQ_IMAGE_SELECT_PIC_3);
			}
			
		});
		
		
		setting_btn.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						
					}
					
		});
		
		member_list_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (isPageOpen) {
					RelativeLayout templateLayout = (RelativeLayout)findViewById(R.id.templateLayout);
        			enableDisableViewGroup(templateLayout,true);
					
        			slidingPage01.setVisibility(View.GONE);
        			slidingPage01.startAnimation(translateRightAnim);
					
        			((LinearLayout) findViewById(R.id.empty_layer)).setVisibility(View.GONE);
        			findViewById(R.id.empty_layer).setEnabled(false);
        			
        			
        		} else {
        			
        			RelativeLayout templateLayout = (RelativeLayout)findViewById(R.id.templateLayout);
        			enableDisableViewGroup(templateLayout,false);
        			
        			slidingPage01.setVisibility(View.VISIBLE);
        			slidingPage01.startAnimation(translateLeftAnim);
        			
        			((LinearLayout) findViewById(R.id.empty_layer)).setVisibility(View.VISIBLE);
        			findViewById(R.id.empty_layer).setEnabled(true);    			
        			
        		}
			}
			
		});
		
		findViewById(R.id.empty_layer).setOnTouchListener(	new OnTouchListener() {

					@Override
					public boolean onTouch(View arg0, MotionEvent arg1) {
						
						RelativeLayout templateLayout = (RelativeLayout)findViewById(R.id.templateLayout);
	        			enableDisableViewGroup(templateLayout,true);
						
	        			slidingPage01.setVisibility(View.GONE);
	        			slidingPage01.startAnimation(translateRightAnim);
						
	        			((LinearLayout) findViewById(R.id.empty_layer)).setVisibility(View.GONE);
	        			findViewById(R.id.empty_layer).setEnabled(false);
	        			
						return true;
					}
				});
	
		setting_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onPopupButtonClick(setting_btn);
			}
			
		});
		
		text_01.setOnEditorActionListener(new OnEditorActionListener() {

			@Override
			public boolean onEditorAction(TextView v, int actionId,	KeyEvent event) {
				// TODO Auto-generated method stub
				switch(v.getId()) 				{
				case R.id.editText1:		{
					if(event.getAction() == KeyEvent.ACTION_DOWN)
					{
						text_01.setBackgroundResource((Integer) null);
					}
					break;
				}
				}
				
				
				
				return false;
			}
			
		});
		
	}
	
	
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public void onPopupButtonClick(View button) {     
	     //PopupMenu 객체 생성.
	        PopupMenu popup = new PopupMenu(this, button);
	        
	        //설정한 popup XML을 inflate.
	        popup.getMenuInflater().inflate(R.menu.popupmenu, popup.getMenu());
	        //팝업메뉴 클릭 시 이벤트
	        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

				@TargetApi(Build.VERSION_CODES.HONEYCOMB)
				@Override
				public boolean onMenuItemClick(MenuItem item) {
					 switch (item.getItemId()) {
					    case R.id.search:
					     /* Search를 선택했을 때 이벤트 실행 코드 작성 */
					     break;
					     
					    case R.id.add:
					     /* Add를 선택했을 때 이벤트 실행 코드 작성 */
					     break;
					     
					    case R.id.edit:
					     /* Edit를 선택했을 때 이벤트 실행 코드 작성 */
					     break;
					     
					    case R.id.share:
					     /* Share를 선택했을 때 이벤트 실행 코드 작성 */
					     break;         
					    }             
					                return true;
			}
			});        
		popup.show();					
	}			
	        	
	        
	                   
	            
	    
	


	

	
	
	public static void enableDisableViewGroup(ViewGroup viewGroup,	boolean enabled) {
		int childCount = viewGroup.getChildCount();
		for (int i = 0; i < childCount; i++) {
			View view = viewGroup.getChildAt(i);
			if (view.getId() != R.id.setting_btn) {
				view.setEnabled(enabled);
				if (view instanceof ViewGroup) {
					enableDisableViewGroup((ViewGroup) view, enabled);
				}
			}
		}
	}

	
	private void inflateLayout(int template_num){
		
		RelativeLayout templateLayout = (RelativeLayout) findViewById(R.id.templateLayout);
		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		switch(template_num) {
		
		case TEMPLATE_1 : 
			inflater.inflate(R.layout.template_01, templateLayout, true);
			break;
			
		case TEMPLATE_2 :
			inflater.inflate(R.layout.template_02, templateLayout, true);
			break;
			
			
		}
		
		
	}
	
	protected void getPhotofromGallery(int REQ_IMAGE_NUM) {
		
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_PICK);
		intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult( intent, REQ_IMAGE_NUM ); 
        
	
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
		
		Uri thumbnail=null;
		
		if(resultCode != RESULT_OK) { // 에러처리
			 //  if(data != null) {
			//    Log.e(TAG, "resultCode : " + resultCode + " Error!!! ");
			 //  }
			   return;
		}
		
		
		if(data.getData()!=null) {
			thumbnail = data.getData();
		}
		
		
		if(resultCode == RESULT_OK){
			switch(requestCode){
			case REQ_IMAGE_SELECT_PIC_1 :
		
	        if( thumbnail != null ) {         
	        	Bitmap selPhoto;
				//BitmapFactory.Options options = new BitmapFactory.Options();	
				//options.inSampleSize = 4;
				//Bitmap src = BitmapFactory.decodeFile(thumbnail.toString(), options);
				//Bitmap resized = Bitmap.createScaledBitmap(src, src.getWidth(), src.getHeight(), true);
				try {
					selPhoto = Images.Media.getBitmap( getContentResolver(), thumbnail );
					Bitmap resized = Bitmap.createScaledBitmap(selPhoto, pic_01.getWidth(), pic_01.getHeight(), true);
					pic_01.setImageBitmap(resized);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			
				
			}
	        break;
	        
		case REQ_IMAGE_SELECT_PIC_2 :
			
			if( thumbnail != null ) {         
				Bitmap selPhoto;
				try {
					selPhoto = Images.Media.getBitmap( getContentResolver(), thumbnail );
					Bitmap resized = Bitmap.createScaledBitmap(selPhoto, pic_02.getWidth(), pic_02.getHeight(), true);
					pic_02.setImageBitmap(resized);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			break;
			
		case REQ_IMAGE_SELECT_PIC_3 :
			
			if( thumbnail != null ) {         
				Bitmap selPhoto;
				try {
					selPhoto = Images.Media.getBitmap( getContentResolver(), thumbnail );
					Bitmap resized = Bitmap.createScaledBitmap(selPhoto, pic_03.getWidth(), pic_03.getHeight(), true);
					pic_03.setImageBitmap(resized);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			break;
			
		default : 
				
				
			
		
		}
		}
		else {
			
		}
	   }
	
	private class SlidingPageAnimationListener implements AnimationListener {
    	
   	 
		public void onAnimationEnd(Animation animation) {
			if (isPageOpen) {
				slidingPage01.setVisibility(View.INVISIBLE);
				isPageOpen = false;
			} else {
				isPageOpen = true;
			}
		}

		public void onAnimationRepeat(Animation animation) {

		}

		public void onAnimationStart(Animation animation) {

		}

    }
	
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
