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
import com.suken.bridgedetection.bean.ProjacceptBean;
import com.suken.bridgedetection.bean.ProjectAcceptanceBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/8.
 */
public class ProjectAcceptanceListAdapter extends BaseAdapter{
    private List<ProjectAcceptanceBean> list = new ArrayList<ProjectAcceptanceBean>();

    private List<ProjacceptBean> projacceptBeen = new ArrayList<ProjacceptBean>();

    private Context mContext;
    private LayoutInflater inflater;
    public ProjectAcceptanceListAdapter(Context context){
        this.mContext = context;
        inflater = LayoutInflater.from(mContext);
    }
    public void setData(List<ProjacceptBean> list) {
        this.projacceptBeen = list;
    }

    public List<ProjectAcceptanceBean> getData() {
        return list;
    }

    @Override
    public int getCount() {
        return projacceptBeen.size();
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
        ProjacceptBean bean = projacceptBeen.get(position);
        holder.projectacceptancelist_item_tv1.setText(bean.getYhtzdno()+"");
        holder.projectacceptancelist_item_tv2.setText(bean.getGydwName()+"");

        String sgka = bean.getSgks();
        String sgjs = bean.getSgjs();
        if (sgka != null) {
            sgka = sgka.split(" ")[0];
        }
        if (sgjs != null) {
            sgjs = sgjs.split(" ")[0];
        }
        holder.projectacceptancelist_item_tv3.setText(sgka+" - "+sgjs);
        holder.projectacceptancelist_item_tv4.setText(bean.getSgfzry());

        holder.projectacceptancelist_item_line5.setVisibility(View.GONE);
        holder.projectacceptancelist_item_tv5.setVisibility(View.GONE);
        return view;
    }
    class HolderView {
        private TextView projectacceptancelist_item_tv1,
                projectacceptancelist_item_tv2,
                projectacceptancelist_item_tv3,
                projectacceptancelist_item_tv4,
                projectacceptancelist_item_tv5;

        private View projectacceptancelist_item_line5;

        public HolderView(View v){
            projectacceptancelist_item_tv1 = (TextView) v.findViewById(R.id.projectacceptancelist_item_tv1);
            projectacceptancelist_item_tv2 = (TextView) v.findViewById(R.id.projectacceptancelist_item_tv2);
            projectacceptancelist_item_tv3 = (TextView) v.findViewById(R.id.projectacceptancelist_item_tv3);
            projectacceptancelist_item_tv4 = (TextView) v.findViewById(R.id.projectacceptancelist_item_tv4);
            projectacceptancelist_item_tv5 = (TextView) v.findViewById(R.id.projectacceptancelist_item_tv5);

            projectacceptancelist_item_line5 = v.findViewById(R.id.projectacceptancelist_item_line5);
        }
    }
}
