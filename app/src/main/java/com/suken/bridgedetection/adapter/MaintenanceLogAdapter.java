package com.suken.bridgedetection.adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.suken.bridgedetection.Constants;
import com.suken.bridgedetection.R;
import com.suken.bridgedetection.activity.MaintenanceLogActivity;
import com.suken.bridgedetection.bean.CatalogueByUIDBean;
import com.suken.bridgedetection.bean.CatalogueByUIDDao;
import com.suken.bridgedetection.bean.IVDesc;
import com.suken.bridgedetection.bean.MaintenanceDiseaseBean;
import com.suken.bridgedetection.bean.MaintenanceLogItemBean;
import com.suken.bridgedetection.bean.MaintenanceTableItemBean;
import com.suken.bridgedetection.util.DateUtil;
import com.suken.bridgedetection.util.Logger;
import com.suken.bridgedetection.util.UiUtil;
import com.suken.bridgedetection.widget.CheckXMDialog;
import com.suken.bridgedetection.widget.DateTimePickDialogUtil;
import com.suken.bridgedetection.widget.TimePickerUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/8.
 */
public class MaintenanceLogAdapter extends BaseAdapter {
    private ArrayList<MaintenanceLogItemBean> maintenanceLogItemBeen = new ArrayList<MaintenanceLogItemBean>();
    private List<CatalogueByUIDBean> catalogueByUIDBeen = new ArrayList<CatalogueByUIDBean>();
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

    public void setCatalogueByUID(List<CatalogueByUIDBean> list) {
        this.catalogueByUIDBeen = list;
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
//        if (view == null) {
            view = inflater.inflate(R.layout.maintenance_log_item, null);
            holder = new HolderView(view);
            view.setTag(holder);
            holder.projectName_edit.setTag(position);
            holder.zh_edit.setTag(position);
            holder.cl_edit.setTag(position);
            holder.unit_edit.setTag(position);
            holder.count_edit.setTag(position);
            holder.address_edit.setTag(position);
            holder.item_checkTime_edit.setTag(position);
//        } else {
//            holder = (HolderView) view.getTag();
//            holder.projectName_edit.setTag(position);
//            holder.zh_edit.setTag(position);
//            holder.cl_edit.setTag(position);
//            holder.unit_edit.setTag(position);
//            holder.count_edit.setTag(position);
//            holder.address_edit.setTag(position);
//            holder.item_checkTime_edit.setTag(position);
//        }
//        holder.projectName_edit.addTextChangedListener(new Watcher(holder.projectName_edit));
        holder.projectName_edit.setKeyListener(null);
        holder.projectName_edit.setOnClickListener(new CheckXMDialog(mActivity,  holder.projectName_edit));
        holder.zh_edit.addTextChangedListener(new Watcher(holder.zh_edit));
        holder.cl_edit.addTextChangedListener(new Watcher(holder.cl_edit));
        holder.unit_edit.addTextChangedListener(new Watcher(holder.unit_edit));
        holder.count_edit.addTextChangedListener(new Watcher(holder.count_edit));
        holder.address_edit.addTextChangedListener(new Watcher(holder.address_edit));
        holder.item_checkTime_edit.addTextChangedListener(new Watcher(holder.item_checkTime_edit));

        holder.video_num.setText(bean.getmVideo().size()+"");
        holder.img_num.setText(bean.getmImages().size()+"");

        holder.projectName_edit.setText(bean.getBhmc());
        holder.zh_edit.setText(bean.getYhzh());
        holder.cl_edit.setText(bean.getYhzh());
        holder.unit_edit.setText(bean.getDw());
        holder.count_edit.setText(bean.getYgsl());
        holder.address_edit.setText(bean.getBhwz());
        holder.item_checkTime_edit.setText(bean.getCreatetime());
        setDateTime(holder);

        String fx = bean.getFx();
        Logger.e("aaa","fx==="+fx);
        if("上行内侧".equals(fx)){
            holder.radioGroup.check(R.id.radioup);
        }else if("下行内侧".equals(fx)){
            holder.radioGroup.check(R.id.radiodown);
        }else if("上行外侧".equals(fx)){
            holder.radioGroup.check(R.id.radioleft);
        }else if("下行外侧".equals(fx)){
            holder.radioGroup.check(R.id.radioright);
        }else{
            holder.radioGroup.check(R.id.radioup);
        }


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
        holder.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.radioup:
                        maintenanceLogItemBeen.get(position).setFx("上行内侧");
                        break;
                    case R.id.radiodown:
                        maintenanceLogItemBeen.get(position).setFx("下行内侧");
                        break;
                    case R.id.radioleft:
                        maintenanceLogItemBeen.get(position).setFx("上行外侧");
                        break;
                    case R.id.radioright:
                        maintenanceLogItemBeen.get(position).setFx("下行外侧");
                        break;

                }
            }
        });

        return view;
    }

    private void initListDialog(final HolderView holder,final int position) {
        final String[] names = new String[catalogueByUIDBeen.size()];
        for (int i = 0; i < catalogueByUIDBeen.size(); i++) {
            CatalogueByUIDBean bean = catalogueByUIDBeen.get(i);

            names[i] = bean.getXimmc() + "(" + bean.getDw() + ")";
        }

        new AlertDialog.Builder(mActivity)
                .setItems(names, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        Logger.e("aaa", "which++" + which);
                        CatalogueByUIDBean bean = catalogueByUIDBeen.get(which);
                        holder.projectName_edit.setText(bean.getXimmc());
                        holder.unit_edit.setText(bean.getDw());

                        maintenanceLogItemBeen.get(position).setOrgid(bean.getOrgid()+"");
                        maintenanceLogItemBeen.get(position).setVersionno(bean.getVersionno()+"");
                        maintenanceLogItemBeen.get(position).setCreateBy(bean.getCreateBy()+"");
                        maintenanceLogItemBeen.get(position).setCreatetime(bean.getCreatetime()+"");
                        maintenanceLogItemBeen.get(position).setCreator(bean.getCreator()+"");
                        maintenanceLogItemBeen.get(position).setUpdateBy(bean.getUpdateBy()+"");
                        maintenanceLogItemBeen.get(position).setUpdatetime(bean.getUpdatetime()+"");
                        maintenanceLogItemBeen.get(position).setUpdator(bean.getUpdator()+"");
                        maintenanceLogItemBeen.get(position).setFlag(bean.getFlag()+"");
                        maintenanceLogItemBeen.get(position).setXcbhid(bean.getId()+"");


                        maintenanceLogItemBeen.get(position).setBhid(bean.getId()+"");
                        maintenanceLogItemBeen.get(position).setBhmc(bean.getXimmc());
                        maintenanceLogItemBeen.get(position).setDw(bean.getDw());
                        maintenanceLogItemBeen.get(position).setRemark(bean.getXcms()+"");
                    }
                })
                .show();
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
                byyj_title,
                img_num,
                video_num;

        private EditText projectName_edit,
                zh_edit,
                cl_edit,
                unit_edit,
                count_edit,
                address_edit,
                item_checkTime_edit;

        private Spinner img_spinner;

        private View item_Line;

        private RadioGroup radioGroup;

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
            img_num = (TextView) view.findViewById(R.id.img_num);

            xiangji = (ImageView) view.findViewById(R.id.xiangji);
            video = (ImageView) view.findViewById(R.id.video);

            projectName_edit = (EditText) view.findViewById(R.id.projectName_edit);
            cl_edit = (EditText) view.findViewById(R.id.cl_edit);
            unit_edit = (EditText) view.findViewById(R.id.unit_edit);
            count_edit = (EditText) view.findViewById(R.id.count_edit);
            address_edit = (EditText) view.findViewById(R.id.address_edit);
            item_checkTime_edit = (EditText) view.findViewById(R.id.item_checkTime_edit);
            zh_edit = (EditText) view.findViewById(R.id.zh_edit);

            img_spinner = (Spinner) view.findViewById(R.id.img_spinner);


            item_Line = view.findViewById(R.id.item_Line);

            radioGroup = (RadioGroup) view.findViewById(R.id.radioGroup);
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
                    maintenanceLogItemBeen.get(position).setBhmc(content!=null&&!"".equals(content)?content:"");
                    break;
                case R.id.zh_edit:
                    Logger.e("aaa","diseaseName_edit="+content+"=====position"+position);
                    maintenanceLogItemBeen.get(position).setYhzh(content!=null&&!"".equals(content)?content:"");
                    break;
                case R.id.cl_edit:
                    Logger.e("aaa","diseaseName_edit="+content+"=====position"+position);
                    maintenanceLogItemBeen.get(position).setYhzh(content!=null&&!"".equals(content)?content:"");
                    break;
                case R.id.unit_edit:
                    Logger.e("aaa","unit_edit==position"+position);
                    maintenanceLogItemBeen.get(position).setDw(content!=null&&!"".equals(content)?content:"");
                    break;
                case R.id.count_edit:
                    Logger.e("aaa","count_edit==position"+position);
                    maintenanceLogItemBeen.get(position).setYgsl(content!=null&&!"".equals(content)?content:"");
                    break;
                case R.id.address_edit:
                    Logger.e("aaa","address_edit==position"+position);
                    maintenanceLogItemBeen.get(position).setBhwz(content!=null&&!"".equals(content)?content:"");
                    break;
                case R.id.item_checkTime_edit:
                    Logger.e("aaa","item_checkTime_edit==position"+position);
                    maintenanceLogItemBeen.get(position).setCreatetime(content!=null&&!"".equals(content)?content:"");
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
                    IVDesc desc = (IVDesc) v.getTag();
                    mActivity.jumpToMedia(ClickImagePositon, Constants.REQUEST_CODE_EDIT_IMG, desc);
                }
            });
            return view;
        }


    }
}
