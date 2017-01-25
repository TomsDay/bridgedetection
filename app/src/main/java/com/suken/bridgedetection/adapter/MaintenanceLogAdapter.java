package com.suken.bridgedetection.adapter;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.suken.bridgedetection.Constants;
import com.suken.bridgedetection.R;
import com.suken.bridgedetection.activity.BaseActivity;
import com.suken.bridgedetection.activity.MaintenanceLogActivity;
import com.suken.bridgedetection.bean.CatalogueByUIDBean;
import com.suken.bridgedetection.bean.CatalogueByUIDDao;
import com.suken.bridgedetection.bean.GeteMaterialBean;
import com.suken.bridgedetection.bean.IVDesc;
import com.suken.bridgedetection.bean.MaintenanceDiseaseBean;
import com.suken.bridgedetection.bean.MaintenanceLogItemBean;
import com.suken.bridgedetection.bean.MaintenanceTableItemBean;
import com.suken.bridgedetection.location.LocationManager;
import com.suken.bridgedetection.location.LocationResult;
import com.suken.bridgedetection.location.OnLocationFinishedListener;
import com.suken.bridgedetection.util.DateUtil;
import com.suken.bridgedetection.util.Logger;
import com.suken.bridgedetection.util.TextUtil;
import com.suken.bridgedetection.util.UiUtil;
import com.suken.bridgedetection.widget.CheckClDialog;
import com.suken.bridgedetection.widget.CheckXMDialog;
import com.suken.bridgedetection.widget.DateTimePickDialogUtil;
import com.suken.bridgedetection.widget.ListViewForScrollView;
import com.suken.bridgedetection.widget.TimePickerUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

        holder.unit_edit.setTag(position);
//        holder.cl_edit.setTag(position);
//        holder.gg_edit.setTag(position);
//        holder.xh_edit.setTag(position);
//        holder.clsl_edit.setTag(position);
//        holder.cldw_edit.setTag(position);
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


        holder.zh_edit.addTextChangedListener(new Watcher(holder.zh_edit));


        holder.unit_edit.addTextChangedListener(new Watcher(holder.unit_edit));
//        holder.cl_edit.setKeyListener(null);
//        holder.cl_edit.addTextChangedListener(new Watcher(holder.cl_edit));
//        holder.gg_edit.addTextChangedListener(new Watcher(holder.gg_edit));
//        holder.xh_edit.addTextChangedListener(new Watcher(holder.xh_edit));
//        holder.clsl_edit.addTextChangedListener(new Watcher(holder.clsl_edit));
//        holder.cldw_edit.addTextChangedListener(new Watcher(holder.cldw_edit));
        holder.count_edit.addTextChangedListener(new Watcher(holder.count_edit));
        holder.address_edit.addTextChangedListener(new Watcher(holder.address_edit));
        holder.item_checkTime_edit.addTextChangedListener(new Watcher(holder.item_checkTime_edit));

        holder.video_num.setText(bean.getmVideo().size()+"");
        holder.img_num.setText(bean.getmImages().size()+"");

        setXimmc(holder, position);


        holder.projectName_edit.setText(bean.getBhmc());
        holder.zh_edit.setText(bean.getYhzh());


        holder.unit_edit.setText(bean.getDw());
        holder.count_edit.setText(bean.getYgsl());
        holder.address_edit.setText(bean.getBhwz());
        holder.item_checkTime_edit.setText(bean.getCreatetime());

        CLAdapter mCLAdapter = new CLAdapter(mActivity);
        mCLAdapter.setData(bean.getGeteMaterialBeens());
        mCLAdapter.setClslListener(new CLAdapter.ClslListener() {
            @Override
            public void getClsl(String clsl, int mPosition) {
                maintenanceLogItemBeen.get(position).getGeteMaterialBeens().get(mPosition).setClsl(clsl);
                Log.e("aaa", ",clsl==" + maintenanceLogItemBeen.get(position).getClsl());
                String getclsl = maintenanceLogItemBeen.get(position).getClsl();
                if(getclsl.contains(",~")){
                    String[] clslArray = getclsl.split(",~");
                    int length = clslArray.length;
                    if (length >= (mPosition + 1)) {
                        clslArray[mPosition] = clsl;
                        StringBuffer sb = new StringBuffer();
                        for (int i = 0; i < length; i++) {
                            Log.e("aaa", "i==" + i + ",clsl==" + clslArray[i]);
                            sb.append(clslArray[i]);
                            if (i != (length - 1)) {
                                sb.append(",~");
                            }
                        }
                        maintenanceLogItemBeen.get(position).setClsl(sb.toString());

                    }
                }else{
                    maintenanceLogItemBeen.get(position).setClsl(clsl);
                }

            }
        });
        holder.cl_listview.setAdapter(mCLAdapter);
        setcCl(holder, position,mCLAdapter);
//        holder.cl_edit.setText(bean.getClmc());
//        holder.gg_edit.setText(bean.getClgg());
//        holder.xh_edit.setText(bean.getClxh());
//        holder.clsl_edit.setText(bean.getClsl());
//        holder.cldw_edit.setText(bean.getCldw());


        setDateTime(holder);

        String fx = bean.getFx();
        if(!TextUtil.isEmptyString(fx)){
            fx = fx.replace("内侧", "");
            if (fx.contains("匝道")) {
                holder.checkBox.setChecked(true);
                fx = fx.replace("匝道", "");
            }
        }

        Logger.e("aaa","fx==="+fx);
        if("上行".equals(fx)){
            holder.radioGroup.check(R.id.radioup);
        }else if("下行".equals(fx)){
            holder.radioGroup.check(R.id.radiodown);
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
                            maintenanceLogItemBeen.get(position).setTpjd(result.longitude + "");
                            maintenanceLogItemBeen.get(position).setTpwd(result.latitude + "");
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
                        if(mActivity == null || ((BaseActivity)mActivity).isDestroyed() || mActivity.isFinishing()){
                            return;
                        }
                        boolean mIsGpsSuccess = false;
                        if (result.isSuccess) {
//                            mIsGpsSuccess = true;
                            maintenanceLogItemBeen.get(position).setTpjd(result.longitude + "");
                            maintenanceLogItemBeen.get(position).setTpwd(result.latitude + "");
//                            mjingdu.setText("经度:" + result.latitude);

//                            mWeidu.setText("纬度:" + result.longitude);
                            Logger.e("aaa","经度:" + result.longitude);
                            Logger.e("aaa","纬度:" + result.latitude);
//                            TextView tv = (TextView) getActivity().findViewById(R.id.syncLocationTv);
                            Toast.makeText(mActivity, "定位成功", Toast.LENGTH_SHORT).show();
//                            tv.setTextColor(Color.WHITE);
                        } else if(!mIsGpsSuccess){
                            Toast.makeText(mActivity, "定位失败", Toast.LENGTH_SHORT).show();
//                            TextView tv = (TextView) getActivity().findViewById(R.id.syncLocationTv);
//                            tv.setText("定位失败");
//                            tv.setTextColor(Color.RED);
                        }

                    }
                });
            }
        });
        holder.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                String fx = maintenanceLogItemBeen.get(position).getFx();
                fx = TextUtil.isEmptyString(fx) ? "上行" : fx;
                switch (i) {
                    case R.id.radioup:

                        if (!fx.contains("上行")) {
                            maintenanceLogItemBeen.get(position).setFx(fx.replace("下行","上行"));
                        }

                        break;
                    case R.id.radiodown:
                        maintenanceLogItemBeen.get(position).setFx("下行");
                        if (!fx.contains("下行")) {
                            maintenanceLogItemBeen.get(position).setFx(fx.replace("上行","下行"));
                        }

                        break;


                }
            }
        });

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                String fx = maintenanceLogItemBeen.get(position).getFx();
                fx = TextUtil.isEmptyString(fx) ? "上行" : fx;
                if(b){
                    if (!fx.contains("匝道")) {
                        maintenanceLogItemBeen.get(position).setFx(fx + "匝道");
                    }
                }else{
                    if (fx.contains("匝道")) {
                        maintenanceLogItemBeen.get(position).setFx(fx.replace("匝道",""));
                    }
                }
            }
        });


        return view;
    }


    public void setcCl(final HolderView holder, final int position, final CLAdapter clAdapter){

        holder.cl_btn.setOnClickListener(new CheckClDialog(mActivity, new CheckClDialog.CheckClDialogReturn(){
            @Override
            public void returnBean(List<GeteMaterialBean> geteMaterialBeen) {
                if (geteMaterialBeen.size() != 0) {
                    Logger.e("aaa","catalogueByUIDBeen==="+geteMaterialBeen.toString());
                    GeteMaterialBean bean = geteMaterialBeen.get(0);
                    Log.i("aaa", "returnBean: "+bean.getClmc());
                    MaintenanceLogItemBean clBean = maintenanceLogItemBeen.get(position);
                    bean.setClsl("0");
                    if(TextUtil.isListEmpty(clBean.getGeteMaterialBeens())){
                        clBean.setClid(bean.getId());
                        clBean.setClmc(bean.getClmc());
                        clBean.setClgg(bean.getGg());
                        clBean.setClxh(bean.getXh());
                        clBean.setClsl("0");
                        clBean.setCldw(bean.getDw());

                    }else{
                        String clmc = clBean.getClmc();
                        String gg = clBean.getClgg();
                        String xh = clBean.getClxh();
                        String cldw = clBean.getCldw();
                        String clsl = clBean.getClsl();
                        String clid = clBean.getClid();
                        clBean.setClid(clid + ",~" + (TextUtil.isEmptyString(bean.getId())?" ":bean.getId()));
                        clBean.setClmc(clmc + ",~" + (TextUtil.isEmptyString(bean.getClmc())?" ":bean.getClmc()));
                        clBean.setClgg(gg + ",~" + (TextUtil.isEmptyString(bean.getGg())?" ":bean.getGg()));
                        clBean.setClxh(xh + ",~" + (TextUtil.isEmptyString(bean.getXh())?" ":bean.getXh()));
                        clBean.setClsl(clsl + ",~" + "0");
                        clBean.setCldw(cldw + ",~" +(TextUtil.isEmptyString(bean.getDw())?" ":bean.getDw()));
                    }
                    clBean.getGeteMaterialBeens().add(bean);
//                    clAdapter.setData(clBean.getGeteMaterialBeens());
                    clAdapter.notifyDataSetChanged();
//                    if(TextUtil.isEmptyString(clmc)){
//                        maintenanceLogItemBeen.get(position).setClmc(bean.getClmc());
//                    }else{
//                        maintenanceLogItemBeen.get(position).setClmc(clmc + "," + bean.getClmc());
//                    }
//                    if(TextUtil.isEmptyString(gg)){
//                        holder.gg_edit.setText(bean.getGg());
//                    }else{
//                        holder.gg_edit.setText(gg + "," + bean.getGg() );
//                    }
//                    if(TextUtil.isEmptyString(xh)){
//                        holder.xh_edit.setText(bean.getXh());
//                    }else{
//                        holder.xh_edit.setText(xh + "," +  bean.getXh());
//                    }
//                    if(TextUtil.isEmptyString(cldw)){
//                        holder.cldw_edit.setText(bean.getDw());
//                    }else{
//                        holder.cldw_edit.setText(cldw + "," + bean.getDw());
//                    }
//                    if(TextUtil.isEmptyString(clsl)){
//                        holder.clsl_edit.setText("0");
//                    }else{
//                        holder.clsl_edit.setText(clsl + ",0"  );
//                    }
//                    holder.cl_edit.setText(bean.getClmc());
//                    holder.ggxh_edit.setText(bean.getGg()+"-"+bean.getXh());
//                    holder.cldw_edit.setText(bean.getDw());

//                    holder.unit_edit.setText(bean.getDw());

//                    maintenanceLogItemBeen.get(position).setClmc(holder.cl_edit.getText().toString());
//                    maintenanceLogItemBeen.get(position).setVersionno(bean.getVersionno()+"");
//                    maintenanceLogItemBeen.get(position).setCreateBy(bean.getCreateBy()+"");
//                    maintenanceLogItemBeen.get(position).setCreatetime(bean.getCreatetime()+"");
//                    maintenanceLogItemBeen.get(position).setCreator(bean.getCreator()+"");
//                    maintenanceLogItemBeen.get(position).setUpdateBy(bean.getUpdateBy()+"");
//                    maintenanceLogItemBeen.get(position).setUpdatetime(bean.getUpdatetime()+"");
//                    maintenanceLogItemBeen.get(position).setUpdator(bean.getUpdator()+"");
//                    maintenanceLogItemBeen.get(position).setFlag(bean.getFlag()+"");
//                    maintenanceLogItemBeen.get(position).setXcbhid(bean.getId()+"");
//
//
//                    maintenanceLogItemBeen.get(position).setBhid(bean.getId()+"");
//                    maintenanceLogItemBeen.get(position).setBhmc(bean.getXimmc());
//                    maintenanceLogItemBeen.get(position).setDw(bean.getDw());
//                    maintenanceLogItemBeen.get(position).setRemark(bean.getXcms()+"");
                }
            }
        }));
    }

    public void setXimmc(final HolderView holder,final int position){
        holder.projectName_edit.setOnClickListener(new CheckXMDialog(mActivity, new CheckXMDialog.CheckXMDDialogReturn() {
            @Override
            public void returnBean(List<CatalogueByUIDBean> catalogueByUIDBeen) {
                if (catalogueByUIDBeen.size() != 0) {
                    Logger.e("aaa","catalogueByUIDBeen==="+catalogueByUIDBeen.toString());
                    CatalogueByUIDBean bean = catalogueByUIDBeen.get(0);
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
            }
        }));
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
                unit_edit,
                count_edit,
                address_edit,
                item_checkTime_edit;
        //        cl_edit,
//                gg_edit,
//                xh_edit,
//                clsl_edit,
//                cldw_edit;
        private Button cl_btn;

        private ListViewForScrollView cl_listview;

        private Spinner img_spinner;

        private View item_Line;

        private RadioGroup radioGroup;

        private CheckBox checkBox;

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
//            cl_edit = (EditText) view.findViewById(R.id.cl_edit);
            unit_edit = (EditText) view.findViewById(R.id.unit_edit);
            count_edit = (EditText) view.findViewById(R.id.count_edit);
            address_edit = (EditText) view.findViewById(R.id.address_edit);
            item_checkTime_edit = (EditText) view.findViewById(R.id.item_checkTime_edit);
            zh_edit = (EditText) view.findViewById(R.id.zh_edit);
//            gg_edit = (EditText) view.findViewById(R.id.gg_edit);
//            xh_edit = (EditText) view.findViewById(R.id.xh_edit);
//            clsl_edit = (EditText) view.findViewById(R.id.clsl_edit);
//            cldw_edit = (EditText) view.findViewById(R.id.cldw_edit);

            cl_btn = (Button) view.findViewById(R.id.cl_btn);

            img_spinner = (Spinner) view.findViewById(R.id.img_spinner);

            cl_listview = (ListViewForScrollView) view.findViewById(R.id.cl_listview);

            item_Line = view.findViewById(R.id.item_Line);

            radioGroup = (RadioGroup) view.findViewById(R.id.radioGroup);

            checkBox = (CheckBox) view.findViewById(R.id.checkBox);

        }
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
            MaintenanceLogItemBean itembean = maintenanceLogItemBeen.get(position);
            switch (editTextID.getId()) {

                case R.id.projectName_edit:
                    Logger.e("aaa","diseaseName_edit="+content+"=====position"+position);
                    maintenanceLogItemBeen.get(position).setBhmc(content!=null&&!"".equals(content)?content:"");
                    break;
                case R.id.zh_edit:
                    Logger.e("aaa","diseaseName_edit="+content+"=====position"+position);
                    maintenanceLogItemBeen.get(position).setYhzh(content!=null&&!"".equals(content)?content:"");
                    break;
                case R.id.unit_edit:
                    Logger.e("aaa","unit_edit==position"+position);
                    maintenanceLogItemBeen.get(position).setDw(content!=null&&!"".equals(content)?content:"");
                    break;
                case R.id.count_edit:
                    Logger.e("aaa","count_edit==position"+position);
                    Logger.e("aaa","content=="+content);
                    itembean.setWxsl(content);
                    itembean.setYgsl(content);
                    break;
                case R.id.address_edit:
                    Logger.e("aaa","address_edit==position"+position);
                    maintenanceLogItemBeen.get(position).setBhwz(content!=null&&!"".equals(content)?content:"");
                    break;
                case R.id.item_checkTime_edit:
                    Logger.e("aaa","item_checkTime_edit==position"+position);
                    maintenanceLogItemBeen.get(position).setCreatetime(content!=null&&!"".equals(content)?content:"");
                    break;
//                case R.id.cl_edit:
//                    Logger.e("aaa","diseaseName_edit="+content+"=====position"+position);
//                    maintenanceLogItemBeen.get(position).setClmc(content!=null&&!"".equals(content)?content:"");
//                    break;
//                case R.id.gg_edit://规格型号
//                    Logger.e("aaa","item_checkTime_edit==position"+position);
//                    maintenanceLogItemBeen.get(position).setClgg(content!=null&&!"".equals(content)?content:"");
//                    break;
//                case R.id.xh_edit://规格型号
//                    Logger.e("aaa","item_checkTime_edit==position"+position);
//                    maintenanceLogItemBeen.get(position).setClxh(content!=null&&!"".equals(content)?content:"");
//                    break;
//                case R.id.clsl_edit://材料数量
//                    Logger.e("aaa","item_checkTime_edit==position"+position);
//                    maintenanceLogItemBeen.get(position).setClsl(content!=null&&!"".equals(content)?content:"");
//                    break;
//                case R.id.cldw_edit://材料单位
//                    Logger.e("aaa","item_checkTime_edit==position"+position);
//                    maintenanceLogItemBeen.get(position).setCldw(content!=null&&!"".equals(content)?content:"");
//                    break;
            }
        }
    }
    public void setDateTime(final HolderView holder){
        dateTime = DateUtil.getDateEndDay();
        String time = holder.item_checkTime_edit.getText().toString();
        if (time == null || time.indexOf("年")==-1) {
            Logger.e("aaa","111111111111111111111111111111111111111111111111111111111111");
            holder.item_checkTime_edit.setText(dateTime);
        }

        holder.item_checkTime_edit.setKeyListener(null);
        holder.item_checkTime_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(
//                        mActivity, dateTime, new DateTimePickDialogUtil.ReturnTime() {
//                    @Override
//                    public void getTime(String time) {
//                        dateTime = time;
//                        holder.item_checkTime_edit.setText(dateTime);
//                    }
//                });
                String str = holder.item_checkTime_edit.getText().toString();
                int y = 0, m = 0, d = 0;
                Calendar c = DateUtil.strToCalendarLong(str);
                y =  c.get(Calendar.YEAR);
                m = c.get(Calendar.MONTH);
                d = c.get(Calendar.DATE);
                DatePickerDialog dlg = new DatePickerDialog(mActivity, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        int mYear = year;
                        int mMonth = month + 1;
                        int mDay = day;
                        holder.item_checkTime_edit.setText(getTime(mYear, mMonth, mDay));
                    }
                }, y, m , d);
                dlg.setTitle("日期：");
                dlg.show();


            }
        });
    }
    public String getTime(int year,int month,int day){
        return  year + "年" + (month <= 9 ? ("0" + month) : month) + "月" + (day <= 9 ? ("0" + day) : day)+"日";
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
                    mActivity.jumpToMedia(0, Constants.REQUEST_CODE_EDIT_IMG, desc);
                }
            });
            return view;
        }


    }
}
