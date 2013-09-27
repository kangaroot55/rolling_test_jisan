package com.example.locking;

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
import android.os.Bundle;
import android.util.Log;
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
                            	tv.setText(user.getId());
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
