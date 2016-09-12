package com.suken.bridgedetection.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.suken.bridgedetection.R;
import com.suken.bridgedetection.bean.SynchMaintenlogListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/25.
 */
public class OfOrderListActAdapter extends BaseAdapter{
    private Context mContext;
    private LayoutInflater inflater;
    List<SynchMaintenlogListBean> synchMaintenlogListBeen = new ArrayList<SynchMaintenlogListBean>();

    public OfOrderListActAdapter(Context context) {
        mContext = context;
        inflater = LayoutInflater.from(mContext);
    }

    public void setData(List<SynchMaintenlogListBean> list){
        synchMaintenlogListBeen = list;
    }
    @Override
    public int getCount() {
        return synchMaintenlogListBeen.size();
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
        SynchMaintenlogListBean bean = synchMaintenlogListBeen.get(position);
        holder.oforderlist_item_tv1.setText(bean.getYhzh());
        holder.oforderlist_item_tv2.setText(bean.getBhmc());
        holder.oforderlist_item_tv3.setText(bean.getDw());
        holder.oforderlist_item_tv4.setText(bean.getWxsl());


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
