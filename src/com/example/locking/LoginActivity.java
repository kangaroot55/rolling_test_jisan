package com.example.locking;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.facebook.FacebookException;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;
import com.facebook.widget.LoginButton.OnErrorListener;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class LoginActivity extends Activity{
	
	private String TAG = "LoginActivity";
	 
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
			
        LoginButton authButton = (LoginButton)findViewById(R.id.authButton);
        authButton.setOnErrorListener(new OnErrorListener() {      
            @Override
            public void onError(FacebookException error) {
                // 여기는 로그인 에러
            }
        });
       
        
        Button go_signup = (Button)findViewById(R.id.btn_signup);
        go_signup.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				startActivity(new Intent(LoginActivity.this, SignupActivity.class));
    		
			}	
			
		}
		);
		
        Button go_gall = (Button)findViewById(R.id.btn_login);
		go_gall.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				startActivity(new Intent(LoginActivity.this, GalleryViewTest.class));
    		
			}	
			
		}
		);
		
 
//        // set permission list, Don't forget to add email
 //       authButton.setReadPermissions(Arrays.asList("basic_info","email"));
         
        // session state call back event
        authButton.setSessionStatusCallback(new Session.StatusCallback() {
            
        	@Override
            public void call(Session session, SessionState state, Exception exception) {
            	
        		
                if (session.isOpened()) {
                    Request.executeMeRequestAsync(session, new Request.GraphUserCallback() {
                        @Override
                        public void onCompleted(GraphUser user,Response response) {    

                    		if (user != null) { 
                                // 로그인 성공 (user에 정보가 들어있음.)
                            	Log.i(TAG,"User ID "+ user.getId());
                                Log.i(TAG,"Email "+ user.asMap().get("email"));
                            	TextView tv = (TextView)findViewById(R.id.tv_welcome);
                            	tv.setText(user.getUsername());
                            	ImageView iv = (ImageView)findViewById(R.id.login_profile);

                            }
                        }
                    });
                }
            }
        });
        
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Session.getActiveSession().onActivityResult(this, requestCode, resultCode, data);
    }

	
}
