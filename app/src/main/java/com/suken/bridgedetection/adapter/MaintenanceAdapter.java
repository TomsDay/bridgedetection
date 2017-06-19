package com.suken.bridgedetection.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.suken.bridgedetection.BridgeDetectionApplication;
import com.suken.bridgedetection.R;
import com.suken.bridgedetection.activity.MaintenanceLogActivity;
import com.suken.bridgedetection.activity.MaintenanceLogListActivity;
import com.suken.bridgedetection.activity.MaintenanceLogUpLoadActivity;
import com.suken.bridgedetection.activity.MaintenanceOfOrderListActivity;
import com.suken.bridgedetection.activity.MaintenanceRequisitionListActivity;
import com.suken.bridgedetection.activity.MaintenanceRequisitionUpActivity;
import com.suken.bridgedetection.activity.MaintenanceTableListActivity;
import com.suken.bridgedetection.activity.MaintenanceOfOrderActivity;
import com.suken.bridgedetection.activity.MaintenanceTableActivity;
import com.suken.bridgedetection.activity.ProjectAcceptanceActivity;
import com.suken.bridgedetection.activity.ProjectAcceptanceListActivity;
import com.suken.bridgedetection.activity.ProjectAcceptanceListUpLoadActivity;
import com.suken.bridgedetection.util.Logger;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/6/4.
 */
public class MaintenanceAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<String> list = new ArrayList<String>();
    private ArrayList<String> submitList = new ArrayList<String>();
    private LayoutInflater inflater;

    public MaintenanceAdapter(Context context) {
        mContext = context;
        inflater = LayoutInflater.from(mContext);
        getData();
    }

    String str = "高速公路";

    public void getData() {

        list.add(str + "养护巡查日志");
//        list.add(str + "维修保养通知单");
        list.add(str + "维修保养日志");

        list.add(str + "施工安全检查表");
//        list.add(str + "维修保养工程验收申请");

        list.add(str + "维修保养工程现场验收");
    }

    public void setData(ArrayList<String> list) {
        this.submitList = list;
    }

    @Override
    public int getCount() {
        return list.size();
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
        if (view == null) {
            view = inflater.inflate(R.layout.maintenance_item, null);
            holder = new HolderView(view);
            view.setTag(holder);
        } else {
            holder = (HolderView) view.getTag();
        }
        holder.maintenance_item_name.setText(list.get(position));
//        if (position == 1 || position == 4) {
//            holder.maintenance_item_name.setTextColor(Color.RED);
//        }
        if (position == 1) {
            holder.maintenance_item_new.setText("查看");
        } else {
            holder.maintenance_item_new.setText("新建");
        }
        holder.maintenance_item_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String roles = BridgeDetectionApplication.mCurrentUser.getRoles();
                Log.i("aaa", "onClick: " + roles);
                Intent in = new Intent();
                if (position == 0) {

                    //日常养护权限

                    if (roles.contains("highway_yhxcy")
                            || roles.contains("highway_yhgcs")) {
                        in.setClass(mContext, MaintenanceTableActivity.class);
                    } else {
                        Toast toast = Toast.makeText(mContext, "无权限", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                        return;
                    }
//                } else if (position == 1) {
//                    if (roles.contains("highway_zxxdy")) {
//                        in.setClass(mContext, MaintenanceRequisitionListActivity.class);
//                        Logger.e("aaa", str + "维修保养通知单");
//                    } else {
//                        Toast toast = Toast.makeText(mContext, "无权限", Toast.LENGTH_LONG);
//                        toast.setGravity(Gravity.CENTER, 0, 0);
//                        toast.show();
//                        return;
//                    }

                } else if (position == 1) {
                    if (roles.contains("highway_rcyhwxgcs")) {
                        in.setClass(mContext, MaintenanceLogListActivity.class);
                    } else {
                        Toast toast = Toast.makeText(mContext, "无权限", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                        return;
                    }
                } else if (position == 2) {
                    if (roles.contains("highway_rcyhaqjcy")
                            || roles.contains("highway_yhgcs")) {
                        in.setClass(mContext, MaintenanceOfOrderActivity.class);
                    } else {
                        Toast toast = Toast.makeText(mContext, "无权限", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                        return;
                    }
//                } else if (position == 3) {
//                    if (roles.contains("highway_zxyssq")) {
//                        in.setClass(mContext, MaintenanceTableActivity.class);
//                    Logger.e("aaa",str + "维修保养工程验收申请");
//                    } else {
//                        Toast toast = Toast.makeText(mContext, "无权限", Toast.LENGTH_LONG);
//                        toast.setGravity(Gravity.CENTER, 0, 0);
//                        toast.show();
//                        return;
//                    }

                } else if (position == 3) {
                    if (roles.contains("highway_rcyhysy")
                            || roles.contains("highway_yhgcs")) {
                        in.setClass(mContext, ProjectAcceptanceListActivity.class);
                    } else {
                        Toast toast = Toast.makeText(mContext, "无权限", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                        return;
                    }

                } else {
                    return;
                }

                mContext.startActivity(in);
            }
        });
//        判断是否有待提交的
        String submitNum = submitList.get(position);
        if (submitNum.equals("0")) {
            holder.maintenance_item_submit.setVisibility(View.GONE);
        } else {
            holder.maintenance_item_submit.setText("待提交：" + submitNum);
            holder.maintenance_item_submit.setVisibility(View.VISIBLE);
        }

        holder.maintenance_item_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent();
                if (position == 0) {
                    in.setClass(mContext, MaintenanceTableListActivity.class);
//                } else if (position == 1) {
//                    in.setClass(mContext, MaintenanceRequisitionUpActivity.class);
                } else if (position == 1) {
                    in.setClass(mContext, MaintenanceLogUpLoadActivity.class);
                } else if (position == 2) {
                    in.setClass(mContext, MaintenanceOfOrderListActivity.class);
//                } else if (position == 4) {

                } else if (position == 3) {
                    in.setClass(mContext, ProjectAcceptanceListUpLoadActivity.class);
                } else {
                    return;
                }
                mContext.startActivity(in);

            }
        });
        return view;
    }

    class HolderView {
        private TextView maintenance_item_name,
                maintenance_item_new,
                maintenance_item_submit;

        public HolderView(View v) {
            maintenance_item_name = (TextView) v.findViewById(R.id.maintenance_item_name);
            maintenance_item_new = (TextView) v.findViewById(R.id.maintenance_item_new);
            maintenance_item_submit = (TextView) v.findViewById(R.id.maintenance_item_submit);
        }
    }
}
