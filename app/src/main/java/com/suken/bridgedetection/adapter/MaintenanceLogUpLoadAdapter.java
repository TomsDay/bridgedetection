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
import java.util.List;

/**
 * Created by Administrator on 2016/7/7.
 */
public class MaintenanceLogUpLoadAdapter extends BaseAdapter {
    List<MaintenanceLogBean> listBeen = new ArrayList<MaintenanceLogBean>();
    private Context mContext;
    private LayoutInflater inflater;
    private UpLoadOnceLogData upLoadOnceLogData;

    public MaintenanceLogUpLoadAdapter(Context context,UpLoadOnceLogData upLoadOnceLogData) {
        mContext = context;
       this.upLoadOnceLogData = upLoadOnceLogData;
        inflater = LayoutInflater.from(mContext);
    }
    public void setData(List<MaintenanceLogBean> listBeen){
        this.listBeen = listBeen;
    }
    public List<MaintenanceLogBean> getData(){
        return listBeen;
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
    public View getView(final int position, View view, ViewGroup viewGroup) {
        HolderView holder = null;
        if(view == null){
            view = inflater.inflate(R.layout.maintenancelogupload_item, null);
            holder = new HolderView(view);
            view.setTag(holder);
        }else{
            holder = (HolderView) view.getTag();
        }

        final MaintenanceLogBean bean = listBeen.get(position);

        holder.maintenanceloglistupload_item_tv1.setText(bean.getBytzid()+"");
        holder.maintenanceloglistupload_item_tv2.setText(bean.getBno()+"");
        holder.maintenanceloglistupload_item_tv3.setText(bean.getWxrq()+"");
        holder.maintenanceloglistupload_item_tv4.setText(bean.getJcry()+"");
        holder.maintenanceloglistupload_item_tv5.setText(bean.getFzry()+"");
        holder.maintenanceloglistupload_item_tv6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upLoadOnceLogData.loadData(position);
            }
        });

        return view;
    }
    class HolderView{
        private TextView maintenanceloglistupload_item_tv1,
                maintenanceloglistupload_item_tv2,
                maintenanceloglistupload_item_tv3,
                maintenanceloglistupload_item_tv4,
                maintenanceloglistupload_item_tv5,
                maintenanceloglistupload_item_tv6;
        private LinearLayout maintenanceloglistupload_layout;


        public HolderView(View v){
            maintenanceloglistupload_item_tv1 = (TextView) v.findViewById(R.id.maintenanceloglistupload_item_tv1);
            maintenanceloglistupload_item_tv2 = (TextView) v.findViewById(R.id.maintenanceloglistupload_item_tv2);
            maintenanceloglistupload_item_tv3 = (TextView) v.findViewById(R.id.maintenanceloglistupload_item_tv3);
            maintenanceloglistupload_item_tv4 = (TextView) v.findViewById(R.id.maintenanceloglistupload_item_tv4);
            maintenanceloglistupload_item_tv5 = (TextView) v.findViewById(R.id.maintenanceloglistupload_item_tv5);
            maintenanceloglistupload_item_tv6 = (TextView) v.findViewById(R.id.maintenanceloglistupload_item_tv6);

            maintenanceloglistupload_layout = (LinearLayout) v.findViewById(R.id.maintenanceloglistupload_layout);


        }
    }
    public interface UpLoadOnceLogData{
        public void loadData(int position);
    }
}
