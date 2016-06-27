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
    ArrayList<MaintenanceLogListBean> listBeen = new ArrayList<MaintenanceLogListBean>();
    private ArrayList<MaintenanceLogBean> maintenanceLogBeen = new ArrayList<MaintenanceLogBean>();
    private int type;


    public MaintenanceLogListAdapter(Context context) {
        mContext = context;
        inflater = LayoutInflater.from(mContext);
    }
    public void setDate( ArrayList<MaintenanceLogListBean> list){
        listBeen = list;
        type = 0;
    }
    public void setDate1( ArrayList<MaintenanceLogBean> list){
        maintenanceLogBeen = list;
        type = 1;
    }
    public ArrayList<MaintenanceLogListBean> getDate(){
        return listBeen;
    }

    @Override
    public int getCount() {
        return type==0? listBeen.size():maintenanceLogBeen.size();
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
        if (type == 0) {
            final MaintenanceLogListBean bean = listBeen.get(position);

            holder.maintenanceloglist_item_tv1.setText(bean.getGydwId());
            holder.maintenanceloglist_item_tv2.setText(bean.getGydwName());
            holder.maintenanceloglist_item_tv3.setText(bean.getCreatetime());
            holder.maintenanceloglist_item_tv4.setText(bean.getCreator());
        }else{
            MaintenanceLogBean bean = maintenanceLogBeen.get(position);
            holder.maintenanceloglist_item_tv1.setText(bean.getSerialNumber());
            holder.maintenanceloglist_item_tv2.setText(bean.getCustodyUnit());
            holder.maintenanceloglist_item_tv3.setText(bean.getDate());
            holder.maintenanceloglist_item_tv4.setText(bean.getPrincipal());

        }




        holder.maintenanceloglist_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//
                final String[] names = { "生成维修保养日志", "取消" };
                new AlertDialog.Builder(mContext)
                        .setItems(names, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                Logger.e("aaa", "which++" + which);
                                switch (which) {
                                    case 0:
                                        Intent in = new Intent(mContext, MaintenanceLogActivity.class);
                                        if (type == 0) {
                                            in.putExtra("bean", listBeen.get(position));
                                        }else{
                                            in.putExtra("id", maintenanceLogBeen.get(position).getId());
                                        }

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
