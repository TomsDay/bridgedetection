package com.suken.bridgedetection.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.suken.bridgedetection.R;
import com.suken.bridgedetection.activity.ProjectAcceptanceActivity;
import com.suken.bridgedetection.bean.MaintenanceOfOrderBean;
import com.suken.bridgedetection.bean.MaintenanceTableItemBean;
import com.suken.bridgedetection.bean.ProjectAcceptanceBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/8.
 */
public class ProjectAcceptanceListAdapter extends BaseAdapter{
    private List<ProjectAcceptanceBean> list = new ArrayList<ProjectAcceptanceBean>();
    private Context mContext;
    private LayoutInflater inflater;
    public ProjectAcceptanceListAdapter(Context context){
        this.mContext = context;
        inflater = LayoutInflater.from(mContext);
    }
    public void setData(List<ProjectAcceptanceBean> list) {
        this.list = list;
    }

    public List<ProjectAcceptanceBean> getData() {
        return list;
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
        if(view == null){
            view = inflater.inflate(R.layout.projectacceptancelist_item, null);
            holder = new HolderView(view);
            view.setTag(holder);
        }else{
            holder = (HolderView) view.getTag();
        }
        ProjectAcceptanceBean bean = list.get(position);
        holder.projectacceptancelist_item_tv1.setText(bean.getCustodyUnit()+"");
        holder.projectacceptancelist_item_tv2.setText(bean.getSerialNumber()+"");
        holder.projectacceptancelist_item_tv3.setText(bean.getMaintenanceUnit()+"");
        holder.projectacceptancelist_item_tv4.setText(bean.getConstructionStartTime()+"-"+bean.getConstructionEndTime());
        return view;
    }
    class HolderView {
        private TextView projectacceptancelist_item_tv1,
                projectacceptancelist_item_tv2,
                projectacceptancelist_item_tv3,
                projectacceptancelist_item_tv4;

        public HolderView(View v){
            projectacceptancelist_item_tv1 = (TextView) v.findViewById(R.id.projectacceptancelist_item_tv1);
            projectacceptancelist_item_tv2 = (TextView) v.findViewById(R.id.projectacceptancelist_item_tv2);
            projectacceptancelist_item_tv3 = (TextView) v.findViewById(R.id.projectacceptancelist_item_tv3);
            projectacceptancelist_item_tv4 = (TextView) v.findViewById(R.id.projectacceptancelist_item_tv4);
        }
    }
}
