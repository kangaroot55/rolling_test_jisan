package com.example.locking;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CanvasTestActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_canvastest);

		Button go_picadd = (Button) findViewById(R.id.btn_canvas_add);
		go_picadd.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {
				mycustomview vm = new mycustomview(CanvasTestActivity.this);
				setContentView(vm);

			}

		});

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

}
