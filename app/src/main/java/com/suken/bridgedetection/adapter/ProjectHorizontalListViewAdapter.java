package com.suken.bridgedetection.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.suken.bridgedetection.R;
import com.suken.bridgedetection.activity.ImagePagerActivity;
import com.suken.bridgedetection.bean.IVDesc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/15.
 */
public class ProjectHorizontalListViewAdapter extends BaseAdapter {

   List<IVDesc> imageList = new ArrayList<IVDesc>(1);
    private Context mContext;
    private LayoutInflater inflater;

    String ImageArray[] ;
    int ImageIDArray[] ;
    Activity mActivity;

    public ProjectHorizontalListViewAdapter(Activity activity) {
        super();
        mActivity = activity;
        this.mContext = activity;
        inflater = LayoutInflater.from(mContext);
    }


    public void setList(List<IVDesc> list){
        imageList = list;
        int sice = list.size();
        if(sice != 0){
            ImageArray = new String[sice];
            ImageIDArray = new int[sice];
            for(int i = 0; i<sice;i++){
                ImageArray[i] = list.get(i).getPath();
                ImageIDArray[i] =list.get(i).getId();
            }
        }
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
//        if(imageList.size() == 0){
//            return 1+1;
//        }
        return imageList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder holder;
        if(convertView == null){
            convertView = inflater.inflate(R.layout.projecthorizontallistview_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        String ImagePath =  imageList.get(position).getPath();
        holder.image_zp.setImageBitmap(BitmapFactory.decodeFile(ImagePath));

        holder.image_zp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                imageBrower(position);
            }
        });

        return convertView;
    }
    class ViewHolder{
        ImageView image_zp;
        public ViewHolder(View convertView){
            image_zp = (ImageView) convertView.findViewById(R.id.image_zp);

        }
    }
    private void imageBrower(int position) {
        Intent intent = new Intent(mContext, ImagePagerActivity.class);
        // 图片url,为了演示这里使用常量，一般从数据库中或网络中获取
        intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_URLS, ImageArray);
        intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_ID,ImageIDArray);
        intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_INDEX, position);
        mActivity.startActivityForResult(intent, 222);
    }
}
