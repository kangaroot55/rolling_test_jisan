package com.example.locking;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Gallery.LayoutParams;



public class ImageAdapter extends BaseAdapter {
    int mGalleryItemBackground;
    private Context mContext;
    private ImageView[] iv;
    /*
    private Integer[] mImageIds = {
    	R.drawable.test1 ,
    	R.drawable.test2 ,
    	R.drawable.test3 ,
    	R.drawable.test4 ,
    	R.drawable.test5 ,
    	R.drawable.test6 ,
    	R.drawable.test7 ,
    	R.drawable.test8 ,
    	R.drawable.test9 ,
    	R.drawable.test10 ,
    };
    */
    
    private Integer[] mImageIds = {
        	R.drawable.back_1 ,
        	R.drawable.back_1 ,
        	R.drawable.back_1 ,
        	R.drawable.back_1 ,
        	R.drawable.back_1 ,
        	R.drawable.back_1 ,
        	R.drawable.back_1 ,
        	R.drawable.back_1 ,
        };
        
    private int cnt;

    public ImageAdapter(Context c) {
        mContext = c;
        cnt = mImageIds.length;
        iv = new ImageView[cnt];
       
       
        for(int i=0; i<cnt; i++){
        	 iv[i] = new ImageView(mContext);
        	 iv[i].setImageResource(mImageIds[i]);
        	 iv[i].setScaleType(ImageView.ScaleType.FIT_XY);
        	 iv[i].setLayoutParams(new Gallery.LayoutParams(480, 360));

        }
    }

    public int getCount() {
        return cnt;
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        	return iv[position];
    }
}