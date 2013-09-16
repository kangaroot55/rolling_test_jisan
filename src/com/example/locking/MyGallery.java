package com.example.locking;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Transformation;
import android.widget.Gallery;


public class MyGallery extends Gallery{
	private final static String TAG = "MyGallery";
	private Context mContext;
	private static Camera mCamera; 
	public MyGallery(Context context) {
		 this(context, null); 
	}
	public MyGallery(Context context, AttributeSet attrs) {
		 this(context, attrs, 0); 
	}
	public MyGallery(Context context, AttributeSet attrs, int defStyle) {
		 super(context, attrs, defStyle);
		 mContext = context;
		 mCamera = new Camera();
		 setSpacing(-30);  // child view �� ������ �ٿ� ��ġ�� ���� ȿ�� �ش�
		 setLayoutParams(new LayoutParams(60 , 60));
	}

	 protected boolean getChildStaticTransformation(View child, Transformation t) {
		
		final int mCenter =(getWidth() - getPaddingLeft() - getPaddingRight()) / 2 + getPaddingLeft();
		final int childCenter = child.getLeft() + child.getWidth() / 2;
		final int childWidth = child.getWidth();
		
		t.clear();
		t.setTransformationType(Transformation.TYPE_MATRIX);

		float rate = Math.abs((float)(mCenter - childCenter)/ childWidth);
		
		mCamera.save();
		final Matrix matrix = t.getMatrix();

		float zoomAmount = (float) (rate * 200.0);
		mCamera.translate(0.0f, 0.0f, zoomAmount);         
		
		mCamera.getMatrix(matrix);    
		matrix.preTranslate(-(childWidth/2), -(childWidth/2));
		matrix.postTranslate((childWidth/2), (childWidth/2));
		mCamera.restore();
		return true;
    }


}
