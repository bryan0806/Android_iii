package com.example.photogallery;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager g = (ViewPager)findViewById(R.id.gallery);
        g.setAdapter(arg0);
    }
    
    public class AdapterVerGaleria extends PagerAdapter {

    	private Activity ctx;
    	private ArrayList<ObjectVerGaleria> dataList;

    	public AdapterVerGaleria(Activity act, ArrayList<ObjectVerGaleria> lista) {

    	    ctx = act;
    	    dataList = lista;
    	}

    	public int getCount() {
    	    return dataList.size();
    	}

    	public Object getItem(int pos) {
    	    return pos;
    	}

    	@Override
    	public Object instantiateItem(View collection, int pos)
    	{
    	    ImageView foto = new ImageView(ctx);



    	        //foto.setLayoutParams(new ViewPager.LayoutParams(Gallery.LayoutParams.FILL_PARENT, Gallery.LayoutParams.FILL_PARENT));
    	        foto.setScaleType(ImageView.ScaleType.FIT_XY);
    	        Utils.fetchDrawableOnThread(Utils.getPath(ctx)+"/"+dataList.get(pos).getUrlImg(), foto, true);
    	        ((ViewPager)collection).addView(foto);


    	    return foto;
    	}

    	@Override
    	public void destroyItem(View collection, int position, Object view)
    	{
    	    ((ViewPager)collection).removeView((ImageView)view);

    	}

    	public long getItemId(int pos) {
    	    return pos;
    	}



    	@Override
    	public boolean isViewFromObject(View view, Object object)
    	{
    	    // TODO Auto-generated method stub
    	    return view == ((ImageView)object);
    	}
    	
    
    	
    }
}
