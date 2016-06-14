package com.suken.bridgedetection.adapter;

import android.graphics.Color;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.suken.bridgedetection.Constants;
import com.suken.bridgedetection.R;
import com.suken.bridgedetection.activity.MaintenanceTableActivity;
import com.suken.bridgedetection.bean.MaintenanceItemBean;
import com.suken.bridgedetection.util.DateUtil;
import com.suken.bridgedetection.util.UiUtil;
import com.suken.bridgedetection.widget.DateTimePickDialogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/5.
 */
public class MaintenanceTableAdapter extends BaseAdapter {
    private ArrayList<MaintenanceItemBean> list = new ArrayList<MaintenanceItemBean>();
    private MaintenanceTableActivity mActivity;
    private LayoutInflater inflater;
    private int ClickImagePositon;
    private String dateTime;


    public MaintenanceTableAdapter(MaintenanceTableActivity context) {
        this.mActivity = context;
        inflater = LayoutInflater.from(mActivity);


    }

    public void setData(ArrayList<MaintenanceItemBean> list) {
        this.list = list;
    }

    public ArrayList<MaintenanceItemBean> getData() {
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
        final MaintenanceItemBean bean = list.get(position);
        if (view == null) {
            view = inflater.inflate(R.layout.maintenance_table_item, null);
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
        setDateTime(holder);
//        holder.img_video_layout.setVisibility(View.GONE); //隐藏拍照
//        holder.form_column.setText("查看情况:"+(position+1));
//        holder.qslx_title.setText("位置");
//        holder.qslx_title.setText("检查内容");
//        holder.byyj_title.setText("检查时间");

        SpinnerAdapter mAdapter = new SpinnerAdapter();
        mAdapter.setItem(bean.getmImages());
        holder.img_spinner.setAdapter(mAdapter);


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
        holder.xiangji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClickImagePositon = position;
               mActivity.jumpToMedia(position, Constants.REQUEST_CODE_CAMERA, null);

            }
        });
        holder.video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mActivity.jumpToMedia(position, Constants.REQUEST_CODE_VIDEO, null);
                mImageOrVideoClick.clickVideo(position);
            }
        });


        return view;
    }
    public void setDateTime(final HolderView holder){
        dateTime = DateUtil.getDate();
        holder.item_checkTime_edit.setText(dateTime);
        holder.item_checkTime_edit.setKeyListener(null);
        holder.item_checkTime_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(
                        mActivity, dateTime, new DateTimePickDialogUtil.ReturnTime() {
                    @Override
                    public void getTime(String time) {
                        dateTime = time;
                        holder.item_checkTime_edit.setText(dateTime);
                    }
                });
            }
        });
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
                byyj_edit,
                item_checkTime_edit;

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
            item_checkTime_edit = (EditText) view.findViewById(R.id.item_checkTime_edit);

            img_spinner = (Spinner) view.findViewById(R.id.img_spinner);


            item_Line = view.findViewById(R.id.item_Line);
        }
    }

    ImageOrVideoClick mImageOrVideoClick;

    public void setImageOrVideoClick(ImageOrVideoClick imageOrVideoClick) {
        mImageOrVideoClick = imageOrVideoClick;
    }

    public interface ImageOrVideoClick {
        public void clickImage(int position);

        public void clickVideo(int position);
    }

    private class SpinnerAdapter extends BaseAdapter {
        private List<MaintenanceItemBean.ImageDesc> mImages = new ArrayList<MaintenanceItemBean.ImageDesc>();

        @Override
        public int getCount() {
            return mImages.size();
        }

        public void setItem(List<MaintenanceItemBean.ImageDesc> list){
            mImages = list;
        }

        @Override
        public MaintenanceItemBean.ImageDesc getItem(int position) {
            return mImages.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            TextView view = new TextView(mActivity);
            MaintenanceItemBean.ImageDesc desc = getItem(position);
            view.setText("照片：  " + (position + 1));
            view.setTag(desc);
            view.setTextColor(Color.RED);
            view.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 9);
            view.setHeight((int) (15 * UiUtil.getDp(mActivity)));
            view.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    MaintenanceItemBean.ImageDesc desc = (MaintenanceItemBean.ImageDesc) v.getTag();
                    mActivity.jumpToMedia(ClickImagePositon, Constants.REQUEST_CODE_EDIT_IMG, desc);
                }
            });
            return view;
        }

    }
}
