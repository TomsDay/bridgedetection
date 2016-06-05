package com.suken.bridgedetection.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.suken.bridgedetection.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/6/5.
 */
public class MaintenanceTableAdapter extends BaseAdapter{
    private ArrayList<Boolean> list = new ArrayList<Boolean>();
    private Context mContext;
    private LayoutInflater inflater;

    public MaintenanceTableAdapter(Context context) {
        this.mContext = context;
        inflater = LayoutInflater.from(mContext);


    }

    public void setData(ArrayList<Boolean> list){
        this.list = list;
    }
    public ArrayList<Boolean> getData(){
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
            view = inflater.inflate(R.layout.activity_form_item, null);
            holder = new HolderView(view);
            view.setTag(holder);
        }else{
            holder = (HolderView) view.getTag();
        }
        if(list.get(position)){
            holder.form_item_edit_layout.setVisibility(View.VISIBLE);
            holder.arrow_img.setImageResource(R.drawable.xia);

        }else{
            holder.form_item_edit_layout.setVisibility(View.GONE);
            holder.arrow_img.setImageResource(R.drawable.shang);
        }
        holder.img_video_layout.setVisibility(View.GONE); //隐藏拍照
        holder.form_column.setText("查看情况:"+(position+1));
        holder.qslx_title.setText("位置");
        holder.qslx_title.setText("检查内容");
        holder.byyj_title.setText("检查时间");




        holder.from_topLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(list.get(position)){
                    list.set(position, false);
                }else{
                    list.set(position, true);
                }
                notifyDataSetChanged();
            }
        });
        if(position == list.size()-1){
            holder.item_Line.setVisibility(View.GONE);
        }else{
            holder.item_Line.setVisibility(View.VISIBLE);
        }

        return view;
    }
    class HolderView{
        public LinearLayout form_item_edit_layout,
                from_topLayout,
                img_video_layout;

        public ImageView arrow_img;

        private TextView form_column,
                qslx_title,
                qsfw_title,
                byyj_title;

        private EditText qslx_edit,
                qsfw_edit,
                byyj_edit;

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

            qslx_edit = (EditText) view.findViewById(R.id.qslx_edit);
            qsfw_edit = (EditText) view.findViewById(R.id.qsfw_edit);
            byyj_edit = (EditText) view.findViewById(R.id.byyj_edit);

            item_Line = view.findViewById(R.id.item_Line);
        }
    }
}
