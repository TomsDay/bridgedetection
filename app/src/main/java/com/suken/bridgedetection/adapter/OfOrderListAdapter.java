package com.suken.bridgedetection.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.suken.bridgedetection.R;
import com.suken.bridgedetection.bean.SynchMaintenlogBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/4.
 */
public class OfOrderListAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater inflater;
    List<SynchMaintenlogBean> synchMaintenlogBeen = new ArrayList<SynchMaintenlogBean>();

    public OfOrderListAdapter(Context context) {
        mContext = context;
        inflater = LayoutInflater.from(mContext);
    }

    public void setData(List<SynchMaintenlogBean> list){
        synchMaintenlogBeen = list;
    }
    @Override
    public int getCount() {
        return synchMaintenlogBeen.size();
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
    public View getView(final int position, View view, ViewGroup viewGroup) {
        HolderView holder = null;
        if(view == null){
            view = inflater.inflate(R.layout.oforderlist_item, null);
            holder = new HolderView(view);
            view.setTag(holder);
        }else{
            holder = (HolderView) view.getTag();
        }
        SynchMaintenlogBean bean = synchMaintenlogBeen.get(position);
        holder.oforderlist_item_tv1.setText(bean.getBno());
        holder.oforderlist_item_tv2.setText(bean.getWxbmmc());
        holder.oforderlist_item_tv3.setText(bean.getWxrq());
        holder.oforderlist_item_tv4.setText(bean.getFzry());


        return view;
    }



    class HolderView{
        private TextView oforderlist_item_tv1,
                oforderlist_item_tv2,
                oforderlist_item_tv3,
                oforderlist_item_tv4;

        private LinearLayout oforderlist_item_Layout;

        public HolderView(View v){
            oforderlist_item_tv1 = (TextView) v.findViewById(R.id.oforderlist_item_tv1);
            oforderlist_item_tv2 = (TextView) v.findViewById(R.id.oforderlist_item_tv2);
            oforderlist_item_tv3 = (TextView) v.findViewById(R.id.oforderlist_item_tv3);
            oforderlist_item_tv4 = (TextView) v.findViewById(R.id.oforderlist_item_tv4);

            oforderlist_item_Layout = (LinearLayout) v.findViewById(R.id.oforderlist_item_Layout);
        }
    }

}
