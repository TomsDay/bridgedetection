package com.suken.bridgedetection.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.suken.bridgedetection.R;
import com.suken.bridgedetection.bean.MaintenanceLogBean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/7.
 */
public class MaintenanceLogUpLoadAdapter extends BaseAdapter {
    ArrayList<MaintenanceLogBean> listBeen = new ArrayList<MaintenanceLogBean>();
    private Context mContext;
    private LayoutInflater inflater;

    public MaintenanceLogUpLoadAdapter(Context context) {
        mContext = context;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return listBeen.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        HolderView holder = null;
        if(view == null){
            view = inflater.inflate(R.layout.maintenanceloglist_item, null);
            holder = new HolderView(view);
            view.setTag(holder);
        }else{
            holder = (HolderView) view.getTag();
        }

        final MaintenanceLogBean bean = listBeen.get(position);

        holder.maintenanceloglistupload_item_tv1.setText(bean.getBno());
        holder.maintenanceloglistupload_item_tv2.setText(bean.getWxbmmc());
        holder.maintenanceloglistupload_item_tv3.setText(bean.getQfrq());
        holder.maintenanceloglistupload_item_tv4.setText(bean.getQfry());
        holder.maintenanceloglistupload_item_tv5.setText(bean.getQfry());

        return view;
    }
    class HolderView{
        private TextView maintenanceloglistupload_item_tv1,
                maintenanceloglistupload_item_tv2,
                maintenanceloglistupload_item_tv3,
                maintenanceloglistupload_item_tv4,
                maintenanceloglistupload_item_tv5;
        private LinearLayout maintenanceloglistupload_layout;


        public HolderView(View v){
            maintenanceloglistupload_item_tv1 = (TextView) v.findViewById(R.id.maintenanceloglistupload_item_tv1);
            maintenanceloglistupload_item_tv2 = (TextView) v.findViewById(R.id.maintenanceloglistupload_item_tv2);
            maintenanceloglistupload_item_tv3 = (TextView) v.findViewById(R.id.maintenanceloglistupload_item_tv3);
            maintenanceloglistupload_item_tv4 = (TextView) v.findViewById(R.id.maintenanceloglistupload_item_tv4);
            maintenanceloglistupload_item_tv4 = (TextView) v.findViewById(R.id.maintenanceloglistupload_item_tv5);

            maintenanceloglistupload_layout = (LinearLayout) v.findViewById(R.id.maintenanceloglistupload_layout);


        }
    }
}
