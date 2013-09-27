package com.example.locking;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Gallery.LayoutParams;
import android.widget.TextView;



public class ImageAdapter extends BaseAdapter {
    int mGalleryItemBackground;
    private Context mContext;
    private ImageView[] iv;
    private TextView[] tv;
    
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
        	R.drawable.lockpic ,
        	R.drawable.lockpic ,
        	R.drawable.lockpic ,
        	R.drawable.lockpic ,
        	R.drawable.lockpic ,
        	R.drawable.lockpic ,
        	R.drawable.lockpic ,
        	R.drawable.lockpic ,
        };
        
    private Integer[] dates = {
    		130916,
    		130917,
    		130918,
    		130919,
    		130910,
    		130913,
    		130912,
    		130911,
    		
        };

    private int cnt;

    public ImageAdapter(Context c) {
        mContext = c;
        cnt = mImageIds.length;
        iv = new ImageView[cnt];
        tv = new TextView[cnt];
       
        for(int i=0; i<cnt; i++){
        	 iv[i] = new ImageView(mContext);
        	 iv[i].setImageResource(mImageIds[i]);
        	 iv[i].setScaleType(ImageView.ScaleType.FIT_XY);
        	 iv[i].setLayoutParams(new Gallery.LayoutParams(480, 720));
        	 
        	 tv[i] = new TextView(mContext);
        	 tv[i].setText("It's album mxxxxx fucker!");
        	 
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