package com.suken.bridgedetection.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.suken.bridgedetection.R;
import com.suken.bridgedetection.activity.ListBean;
import com.suken.bridgedetection.activity.MaintenanceLogActivity;
import com.suken.bridgedetection.bean.MaintenanceLogBean;
import com.suken.bridgedetection.bean.MaintenanceLogListBean;
import com.suken.bridgedetection.util.Logger;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/6/21.
 */
public class MaintenanceLogListAdapter extends BaseAdapter{
    private Context mContext;
    private LayoutInflater inflater;
    ArrayList<MaintenanceLogBean> listBeen = new ArrayList<MaintenanceLogBean>();
    private ArrayList<MaintenanceLogBean> maintenanceLogBeen = new ArrayList<MaintenanceLogBean>();


    public MaintenanceLogListAdapter(Context context) {
        mContext = context;
        inflater = LayoutInflater.from(mContext);
    }
    public void setDate( ArrayList<MaintenanceLogBean> list){
        listBeen = list;
    }

    public ArrayList<MaintenanceLogBean> getDate(){
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
            view = inflater.inflate(R.layout.maintenanceloglist_item, null);
            holder = new HolderView(view);
            view.setTag(holder);
        }else{
            holder = (HolderView) view.getTag();
        }
        final MaintenanceLogBean bean = listBeen.get(position);

        holder.maintenanceloglist_item_tv1.setText(bean.getBno());
        holder.maintenanceloglist_item_tv2.setText(bean.getWxbmmc());
        holder.maintenanceloglist_item_tv3.setText(bean.getQfrq());
        holder.maintenanceloglist_item_tv4.setText(bean.getQfry());





        holder.maintenanceloglist_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//
                final String[] names = { "生成维修保养日志", "取消" };
                new AlertDialog.Builder(mContext,R.style.NOmengceng_dialog)
                        .setItems(names, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                Logger.e("aaa", "which++" + which);
                                switch (which) {
                                    case 0:
                                        Intent in = new Intent(mContext, MaintenanceLogActivity.class);
                                        in.putExtra("bean", listBeen.get(position));
                                        mContext.startActivity(in);
                                        break;
                                    default:
                                        break;
                                }


                            }
                        })
                        .show();

            }
        });
        return view;
    }
    class HolderView{
        private TextView maintenanceloglist_item_tv1,
                maintenanceloglist_item_tv2,
                maintenanceloglist_item_tv3,
                maintenanceloglist_item_tv4;
        private LinearLayout maintenanceloglist_layout;


        public HolderView(View v){
            maintenanceloglist_item_tv1 = (TextView) v.findViewById(R.id.maintenanceloglist_item_tv1);
            maintenanceloglist_item_tv2 = (TextView) v.findViewById(R.id.maintenanceloglist_item_tv2);
            maintenanceloglist_item_tv3 = (TextView) v.findViewById(R.id.maintenanceloglist_item_tv3);
            maintenanceloglist_item_tv4 = (TextView) v.findViewById(R.id.maintenanceloglist_item_tv4);

            maintenanceloglist_layout = (LinearLayout) v.findViewById(R.id.maintenanceloglist_layout);


        }
    }
}
