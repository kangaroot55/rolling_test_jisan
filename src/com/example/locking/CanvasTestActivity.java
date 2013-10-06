package com.example.locking;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.facebook.Request;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.android.Facebook;
import com.facebook.model.GraphUser;
import com.facebook.widget.ProfilePictureView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class CanvasTestActivity extends Activity {

	final String TAG = "CanvasTestActivity!!!!sibal";
	private ProfilePictureView profilePictureView;
	ProfilePictureView ppv;
	private UiLifecycleHelper uiHelper;
	
	/*
	private Session.StatusCallback callback = new Session.StatusCallback() {
	    @Override
	    public void call(final Session session, final SessionState state, final Exception exception) {
	        onSessionStateChange(session, state, exception);
	    }
	};
	*/
	
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_canvastest);
		/*
		Session session = Session.getActiveSession();
	    if (session != null && session.isOpened()) {
	        // Get the user's data
	        makeMeRequest(session);
	    }

	    ppv = (ProfilePictureView)findViewById(R.id.profilepicview);
		ppv.setCropped(true);
*/		

		
		
		Button go_picadd = (Button) findViewById(R.id.btn_canvas_add);
		go_picadd.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {
				mycustomview vm = new mycustomview(CanvasTestActivity.this);
				setContentView(vm);


				
				
			}
				
				

		});

		
		
		
		
		
		
		//kakao url function
		
		
		Button go_kakao_url = (Button) findViewById(R.id.btn_kakaotalk_url);
		go_kakao_url.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {

				KakaoLink kakaoLink = KakaoLink.getLink(getApplicationContext());

				// check, intent is available.
				if (!kakaoLink.isAvailableIntent())
					return;

				/**
				 * @param activity
				 * @param url
				 * @param message
				 * @param appId
				 * @param appVer
				 * @param appName
				 * @param encoding
				 */
				try {
					kakaoLink.openKakaoLink(CanvasTestActivity.this, "http://link.kakao.com/?test-android-app",
							"First KakaoLink Message for send url.", getPackageName(), getPackageManager()
									.getPackageInfo(getPackageName(), 0).versionName, "KakaoLink Test App", "UTF-8");
				}
				catch (NameNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		Button go_kakao_app = (Button) findViewById(R.id.btn_kakaotalk_app);
		go_kakao_app.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {

				ArrayList<Map<String, String>> metaInfoArray = new ArrayList<Map<String, String>>();

				// If application is support Android platform.
				Map<String, String> metaInfoAndroid = new Hashtable<String, String>(1);
				metaInfoAndroid.put("os", "android");
				metaInfoAndroid.put("devicetype", "phone");
				metaInfoAndroid.put("installurl", "market://details?id=com.kakao.talk");
				metaInfoAndroid.put("executeurl", "kakaoLinkTest://startActivity");

				Map<String, String> metaInfoIOS = new Hashtable<String, String>(1);
				metaInfoIOS.put("os", "ios");
				metaInfoIOS.put("devicetype", "phone");
				metaInfoIOS.put("installurl", "your iOS app install url");
				metaInfoIOS.put("executeurl", "kakaoLinkTest://startActivity");

				// add to array
				metaInfoArray.add(metaInfoAndroid);
				metaInfoArray.add(metaInfoIOS);

				// Recommended: Use application context for parameter.
				KakaoLink kakaoLink = KakaoLink.getLink(getApplicationContext());

				// check, intent is available.
				if (!kakaoLink.isAvailableIntent())
					return;

				/**
				 * @param activity
				 * @param url
				 * @param message
				 * @param appId
				 * @param appVer
				 * @param appName
				 * @param encoding
				 * @param metaInfoArray
				 */
				try {
					kakaoLink.openKakaoAppLink(CanvasTestActivity.this, "http://link.kakao.com/?test-android-app",
							"First KakaoLink Message for send app data.", getPackageName(), getPackageManager()
									.getPackageInfo(getPackageName(), 0).versionName, "KakaoLink Test App", "UTF-8",
							metaInfoArray);
				}
				catch (NameNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		});
		
		
		
		

	}
	
	public Bitmap getUserPic(String userID) {
	    String imageURL;
	    Bitmap bitmap = null;
	    Log.d(TAG, "Loading Picture");
	    imageURL = "http://graph.facebook.com/"+userID+"/picture?type=small";
	    try {
	        bitmap = BitmapFactory.decodeStream((InputStream)new URL(imageURL).getContent());
	    } catch (Exception e) {
	        Log.d("TAG", "Loading Picture FAILED");
	        e.printStackTrace();
	    }
	    return bitmap;
	}

	protected class mycustomview extends View {

		public mycustomview(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
		}

		public void onDraw(Canvas canvas) {

			Resources res = getResources();
			BitmapDrawable bd = (BitmapDrawable) res.getDrawable(R.drawable.ic_launcher);
			Bitmap bit = bd.getBitmap();

			canvas.save();
			canvas.rotate(90, (bit.getWidth()) / 2, (bit.getHeight()) / 2);
			canvas.drawBitmap(bit, 0, 0, null);
			canvas.restore();
			// canvas.drawBitmap(bit, 120, 180, null);

		}
	}
	

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Session.getActiveSession().onActivityResult(this, requestCode, resultCode, data);
    }
/*
    private void onSessionStateChange(final Session session, SessionState state, Exception exception) {
        if (session != null && session.isOpened()) {
            // Get the user's data.
            makeMeRequest(session);
        }
    }
    
    private void makeMeRequest(final Session session) {
        // Make an API call to get user data and define a 
        // new callback to handle the response.
        Request request = Request.newMeRequest(session, new Request.GraphUserCallback() {
            @Override
            public void onCompleted(GraphUser user, Response response) {
                // If the response is successful
                if (session == Session.getActiveSession()) {
                    if (user != null) {
                        // Set the id for the ProfilePictureView
                        // view that in turn displays the profile picture.
                        profilePictureView.setProfileId(user.getId());
                        // Set the Textview's text to the user's name.
                        TextView tv = (TextView)findViewById(R.id.tv_test_friendlist);
        				tv.setText(user.getName());
                    }
                }
                if (response.getError() != null) {
                    // Handle errors, will do so later.
                }
            }
        });
        request.executeAsync();
    } 
*/

}
