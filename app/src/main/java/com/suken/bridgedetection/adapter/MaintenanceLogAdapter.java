package com.suken.bridgedetection.adapter;

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
import com.suken.bridgedetection.activity.MaintenanceLogActivity;
import com.suken.bridgedetection.bean.MaintenanceTableItemBean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/6/8.
 */
public class MaintenanceLogAdapter extends BaseAdapter {
    private ArrayList<MaintenanceTableItemBean> list = new ArrayList<MaintenanceTableItemBean>();
    private MaintenanceLogActivity mActivity;
    private LayoutInflater inflater;

    public  MaintenanceLogAdapter(MaintenanceLogActivity mActivity){
        this.mActivity = mActivity;
        inflater = LayoutInflater.from(mActivity);
    }
    public void setData(ArrayList<MaintenanceTableItemBean> list) {
        this.list = list;
    }

    public ArrayList<MaintenanceTableItemBean> getData() {
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
        final MaintenanceTableItemBean bean = list.get(position);
        if (view == null) {
            view = inflater.inflate(R.layout.maintenance_log_item, null);
            holder = new HolderView(view);
            view.setTag(holder);
        } else {
            holder = (HolderView) view.getTag();
        }
        if (bean.isShow()) {
            holder.form_item_edit_layout.setVisibility(View.VISIBLE);
            holder.arrow_img.setImageResource(R.drawable.xia);

        } else {
            holder.form_item_edit_layout.setVisibility(View.GONE);
            holder.arrow_img.setImageResource(R.drawable.shang);
        }
        holder.from_topLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bean.isShow()) {
                    list.get(position).setShow(false);
                } else {
                    list.get(position).setShow(true);
                }
                notifyDataSetChanged();
            }
        });
        if (position == list.size() - 1) {
            holder.item_Line.setVisibility(View.GONE);
        } else {
            holder.item_Line.setVisibility(View.VISIBLE);
        }

        return view;
    }
    class HolderView {
        public LinearLayout form_item_edit_layout,
                from_topLayout,
                img_video_layout;

        public ImageView arrow_img,
                xiangji,
                video;

        private TextView form_column,
                qslx_title,
                qsfw_title,
                byyj_title;

        private EditText qslx_edit,
                qsfw_edit,
                byyj_edit;

        private Spinner img_spinner;

        private View item_Line;

        public HolderView(View view) {
            form_item_edit_layout = (LinearLayout) view.findViewById(R.id.form_item_edit_layout);
            from_topLayout = (LinearLayout) view.findViewById(R.id.from_topLayout);
            img_video_layout = (LinearLayout) view.findViewById(R.id.img_video_layout);

            arrow_img = (ImageView) view.findViewById(R.id.arrow_img);

            form_column = (TextView) view.findViewById(R.id.form_column);
            qslx_title = (TextView) view.findViewById(R.id.qslx_title);
            qsfw_title = (TextView) view.findViewById(R.id.qsfw_title);
            byyj_title = (TextView) view.findViewById(R.id.byyj_title);

            xiangji = (ImageView) view.findViewById(R.id.xiangji);
            video = (ImageView) view.findViewById(R.id.video);

            qslx_edit = (EditText) view.findViewById(R.id.qslx_edit);
            qsfw_edit = (EditText) view.findViewById(R.id.qsfw_edit);
            byyj_edit = (EditText) view.findViewById(R.id.byyj_edit);

            img_spinner = (Spinner) view.findViewById(R.id.img_spinner);


            item_Line = view.findViewById(R.id.item_Line);
        }
    }
}
