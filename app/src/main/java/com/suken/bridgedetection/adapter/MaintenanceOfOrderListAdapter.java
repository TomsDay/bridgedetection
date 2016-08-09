package com.suken.bridgedetection.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.suken.bridgedetection.R;
import com.suken.bridgedetection.bean.MaintenanceLogBean;
import com.suken.bridgedetection.bean.MaintenanceLogListBean;
import com.suken.bridgedetection.bean.MaintenanceOfOrderBean;
import com.suken.bridgedetection.bean.MaintenanceTableBean;
import com.suken.bridgedetection.util.TextUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/27.
 */
public class MaintenanceOfOrderListAdapter extends BaseAdapter{
    private Context mContext;
    private List<MaintenanceOfOrderBean> maintenanceOfOrderBeen = new ArrayList<MaintenanceOfOrderBean>();
    private LayoutInflater inflater;
    private UpLoadOnceOforderData upLoadOnceOforderData;
    public MaintenanceOfOrderListAdapter(Context context,UpLoadOnceOforderData upLoadOnceOforderData) {
        mContext = context;
        this.upLoadOnceOforderData = upLoadOnceOforderData;
        inflater = LayoutInflater.from(mContext);
    }

    public void setDate( List<MaintenanceOfOrderBean> list){
        maintenanceOfOrderBeen = list;
    }
    public List<MaintenanceOfOrderBean> getDate(){
        return maintenanceOfOrderBeen;
    }


    @Override
    public int getCount() {
        return maintenanceOfOrderBeen.size();
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
            view = inflater.inflate(R.layout.maintenanceoforder_item, null);
            holder = new HolderView(view);
            view.setTag(holder);
        }else{
            holder = (HolderView) view.getTag();
        }
        MaintenanceOfOrderBean bean = maintenanceOfOrderBeen.get(position);
        holder.maintenanceoforder_item_tv1.setText(bean.getId()+"");
        holder.maintenanceoforder_item_tv2.setText(!TextUtil.isEmptyString(bean.getSgdwmc())?bean.getSgdwmc():"");
        holder.maintenanceoforder_item_tv3.setText(bean.getJcsj()+"");
        holder.maintenanceoforder_item_tv4.setText(bean.getJcry()+"");
        holder.maintenanceoforder_item_tv5.setText(bean.getAqgly()  +"");
        holder.maintenanceoforder_item_tv6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upLoadOnceOforderData.loadData(position);

            }
        });
        
        return view;
    }

    class HolderView{
        private TextView maintenanceoforder_item_tv1,
                maintenanceoforder_item_tv2,
                maintenanceoforder_item_tv3,
                maintenanceoforder_item_tv4,
                maintenanceoforder_item_tv5,
                maintenanceoforder_item_tv6;

        public HolderView(View v){
            maintenanceoforder_item_tv1 = (TextView) v.findViewById(R.id.maintenanceoforder_item_tv1);
            maintenanceoforder_item_tv2 = (TextView) v.findViewById(R.id.maintenanceoforder_item_tv2);
            maintenanceoforder_item_tv3 = (TextView) v.findViewById(R.id.maintenanceoforder_item_tv3);
            maintenanceoforder_item_tv4 = (TextView) v.findViewById(R.id.maintenanceoforder_item_tv4);
            maintenanceoforder_item_tv5 = (TextView) v.findViewById(R.id.maintenanceoforder_item_tv5);
            maintenanceoforder_item_tv6 = (TextView) v.findViewById(R.id.maintenanceoforder_item_tv6);
        }
    }
    public interface UpLoadOnceOforderData{
        public void loadData(int position);
    }
}
