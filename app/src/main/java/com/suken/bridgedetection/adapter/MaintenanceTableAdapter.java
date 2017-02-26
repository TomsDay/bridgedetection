package com.suken.bridgedetection.adapter;

import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.suken.bridgedetection.Constants;
import com.suken.bridgedetection.R;
import com.suken.bridgedetection.activity.BaseActivity;
import com.suken.bridgedetection.activity.MaintenanceTableActivity;
import com.suken.bridgedetection.bean.IVDesc;
import com.suken.bridgedetection.bean.MaintenanceDiseaseBean;
import com.suken.bridgedetection.bean.MaintenanceTableItemBean;
import com.suken.bridgedetection.location.LocationManager;
import com.suken.bridgedetection.location.LocationResult;
import com.suken.bridgedetection.location.OnLocationFinishedListener;
import com.suken.bridgedetection.util.DateUtil;
import com.suken.bridgedetection.util.Logger;
import com.suken.bridgedetection.util.TextUtil;
import com.suken.bridgedetection.util.UiUtil;
import com.suken.bridgedetection.widget.CheckDiseaseDialog;
import com.suken.bridgedetection.widget.DateTimePickDialogUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Administrator on 2016/6/5.
 */
public class MaintenanceTableAdapter extends BaseAdapter {
    private ArrayList<MaintenanceTableItemBean> list = new ArrayList<MaintenanceTableItemBean>();
    private List<MaintenanceDiseaseBean> maintenanceDiseaseBeanList = new ArrayList<MaintenanceDiseaseBean>();

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

    public void setDiseaseData(List<MaintenanceDiseaseBean> list) {
        this.maintenanceDiseaseBeanList = list;
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
//        if (view == null) {
        view = inflater.inflate(R.layout.maintenance_table_item, null);
        holder = new HolderView(view);
        view.setTag(holder);
//            holder.diseaseName_edit.setTag(position);
//            holder.unit_edit.setTag(position);
        holder.count_edit.setTag(position);
        holder.address_edit.setTag(position);
        holder.item_checkTime_edit.setTag(position);
        holder.zh_edit.setTag(position);
        holder.remark_edit.setTag(position);
        holder.zhfw_edit.setTag(position);

//        } else {
//            holder = (HolderView) view.getTag();
////            holder.diseaseName_edit.setTag(position);
////            holder.unit_edit.setTag(position);
//            holder.count_edit.setTag(position);
//            holder.address_edit.setTag(position);
//            holder.item_checkTime_edit.setTag(position);
//            holder.zh_edit.setTag(position);
//        }

//        holder.diseaseName_edit.addTextChangedListener(new Watcher(holder.diseaseName_edit));
//        holder.unit_edit.addTextChangedListener(new Watcher(holder.unit_edit));
        holder.count_edit.addTextChangedListener(new Watcher(holder.count_edit));
        holder.address_edit.addTextChangedListener(new Watcher(holder.address_edit));
        holder.item_checkTime_edit.addTextChangedListener(new Watcher(holder.item_checkTime_edit));
        holder.zh_edit.addTextChangedListener(new Watcher(holder.zh_edit));
        holder.remark_edit.addTextChangedListener(new Watcher(holder.remark_edit));
        holder.zhfw_edit.addTextChangedListener(new Watcher(holder.zhfw_edit));



        if (bean.isShow()) {
            holder.form_item_edit_layout.setVisibility(View.VISIBLE);
            holder.arrow_img.setImageResource(R.drawable.xia);

        } else {
            holder.form_item_edit_layout.setVisibility(View.GONE);
            holder.arrow_img.setImageResource(R.drawable.shang);
        }
//        setDateTime(holder);

//        private EditText diseaseName_edit,
//                unit_edit,
//                count_edit,
//                address_edit,
//                item_checkTime_edit;
        holder.video_num.setText(bean.getmVideo().size() + "");
        holder.img_num.setText(bean.getmImages().size() + "");
        holder.diseaseName_edit.setText(bean.getBhmc());
        holder.unit_edit.setText(bean.getDw());
        holder.count_edit.setText(bean.getYgsl());
        holder.address_edit.setText(bean.getBhwz());
        holder.item_checkTime_edit.setText(bean.getJcsj());
        holder.zh_edit.setText(bean.getYhzh());
        holder.remark_edit.setText(bean.getRemark());
        holder.zhfw_edit.setText(bean.getZhfw());
        setDateTime(holder,position);

        String fx = bean.getFx();
        Logger.e("aaa", "fx===" + fx);
        if (!TextUtil.isEmptyString(fx) && fx.contains("匝道")) {
            holder.checkBox.setChecked(true);
            fx = fx.replace("匝道", "");
        }
        if ("上行".equals(fx)) {
            holder.radioGroup.check(R.id.radioup);
        } else if ("下行".equals(fx)) {
            holder.radioGroup.check(R.id.radiodown);
        } else if ("双向".equals(fx)) {
            holder.radioGroup.check(R.id.radioupdown);
        }else{
            holder.radioGroup.check(R.id.radioup);
        }
        String jcqk = bean.getCus1();
        if (!TextUtil.isEmptyString(jcqk) && "未发现异常情况".equals(jcqk)) {
            holder.checkBox_jcqi.setChecked(true);
        }
        String isxd = bean.getIsxd();
        if (!TextUtil.isEmptyString(isxd) && "1".equals(isxd)) {
            holder.checkBox_isxd.setChecked(false);
        }else{
            holder.checkBox_isxd.setChecked(true);
        }
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
                mActivity.jumpToMedia(position, Constants.REQUEST_CODE_CAPTURE, null);
                LocationManager.getInstance().syncLocation(new OnLocationFinishedListener() {
                    @Override
                    public void onLocationFinished(LocationResult result) {
                        if (mActivity == null || ((BaseActivity) mActivity).isDestroyed() || mActivity.isFinishing()) {
                            return;
                        }
                        boolean mIsGpsSuccess = false;
                        if (result.isSuccess) {
//                            mIsGpsSuccess = true;
                            list.get(position).setTpjd(result.longitude + "");
                            list.get(position).setTpwd(result.latitude + "");
//                            mjingdu.setText("经度:" + result.latitude);

//                            mWeidu.setText("纬度:" + result.longitude);
                            Logger.e("aaa", "经度:" + result.longitude);
                            Logger.e("aaa", "纬度:" + result.latitude);
//                            TextView tv = (TextView) getActivity().findViewById(R.id.syncLocationTv);
                            Toast.makeText(mActivity, "定位成功", Toast.LENGTH_SHORT).show();
//                            tv.setTextColor(Color.WHITE);
                        } else if (!mIsGpsSuccess) {
                            Toast.makeText(mActivity, "定位失败", Toast.LENGTH_SHORT).show();
//                            TextView tv = (TextView) getActivity().findViewById(R.id.syncLocationTv);
//                            tv.setText("定位失败");
//                            tv.setTextColor(Color.RED);
                        }

                    }
                });
            }
        });
        holder.video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mActivity.jumpToMedia(position, Constants.REQUEST_CODE_VIDEO, null);
//                mImageOrVideoClick.clickVideo(position);
                LocationManager.getInstance().syncLocation(new OnLocationFinishedListener() {
                    @Override
                    public void onLocationFinished(LocationResult result) {
                        if (mActivity == null || ((BaseActivity) mActivity).isDestroyed() || mActivity.isFinishing()) {
                            return;
                        }
                        boolean mIsGpsSuccess = false;
                        if (result.isSuccess) {
//                            mIsGpsSuccess = true;
                            list.get(position).setTpjd(result.longitude + "");
                            list.get(position).setTpwd(result.latitude + "");
//                            mjingdu.setText("经度:" + result.latitude);

//                            mWeidu.setText("纬度:" + result.longitude);
                            Logger.e("aaa", "经度:" + result.longitude);
                            Logger.e("aaa", "纬度:" + result.latitude);
//                            TextView tv = (TextView) getActivity().findViewById(R.id.syncLocationTv);
                            Toast.makeText(mActivity, "定位成功", Toast.LENGTH_SHORT).show();
//                            tv.setTextColor(Color.WHITE);
                        } else if (!mIsGpsSuccess) {
                            Toast.makeText(mActivity, "定位失败", Toast.LENGTH_SHORT).show();
//                            TextView tv = (TextView) getActivity().findViewById(R.id.syncLocationTv);
//                            tv.setText("定位失败");
//                            tv.setTextColor(Color.RED);
                        }

                    }
                });
            }
        });


        holder.diseaseName_edit.setKeyListener(null);
        holder.unit_edit.setKeyListener(null);
        setBhmc(holder, position);
//        holder.diseaseName_edit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                initListDialog(finalHolder,position);
//            }
//        });
//        holder.unit_edit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                initListDialog(finalHolder,position);
//            }
//        });


        holder.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
//                String fx = list.get(position).getFx();
//                fx = TextUtil.isEmptyString(fx) ? "上行" : fx;
                switch (i) {
                    case R.id.radioup:
                        list.get(position).setFx("上行");
//                        if (!fx.contains("上行")) {
//                            list.get(position).setFx(fx.replace("下行","上行"));
//                        }

                        break;
                    case R.id.radiodown:
                        list.get(position).setFx("下行");
//                        if (!fx.contains("下行")) {
//                            list.get(position).setFx(fx.replace("上行","下行"));
//                        }

                        break;
                    case R.id.radioupdown:
                        list.get(position).setFx("双向");
//                        if (!fx.contains("下行")) {
//                            list.get(position).setFx(fx.replace("上行","下行"));
//                        }

                        break;

                }
            }
        });
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                String fx = list.get(position).getFx();
                fx = TextUtil.isEmptyString(fx) ? "上行" : fx;
                if(b){
                    if (!fx.contains("匝道")) {
                        list.get(position).setFx(fx + "匝道");
                    }
                }else{
                    if (fx.contains("匝道")) {
                        list.get(position).setFx(fx.replace("匝道",""));
                    }
                }
            }
        });
        holder.checkBox_jcqi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    list.get(position).setCus1("未发现异常情况");
                    list.get(position).setYhzt("2");
                }else{
                    list.get(position).setCus1("");
                    list.get(position).setYhzt("1");
                }

            }
        });
        holder.checkBox_isxd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                list.get(position).setIsxd(b?"2":"1");
                Logger.e("aaa", "isxd==="+list.get(position).getIsxd());
            }
        });

        return view;
    }

    //    private void initListDialog(final HolderView holder,final int position) {
//        final String[] names = new String[maintenanceDiseaseBeanList.size()];
//        for (int i = 0; i < maintenanceDiseaseBeanList.size(); i++) {
//            MaintenanceDiseaseBean bean = maintenanceDiseaseBeanList.get(i);
//
//            names[i] = bean.getBhmc() + "(" + bean.getDw() + ")";
//        }
//
//        new AlertDialog.Builder(mActivity)
//                .setItems(names, new DialogInterface.OnClickListener() {
//
//                    @Override
//                    public void onClick(DialogInterface dialog,
//                                        int which) {
//                        Logger.e("aaa", "which++" + which);
//                        MaintenanceDiseaseBean bean = maintenanceDiseaseBeanList.get(which);
//                        holder.diseaseName_edit.setText(bean.getBhmc());
//                        holder.unit_edit.setText(bean.getDw());
//
//                        list.get(position).setBhid(bean.getId()+"");
//                        list.get(position).setBhmc(bean.getBhmc());
//                        list.get(position).setDw(bean.getDw());
//
//                        list.get(position).setRemark(bean.getXcms()+"");
//                    }
//                })
//                .show();
//    }
    public void setBhmc(final HolderView holder, final int position) {
        holder.diseaseName_edit.setOnClickListener(new CheckDiseaseDialog(mActivity, new CheckDiseaseDialog.CheckDiseaseDialogReturn() {
            @Override
            public void returnBean(List<MaintenanceDiseaseBean> maintenanceDiseaseList) {
                if (maintenanceDiseaseList.size() != 0) {
                    Logger.e("aaa", "maintenanceDiseaseList===" + maintenanceDiseaseList.toString());
                    MaintenanceDiseaseBean bean = maintenanceDiseaseList.get(0);
                    holder.diseaseName_edit.setText(bean.getBhmc());
                    holder.unit_edit.setText(bean.getDw());

                    list.get(position).setBhid(bean.getId() + "");
                    list.get(position).setBhmc(bean.getBhmc());
                    list.get(position).setDw(bean.getDw());
                    list.get(position).setRemark(bean.getXcms() + "");
                }
            }
        }));
    }

    class Watcher implements TextWatcher {
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
            int position = (Integer) editTextID.getTag();
            Logger.e("aaa", "content=111=" + content);
            switch (editTextID.getId()) {

//                case R.id.diseaseName_edit:
//                    list.get(position).setDiseaseName(content!=null&&!"".equals(content)?content:"");
//                    list.get(position).setUnit(content!=null&&!"".equals(content)?content:"");
//                    break;
//                case R.id.unit_edit:
//                    list.get(position).setUnit(content!=null&&!"".equals(content)?content:"");
//                    break;
                case R.id.count_edit:
                    list.get(position).setYgsl(!TextUtil.isEmptyString(content)? content : "");
                    break;
                case R.id.address_edit:
                    list.get(position).setBhwz(!TextUtil.isEmptyString(content)? content : "");
                    break;
                case R.id.item_checkTime_edit:
                    Logger.e("aaa", "content=222222=" + content);
                    list.get(position).setJcsj(!TextUtil.isEmptyString(content)? content : "");
                    break;
                case R.id.zh_edit:
                    list.get(position).setYhzh(!TextUtil.isEmptyString(content)? content : "");
                    break;
                case R.id.remark_edit:
                    list.get(position).setRemark(!TextUtil.isEmptyString(content)? content : "");
                    break;
                case R.id.zhfw_edit:
                    Logger.e("aaa","diseaseName_edit="+content+"=====position"+position);
                    list.get(position).setZhfw(!TextUtil.isEmptyString(content)?content:"");
                    break;
            }
        }
    }

    public void setDateTime(final HolderView holder,int position) {
        Calendar c1 =  Calendar.getInstance();
        dateTime = addZeroTime(c1.get(Calendar.HOUR_OF_DAY)) + ":" + addZeroTime(c1.get(Calendar.MINUTE));
        final String time = holder.item_checkTime_edit.getText().toString();
//        if (time == null || time.indexOf("年") == -1)
        if(TextUtil.isEmptyString(time)&&TextUtil.isEmptyString(list.get(position).getTjsj())){
            Logger.e("aaa", "111111111111111111111111111111111111111111111111111111111111");
            holder.item_checkTime_edit.setText(dateTime);
        }
        holder.item_checkTime_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(
//                        mActivity, null, new DateTimePickDialogUtil.ReturnTime() {
//                    @Override
//                    public void getTime(String time) {
//                        dateTime = time;
//                        holder.item_checkTime_edit.setText(dateTime);
//                    }
//                });
                Calendar c =  Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);
                TimePickerDialog dialog = new TimePickerDialog(mActivity, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                        dateTime = (addZeroTime(hour)) + ":" + (addZeroTime(minute));
                        holder.item_checkTime_edit.setText(dateTime);
                    }
                }, hour, minute, true);
                dialog.show();

            }
        } );
//        holder.item_checkTime_edit.setKeyListener(null);
//        holder.item_checkTime_edit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(
//                        mActivity, dateTime, new DateTimePickDialogUtil.ReturnTime() {
//                    @Override
//                    public void getTime(String time) {
//                        dateTime = time;
//                        holder.item_checkTime_edit.setText(dateTime);
//                    }
//                });
//            }
//        });


    }
    public String addZeroTime(int num){
        return (num < 10 ? "0" + num : num+"");
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

        private EditText diseaseName_edit,
                zhfw_edit,
                unit_edit,
                count_edit,
                address_edit,
                item_checkTime_edit,
                zh_edit,
                remark_edit;

        private Button item_checkTime_btn;

        private Spinner img_spinner;

        private View item_Line;

        private RadioGroup radioGroup;

        private CheckBox checkBox,
                checkBox_jcqi,checkBox_isxd;

        public HolderView(View view) {
            form_item_edit_layout = (LinearLayout) view.findViewById(R.id.form_item_edit_layout);
            from_topLayout = (LinearLayout) view.findViewById(R.id.from_topLayout);
            img_video_layout = (LinearLayout) view.findViewById(R.id.img_video_layout);

            arrow_img = (ImageView) view.findViewById(R.id.arrow_img);

            form_column = (TextView) view.findViewById(R.id.form_column);
            qslx_title = (TextView) view.findViewById(R.id.qslx_title);
            qsfw_title = (TextView) view.findViewById(R.id.qsfw_title);
            byyj_title = (TextView) view.findViewById(R.id.byyj_title);
            img_num = (TextView) view.findViewById(R.id.img_num);
            video_num = (TextView) view.findViewById(R.id.video_num);

            xiangji = (ImageView) view.findViewById(R.id.xiangji);
            video = (ImageView) view.findViewById(R.id.video);

            diseaseName_edit = (EditText) view.findViewById(R.id.diseaseName_edit);
            unit_edit = (EditText) view.findViewById(R.id.unit_edit);
            count_edit = (EditText) view.findViewById(R.id.count_edit);
            address_edit = (EditText) view.findViewById(R.id.address_edit);
            item_checkTime_edit = (EditText) view.findViewById(R.id.item_checkTime_edit);
            zh_edit = (EditText) view.findViewById(R.id.zh_edit);
            zhfw_edit = (EditText) view.findViewById(R.id.zhfw_edit);
            remark_edit = (EditText) view.findViewById(R.id.remark_edit);

            img_spinner = (Spinner) view.findViewById(R.id.img_spinner);

            item_Line = view.findViewById(R.id.item_Line);

            radioGroup = (RadioGroup) view.findViewById(R.id.radioGroup);

            item_checkTime_btn = (Button) view.findViewById(R.id.item_checkTime_btn);

            checkBox = (CheckBox) view.findViewById(R.id.checkBox);

            checkBox_jcqi = (CheckBox) view.findViewById(R.id.checkBox_jcqi);

            checkBox_isxd = (CheckBox) view.findViewById(R.id.checkBox_isxd);
        }
    }

//    ImageOrVideoClick mImageOrVideoClick;

//    public void setImageOrVideoClick(ImageOrVideoClick imageOrVideoClick) {
//        mImageOrVideoClick = imageOrVideoClick;
//    }
//
//    public interface ImageOrVideoClick {
//        public void clickImage(int position);
//
//        public void clickVideo(int position);
//    }

    private class SpinnerAdapter extends BaseAdapter {
        private List<IVDesc> mImages = new ArrayList<IVDesc>();

        @Override
        public int getCount() {
            return mImages.size();
        }

        public void setItem(List<IVDesc> list) {
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
