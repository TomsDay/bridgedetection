package com.suken.bridgedetection.adapter;

import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.suken.bridgedetection.Constants;
import com.suken.bridgedetection.R;
import com.suken.bridgedetection.activity.MaintenanceTableActivity;
import com.suken.bridgedetection.bean.MaintenanceTableItemBean;
import com.suken.bridgedetection.util.DateUtil;
import com.suken.bridgedetection.util.Logger;
import com.suken.bridgedetection.util.UiUtil;
import com.suken.bridgedetection.widget.DateTimePickDialogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/5.
 */
public class MaintenanceTableAdapter extends BaseAdapter {
    private ArrayList<MaintenanceTableItemBean> list = new ArrayList<MaintenanceTableItemBean>();
    private MaintenanceTableActivity mActivity;
    private LayoutInflater inflater;
    private int ClickImagePositon;
    private String dateTime;


    public MaintenanceTableAdapter(MaintenanceTableActivity context) {
        this.mActivity = context;
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
            view = inflater.inflate(R.layout.maintenance_table_item, null);
            holder = new HolderView(view);
            view.setTag(holder);
            holder.diseaseName_edit.setTag(position);
            holder.unit_edit.setTag(position);
            holder.count_edit.setTag(position);
            holder.address_edit.setTag(position);
            holder.item_checkTime_edit.setTag(position);
        } else {
            holder = (HolderView) view.getTag();
            holder.diseaseName_edit.setTag(position);
            holder.unit_edit.setTag(position);
            holder.count_edit.setTag(position);
            holder.address_edit.setTag(position);
            holder.item_checkTime_edit.setTag(position);
        }

        holder.diseaseName_edit.addTextChangedListener(new Watcher(holder.diseaseName_edit));
        holder.unit_edit.addTextChangedListener(new Watcher(holder.unit_edit));
        holder.count_edit.addTextChangedListener(new Watcher(holder.count_edit));
        holder.address_edit.addTextChangedListener(new Watcher(holder.address_edit));
        holder.item_checkTime_edit.addTextChangedListener(new Watcher(holder.item_checkTime_edit));

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


    class  Watcher implements TextWatcher {
        private EditText editTextID;
        public Watcher(EditText editText) {
            editTextID = editText;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//            int position = (Integer)editTextID.getTag();
//            switch (editTextID.getId()) {
//                case R.id.diseaseName_edit:
//                    Logger.e("aaa", "=======diseaseName_edit=" + charSequence + "=====position" + position);
////                    list.get(mPosition).setDiseaseName(charSequence != null && !"".equals(charSequence) ? charSequence : "");
//                    break;
//            }
        }

        @Override
        public void afterTextChanged(Editable editable) {
            String content = editable.toString();
            int position = (Integer)editTextID.getTag();

            switch (editTextID.getId()) {

                case R.id.diseaseName_edit:
                    Logger.e("aaa","diseaseName_edit="+content+"=====position"+position);
                    list.get(position).setDiseaseName(content!=null&&!"".equals(content)?content:"");
                    break;
                case R.id.unit_edit:
                    Logger.e("aaa","unit_edit==position"+position);
                    list.get(position).setUnit(content!=null&&!"".equals(content)?content:"");
                    break;
                case R.id.count_edit:
                    Logger.e("aaa","count_edit==position"+position);
                    list.get(position).setCount(content!=null&&!"".equals(content)?content:"");
                    break;
                case R.id.address_edit:
                    Logger.e("aaa","address_edit==position"+position);
                    list.get(position).setAddress(content!=null&&!"".equals(content)?content:"");
                    break;
                case R.id.item_checkTime_edit:
                    Logger.e("aaa","item_checkTime_edit==position"+position);
                    list.get(position).setCheckTime(content!=null&&!"".equals(content)?content:"");
                    break;
            }
        }
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

        private EditText diseaseName_edit,
                unit_edit,
                count_edit,
                address_edit,
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

            diseaseName_edit = (EditText) view.findViewById(R.id.diseaseName_edit);
            unit_edit = (EditText) view.findViewById(R.id.unit_edit);
            count_edit = (EditText) view.findViewById(R.id.count_edit);
            address_edit = (EditText) view.findViewById(R.id.address_edit);
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
        private List<MaintenanceTableItemBean.ImageDesc> mImages = new ArrayList<MaintenanceTableItemBean.ImageDesc>();

        @Override
        public int getCount() {
            return mImages.size();
        }

        public void setItem(List<MaintenanceTableItemBean.ImageDesc> list){
            mImages = list;
        }

        @Override
        public MaintenanceTableItemBean.ImageDesc getItem(int position) {
            return mImages.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            TextView view = new TextView(mActivity);
            MaintenanceTableItemBean.ImageDesc desc = getItem(position);
            view.setText("照片：  " + (position + 1));
            view.setTag(desc);
            view.setTextColor(Color.RED);
            view.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 9);
            view.setHeight((int) (15 * UiUtil.getDp(mActivity)));
            view.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    MaintenanceTableItemBean.ImageDesc desc = (MaintenanceTableItemBean.ImageDesc) v.getTag();
                    mActivity.jumpToMedia(ClickImagePositon, Constants.REQUEST_CODE_EDIT_IMG, desc);
                }
            });
            return view;
        }


    }
}
