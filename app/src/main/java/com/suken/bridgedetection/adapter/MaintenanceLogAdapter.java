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
import android.widget.TextView;

import com.suken.bridgedetection.Constants;
import com.suken.bridgedetection.R;
import com.suken.bridgedetection.activity.MaintenanceLogActivity;
import com.suken.bridgedetection.bean.IVDesc;
import com.suken.bridgedetection.bean.MaintenanceLogItemBean;
import com.suken.bridgedetection.bean.MaintenanceTableItemBean;
import com.suken.bridgedetection.util.DateUtil;
import com.suken.bridgedetection.util.Logger;
import com.suken.bridgedetection.util.UiUtil;
import com.suken.bridgedetection.widget.DateTimePickDialogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/8.
 */
public class MaintenanceLogAdapter extends BaseAdapter {
    private ArrayList<MaintenanceLogItemBean> maintenanceLogItemBeen = new ArrayList<MaintenanceLogItemBean>();
    private MaintenanceLogActivity mActivity;
    private LayoutInflater inflater;
    private String dateTime;

    public  MaintenanceLogAdapter(MaintenanceLogActivity mActivity){
        this.mActivity = mActivity;
        inflater = LayoutInflater.from(mActivity);
    }
    public void setData(ArrayList<MaintenanceLogItemBean> list) {
        this.maintenanceLogItemBeen = list;
    }

    public ArrayList<MaintenanceLogItemBean> getData() {
        return maintenanceLogItemBeen;
    }
    @Override
    public int getCount() {
        return maintenanceLogItemBeen.size();
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
        final MaintenanceLogItemBean bean = maintenanceLogItemBeen.get(position);
        if (view == null) {
            view = inflater.inflate(R.layout.maintenance_log_item, null);
            holder = new HolderView(view);
            view.setTag(holder);
            holder.projectName_edit.setTag(position);
            holder.cl_edit.setTag(position);
            holder.unit_edit.setTag(position);
            holder.count_edit.setTag(position);
            holder.address_edit.setTag(position);
            holder.item_checkTime_edit.setTag(position);
        } else {
            holder = (HolderView) view.getTag();
            holder.projectName_edit.setTag(position);
            holder.cl_edit.setTag(position);
            holder.unit_edit.setTag(position);
            holder.count_edit.setTag(position);
            holder.address_edit.setTag(position);
            holder.item_checkTime_edit.setTag(position);
        }
        holder.projectName_edit.addTextChangedListener(new Watcher(holder.projectName_edit));
        holder.cl_edit.addTextChangedListener(new Watcher(holder.cl_edit));
        holder.unit_edit.addTextChangedListener(new Watcher(holder.unit_edit));
        holder.count_edit.addTextChangedListener(new Watcher(holder.count_edit));
        holder.address_edit.addTextChangedListener(new Watcher(holder.address_edit));
        holder.item_checkTime_edit.addTextChangedListener(new Watcher(holder.item_checkTime_edit));

        holder.video_num.setText(bean.getmVideo().size()+"");
        holder.projectName_edit.setText(bean.getProjectName());
        holder.cl_edit.setText(bean.getMaterialName());
        holder.unit_edit.setText(bean.getUnit());
        holder.count_edit.setText(bean.getCount());
        holder.address_edit.setText(bean.getAddress());
        holder.item_checkTime_edit.setText(bean.getCheckTime());
        setDateTime(holder);

        SpinnerAdapter mAdapter = new SpinnerAdapter();
        mAdapter.setItem(bean.getmImages());
        holder.img_spinner.setAdapter(mAdapter);



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
                    maintenanceLogItemBeen.get(position).setShow(false);
                } else {
                    maintenanceLogItemBeen.get(position).setShow(true);
                }
                notifyDataSetChanged();
            }
        });
        if (position == maintenanceLogItemBeen.size() - 1) {
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
//                mImageOrVideoClick.clickVideo(position);
            }
        });

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
                video_num,
                byyj_title;

        private EditText projectName_edit,
                cl_edit,
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
            video_num = (TextView) view.findViewById(R.id.video_num);

            xiangji = (ImageView) view.findViewById(R.id.xiangji);
            video = (ImageView) view.findViewById(R.id.video);

            projectName_edit = (EditText) view.findViewById(R.id.projectName_edit);
            cl_edit = (EditText) view.findViewById(R.id.cl_edit);
            unit_edit = (EditText) view.findViewById(R.id.unit_edit);
            count_edit = (EditText) view.findViewById(R.id.count_edit);
            address_edit = (EditText) view.findViewById(R.id.address_edit);
            item_checkTime_edit = (EditText) view.findViewById(R.id.item_checkTime_edit);

            img_spinner = (Spinner) view.findViewById(R.id.img_spinner);


            item_Line = view.findViewById(R.id.item_Line);
        }
    }
    private int ClickImagePositon;
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

                case R.id.projectName_edit:
                    Logger.e("aaa","diseaseName_edit="+content+"=====position"+position);
                    maintenanceLogItemBeen.get(position).setProjectName(content!=null&&!"".equals(content)?content:"");
                    break;
                case R.id.cl_edit:
                    Logger.e("aaa","diseaseName_edit="+content+"=====position"+position);
                    maintenanceLogItemBeen.get(position).setMaterialName(content!=null&&!"".equals(content)?content:"");
                    break;
                case R.id.unit_edit:
                    Logger.e("aaa","unit_edit==position"+position);
                    maintenanceLogItemBeen.get(position).setUnit(content!=null&&!"".equals(content)?content:"");
                    break;
                case R.id.count_edit:
                    Logger.e("aaa","count_edit==position"+position);
                    maintenanceLogItemBeen.get(position).setCount(content!=null&&!"".equals(content)?content:"");
                    break;
                case R.id.address_edit:
                    Logger.e("aaa","address_edit==position"+position);
                    maintenanceLogItemBeen.get(position).setAddress(content!=null&&!"".equals(content)?content:"");
                    break;
                case R.id.item_checkTime_edit:
                    Logger.e("aaa","item_checkTime_edit==position"+position);
                    maintenanceLogItemBeen.get(position).setCheckTime(content!=null&&!"".equals(content)?content:"");
                    break;
            }
        }
    }
    public void setDateTime(final HolderView holder){
        dateTime = DateUtil.getDate();
        String time = holder.item_checkTime_edit.getText().toString();
        if (time == null || time.indexOf("年")==-1) {
            Logger.e("aaa","111111111111111111111111111111111111111111111111111111111111");
            holder.item_checkTime_edit.setText(dateTime);
        }

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
    private class SpinnerAdapter extends BaseAdapter {
        private List<IVDesc> mImages = new ArrayList<IVDesc>();

        @Override
        public int getCount() {
            return mImages.size();
        }

        public void setItem(List<IVDesc> list){
            mImages = list;
        }

        @Override
        public IVDesc getItem(int position) {
            return mImages.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            TextView view = new TextView(mActivity);
            IVDesc desc = getItem(position);
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
