package com.louis.ayn.ofsta;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {

    private Context context;
    private Integer[] imageIds = {
            R.mipmap.ic_app_icon, R.mipmap.ic_app_icon,
            R.mipmap.ic_app_icon, R.mipmap.ic_app_icon,
            R.mipmap.ic_app_icon, R.mipmap.ic_app_icon,
            R.mipmap.ic_app_icon, R.mipmap.ic_app_icon,
    };

    public ImageAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return imageIds.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ImageView iv;
        if (convertView == null) {
            iv = new ImageView(context);
            iv.setLayoutParams(new GridView.LayoutParams(90, 90));
            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
            iv.setPadding(8, 8, 8, 8);
        } else {
            iv = (ImageView) convertView;
        }

        iv.setImageResource(imageIds[position]);

        return iv;
    }
}
