package com.suken.bridgedetection.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;

import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.j256.ormlite.dao.CloseableIterator;
import com.j256.ormlite.dao.ForeignCollection;
import com.suken.bridgedetection.BridgeDetectionApplication;
import com.suken.bridgedetection.Constants;
import com.suken.bridgedetection.R;
import com.suken.bridgedetection.adapter.TestArrayAdapter;
import com.suken.bridgedetection.bean.IVDesc;
import com.suken.bridgedetection.bean.IVDescDao;
import com.suken.bridgedetection.bean.MaintenanceOfOrderBean;
import com.suken.bridgedetection.bean.MaintenanceOfOrderDao;
import com.suken.bridgedetection.bean.MaintenanceOfOrderItemBean;
import com.suken.bridgedetection.location.LocationManager;
import com.suken.bridgedetection.location.LocationResult;
import com.suken.bridgedetection.location.OnLocationFinishedListener;
import com.suken.bridgedetection.util.FileUtils;
import com.suken.bridgedetection.util.Logger;
import com.suken.bridgedetection.util.TextUtil;
import com.suken.bridgedetection.util.UiUtil;
import com.suken.imageditor.ImageditorActivity;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 高速公路施工安全检查表
 */
public class MaintenanceOfOrderActivity extends BaseActivity implements OnLocationFinishedListener {

    private EditText maintenanceoforder_gydw_ev,
            maintenanceoforder_checkDate_ev,
            maintenanceoforder_content_ev,
            maintenanceoforder_qtqk_ev,
            maintenanceoforder_yj_ev,
            maintenanceoforder_jcr_ev,
            maintenanceoforder_jlr_ev;

    private RadioGroup maintenanceoforder_bzbf_radioGroup,
            maintenanceoforder_bzfm_radioGroup,
            maintenanceoforder_aqzy_radioGroup,
            maintenanceoforder_sgzy_radioGroup,
            maintenanceoforder_aqxs_radioGroup,
            maintenanceoforder_aqzgly_radioGroup;

    private ImageView maintenanceoforder_bzbf_xiangji,
            maintenanceoforder_bzbf_video,
            maintenanceoforder_bzfm_xiangji,
            maintenanceoforder_bzfm_video,
            maintenanceoforder_aqzy_xiangji,
            maintenanceoforder_aqzy_video,
            maintenanceoforder_sgzy_xiangji,
            maintenanceoforder_sgzy_video,
            maintenanceoforder_aqxs_xiangji,
            maintenanceoforder_aqxs_video,
            maintenanceoforder_aqzgly_xiangji,
            maintenanceoforder_aqzgly_video;

    private Spinner maintenanceoforder_bzbf_imgSpinner,
            maintenanceoforder_bzfm_imgSpinner,
            maintenanceoforder_aqzy_imgSpinner,
            maintenanceoforder_sgzy_imgSpinner,
            maintenanceoforder_aqxs_imgSpinner,
            maintenanceoforder_aqzgly_imgSpinner,
            maintenanceoforder_weather_spinner;

    private TextView maintenanceoforder_bzbf_videoNum,
            maintenanceoforder_bzfm_videoNum,
            maintenanceoforder_aqzy_videoNum,
            maintenanceoforder_sgzy_videoNum,
            maintenanceoforder_aqxs_videoNum,
            maintenanceoforder_aqzgly_videoNum,
            maintenanceoforder_bzbf_imgNum,
            maintenanceoforder_bzfm_imgNum,
            maintenanceoforder_aqzy_imgNum,
            maintenanceoforder_sgzy_imgNum,
            maintenanceoforder_aqxs_imgNum,
            maintenanceoforder_aqzgly_imgNum,
            saveBtn,gps_text;

    private SpinnerAdapter bzbfAdapter,
            bzfmAdapter,
            aqzyAdapter,
            sgzyAdapter,
            aqxsAdapter,
            aqzglyAdapter;

    private int mYear,mMonth, mDay;

    private List<MaintenanceOfOrderBean> maintenanceOfOrderBeen = new ArrayList<MaintenanceOfOrderBean>();

    private List<MaintenanceOfOrderItemBean> maintenanceOfOrderItemBeens = new ArrayList<MaintenanceOfOrderItemBean>();



    private MaintenanceOfOrderDao maintenanceOfOrderDao;
    private IVDescDao ivDescDao;

    private String[] mStringArrayWeather;
    private ArrayAdapter<String> mArrayWeatherAdapter;
    private String strWeather = "晴";
    private int selsctWeather;
    private int id;


    private Context mContext;
    private Activity mActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance_of_order);
        mContext = this;
        mActivity = this;
        maintenanceOfOrderDao = new MaintenanceOfOrderDao();
        ivDescDao = new IVDescDao();
        id = getIntent().getIntExtra("id", 0);
        LocationManager.getInstance().syncLocation(this);
        initView();
    }

    private void initView() {

        maintenanceoforder_gydw_ev = (EditText) findViewById(R.id.maintenanceoforder_gydw_ev);
        maintenanceoforder_checkDate_ev = (EditText) findViewById(R.id.maintenanceoforder_checkDate_ev);
        maintenanceoforder_content_ev = (EditText) findViewById(R.id.maintenanceoforder_content_ev);
        maintenanceoforder_qtqk_ev = (EditText) findViewById(R.id.maintenanceoforder_qtqk_ev);
        maintenanceoforder_yj_ev = (EditText) findViewById(R.id.maintenanceoforder_yj_ev);
        maintenanceoforder_jcr_ev = (EditText) findViewById(R.id.maintenanceoforder_jcr_ev);
        maintenanceoforder_jlr_ev = (EditText) findViewById(R.id.maintenanceoforder_jlr_ev);

        maintenanceoforder_bzbf_radioGroup = (RadioGroup) findViewById(R.id.maintenanceoforder_bzbf_radioGroup);
        maintenanceoforder_bzfm_radioGroup = (RadioGroup) findViewById(R.id.maintenanceoforder_bzfm_radioGroup);
        maintenanceoforder_aqzy_radioGroup = (RadioGroup) findViewById(R.id.maintenanceoforder_aqzy_radioGroup);
        maintenanceoforder_sgzy_radioGroup = (RadioGroup) findViewById(R.id.maintenanceoforder_sgzy_radioGroup);
        maintenanceoforder_aqxs_radioGroup = (RadioGroup) findViewById(R.id.maintenanceoforder_aqxs_radioGroup);
        maintenanceoforder_aqzgly_radioGroup = (RadioGroup) findViewById(R.id.maintenanceoforder_aqzgly_radioGroup);

        maintenanceoforder_bzbf_xiangji = (ImageView) findViewById(R.id.maintenanceoforder_bzbf_xiangji);
        maintenanceoforder_bzbf_video = (ImageView) findViewById(R.id.maintenanceoforder_bzbf_video);
        maintenanceoforder_bzfm_xiangji = (ImageView) findViewById(R.id.maintenanceoforder_bzfm_xiangji);
        maintenanceoforder_bzfm_video = (ImageView) findViewById(R.id.maintenanceoforder_bzfm_video);
        maintenanceoforder_aqzy_xiangji = (ImageView) findViewById(R.id.maintenanceoforder_aqzy_xiangji);
        maintenanceoforder_aqzy_video = (ImageView) findViewById(R.id.maintenanceoforder_aqzy_video);
        maintenanceoforder_sgzy_xiangji = (ImageView) findViewById(R.id.maintenanceoforder_sgzy_xiangji);
        maintenanceoforder_sgzy_video = (ImageView) findViewById(R.id.maintenanceoforder_sgzy_video);
        maintenanceoforder_aqxs_xiangji = (ImageView) findViewById(R.id.maintenanceoforder_aqxs_xiangji);
        maintenanceoforder_aqxs_video = (ImageView) findViewById(R.id.maintenanceoforder_aqxs_video);
        maintenanceoforder_aqzgly_xiangji = (ImageView) findViewById(R.id.maintenanceoforder_aqzgly_xiangji);
        maintenanceoforder_aqzgly_video = (ImageView) findViewById(R.id.maintenanceoforder_aqzgly_video);

        maintenanceoforder_bzbf_imgSpinner = (Spinner) findViewById(R.id.maintenanceoforder_bzbf_imgSpinner);
        maintenanceoforder_bzfm_imgSpinner = (Spinner) findViewById(R.id.maintenanceoforder_bzfm_imgSpinner);
        maintenanceoforder_aqzy_imgSpinner = (Spinner) findViewById(R.id.maintenanceoforder_aqzy_imgSpinner);
        maintenanceoforder_sgzy_imgSpinner = (Spinner) findViewById(R.id.maintenanceoforder_sgzy_imgSpinner);
        maintenanceoforder_aqxs_imgSpinner = (Spinner) findViewById(R.id.maintenanceoforder_aqxs_imgSpinner);
        maintenanceoforder_aqzgly_imgSpinner = (Spinner) findViewById(R.id.maintenanceoforder_aqzgly_imgSpinner);
        maintenanceoforder_weather_spinner = (Spinner) findViewById(R.id.maintenanceoforder_weather_spinner);

        maintenanceoforder_bzbf_videoNum = (TextView) findViewById(R.id.maintenanceoforder_bzbf_videoNum);
        maintenanceoforder_bzfm_videoNum = (TextView) findViewById(R.id.maintenanceoforder_bzfm_videoNum);
        maintenanceoforder_aqzy_videoNum = (TextView) findViewById(R.id.maintenanceoforder_aqzy_videoNum);
        maintenanceoforder_sgzy_videoNum = (TextView) findViewById(R.id.maintenanceoforder_sgzy_videoNum);
        maintenanceoforder_aqxs_videoNum = (TextView) findViewById(R.id.maintenanceoforder_aqxs_videoNum);
        maintenanceoforder_aqzgly_videoNum = (TextView) findViewById(R.id.maintenanceoforder_aqzgly_videoNum);
        saveBtn = (TextView) findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                saveDialog();
            }
        });
        gps_text = (TextView) findViewById(R.id.gps_text);
        gps_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(gps_text.getText().toString().equals("定位失败，点击从新定位")){
                    LocationManager.getInstance().syncLocation(MaintenanceOfOrderActivity.this);
                }
            }
        });

        maintenanceoforder_bzbf_imgNum = (TextView) findViewById(R.id.maintenanceoforder_bzbf_imgNum);
        maintenanceoforder_bzfm_imgNum = (TextView) findViewById(R.id.maintenanceoforder_bzfm_imgNum);
        maintenanceoforder_aqzy_imgNum = (TextView) findViewById(R.id.maintenanceoforder_aqzy_imgNum);
        maintenanceoforder_sgzy_imgNum = (TextView) findViewById(R.id.maintenanceoforder_sgzy_imgNum);
        maintenanceoforder_aqxs_imgNum = (TextView) findViewById(R.id.maintenanceoforder_aqxs_imgNum);
        maintenanceoforder_aqzgly_imgNum = (TextView) findViewById(R.id.maintenanceoforder_aqzgly_imgNum);



        initTime();
        initSpinner();

        if (id != 0) {
            maintenanceOfOrderBeen = (ArrayList<MaintenanceOfOrderBean>)  maintenanceOfOrderDao.queryByID(id);
            Logger.e("aaa","maintenanceTableBeanList++"+maintenanceOfOrderBeen.toString());
            if(maintenanceOfOrderBeen.size()>0){
                MaintenanceOfOrderBean bean = maintenanceOfOrderBeen.get(0);

                maintenanceoforder_gydw_ev.setText(bean.getGldwName());
                maintenanceoforder_checkDate_ev.setText(bean.getJcsj());
                maintenanceoforder_content_ev.setText(bean.getXcnr());
                maintenanceoforder_qtqk_ev.setText(bean.getQtqk());
//                maintenanceoforder_yj_ev.setText(bean.getClyj());
                maintenanceoforder_jcr_ev.setText(bean.getJcry());
                maintenanceoforder_jlr_ev.setText(bean.getAqgly());

                strWeather = bean.getWeather();
                for (int i = 0; i < mStringArrayWeather.length; i++) {
                    if(mStringArrayWeather[i].equals(strWeather)){
                        selsctWeather = i;
                        break;
                    }
                }
                maintenanceoforder_weather_spinner.setSelection(selsctWeather);

                ForeignCollection<MaintenanceOfOrderItemBean> orders = bean.getMaintenanceOfOrderItemBeen();
                CloseableIterator<MaintenanceOfOrderItemBean> iterator = orders.closeableIterator();
                try {
                    int position = 0;
                    while(iterator.hasNext()){
                        MaintenanceOfOrderItemBean b = iterator.next();

                        List<IVDesc> imageDesc = ivDescDao.getImageMaintenanceOfOrderItemBeanByUserId(b.getId());
                        b.setmImages(imageDesc);

                        List<IVDesc> videoDesc = ivDescDao.getVideoMaintenanceOfOrderItemBeanByUserId(b.getId());
                        b.setmVideo(videoDesc);

                        maintenanceOfOrderItemBeens.add(b);
                        Logger.e("aaa",b.toString());
                        int imageSize = imageDesc.size();
                        int videoSize = videoDesc.size();

                        if(position == 0){
                            maintenanceoforder_bzbf_radioGroup.check(b.getJczt().equals("不符合检查标准")? R.id.maintenanceoforder_bzbf_no: R.id.maintenanceoforder_bzbf_yes);
                            maintenanceoforder_bzbf_videoNum.setText(videoSize + "");
                            maintenanceoforder_bzbf_imgNum.setText(imageSize + "");
                        }else if(position == 1){
                            maintenanceoforder_bzfm_radioGroup.check(b.getJczt().equals("不符合检查标准")? R.id.maintenanceoforder_bzfm_no: R.id.maintenanceoforder_bzfm_yes);
                            maintenanceoforder_bzfm_videoNum.setText(videoSize + "");
                            maintenanceoforder_bzfm_imgNum.setText(imageSize + "");
                        }else if(position == 2){
                            maintenanceoforder_aqzy_radioGroup.check(b.getJczt().equals("不符合检查标准")? R.id.maintenanceoforder_aqzy_no: R.id.maintenanceoforder_aqzy_yes);
                            maintenanceoforder_aqzy_videoNum.setText(videoSize + "");
                            maintenanceoforder_aqzy_imgNum.setText(imageSize + "");
                        }else if(position == 3){
                            maintenanceoforder_sgzy_radioGroup.check(b.getJczt().equals("不符合检查标准")? R.id.maintenanceoforder_sgzy_no: R.id.maintenanceoforder_sgzy_yes);
                            maintenanceoforder_sgzy_videoNum.setText(videoSize + "");
                            maintenanceoforder_sgzy_imgNum.setText(imageSize + "");
                        }else if(position == 4){
                            maintenanceoforder_aqxs_radioGroup.check(b.getJczt().equals("不符合检查标准")? R.id.maintenanceoforder_aqxs_no: R.id.maintenanceoforder_aqxs_yes);
                            maintenanceoforder_aqxs_videoNum.setText(videoSize + "");
                            maintenanceoforder_aqxs_imgNum.setText(imageSize + "");
                        }else if(position == 5){
                            maintenanceoforder_aqzgly_radioGroup.check(b.getJczt().equals("不符合检查标准")? R.id.maintenanceoforder_aqzgly_no: R.id.maintenanceoforder_aqzgly_yes);
                            maintenanceoforder_aqzgly_videoNum.setText(videoSize + "");
                            maintenanceoforder_aqzgly_imgNum.setText(imageSize + "");
                        }

                        position++;
                    }
                } finally {

                    try {
                        iterator.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

            }else{
                maintenanceoforder_gydw_ev.setText(BridgeDetectionApplication.mCurrentUser.getDefgqName());
                maintenanceoforder_jcr_ev.setText(BridgeDetectionApplication.mCurrentUser.getUserName());
                seThisNewMaintenanceOfOrderItemBeen();
            }

        }else{
            maintenanceoforder_gydw_ev.setText(BridgeDetectionApplication.mCurrentUser.getDefgqName());
            maintenanceoforder_jcr_ev.setText(BridgeDetectionApplication.mCurrentUser.getUserName());
            seThisNewMaintenanceOfOrderItemBeen();
        }
        initRadioGroup();
        setClick();
        setSpinnerAdapter();


    }
    public void setClick(){

        maintenanceoforder_bzbf_xiangji.setOnClickListener(listener);
        maintenanceoforder_bzbf_video.setOnClickListener(listener);
        maintenanceoforder_bzfm_xiangji.setOnClickListener(listener);
        maintenanceoforder_bzfm_video.setOnClickListener(listener);
        maintenanceoforder_aqzy_xiangji.setOnClickListener(listener);
        maintenanceoforder_aqzy_video.setOnClickListener(listener);
        maintenanceoforder_sgzy_xiangji.setOnClickListener(listener);
        maintenanceoforder_sgzy_video.setOnClickListener(listener);
        maintenanceoforder_aqxs_xiangji.setOnClickListener(listener);
        maintenanceoforder_aqxs_video.setOnClickListener(listener);
        maintenanceoforder_aqzgly_xiangji.setOnClickListener(listener);
        maintenanceoforder_aqzgly_video.setOnClickListener(listener);


    }

    OnClickListener listener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.maintenanceoforder_bzbf_xiangji:
                    jumpToMedia(0, Constants.REQUEST_CODE_CAMERA, null);
                    break;
                case R.id.maintenanceoforder_bzbf_video:
                    jumpToMedia(0, Constants.REQUEST_CODE_VIDEO, null);
                    break;
                case R.id.maintenanceoforder_bzfm_xiangji:
                    jumpToMedia(1, Constants.REQUEST_CODE_CAMERA, null);
                    break;
                case R.id.maintenanceoforder_bzfm_video:
                    jumpToMedia(1, Constants.REQUEST_CODE_VIDEO, null);
                    break;
                case R.id.maintenanceoforder_aqzy_xiangji:
                    jumpToMedia(2, Constants.REQUEST_CODE_CAMERA, null);
                    break;
                case R.id.maintenanceoforder_aqzy_video:
                    jumpToMedia(2, Constants.REQUEST_CODE_VIDEO, null);
                    break;
                case R.id.maintenanceoforder_sgzy_xiangji:
                    jumpToMedia(3, Constants.REQUEST_CODE_CAMERA, null);
                    break;
                case R.id.maintenanceoforder_sgzy_video:
                    jumpToMedia(3, Constants.REQUEST_CODE_VIDEO, null);
                    break;
                case R.id.maintenanceoforder_aqxs_xiangji:
                    jumpToMedia(4, Constants.REQUEST_CODE_CAMERA, null);
                    break;
                case R.id.maintenanceoforder_aqxs_video:
                    jumpToMedia(4, Constants.REQUEST_CODE_VIDEO, null);
                    break;
                case R.id.maintenanceoforder_aqzgly_xiangji:
                    jumpToMedia(5, Constants.REQUEST_CODE_CAMERA, null);
                    break;
                case R.id.maintenanceoforder_aqzgly_video:
                    jumpToMedia(5, Constants.REQUEST_CODE_VIDEO, null);
                    break;

            }
        }
    };

    private void seThisNewMaintenanceOfOrderItemBeen(){
//        maintenanceOfOrderItemBeens


        for (int i = 0; i < 6; i++) {
            MaintenanceOfOrderItemBean bean = new MaintenanceOfOrderItemBean();
//            bean.setVersionno("1");
//            bean.setCreateBy(BridgeDetectionApplication.mCurrentUser.getUserId());
//            bean.setUpdateBy(BridgeDetectionApplication.mCurrentUser.getUserId());
//            bean.setCreator(BridgeDetectionApplication.mCurrentUser.getUserName());
//            bean.setUpdateBy(BridgeDetectionApplication.mCurrentUser.getUserName());
//            SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//            String date = sDateFormat.format(new java.util.Date());
//            bean.setUpdatetime(date);
//            bean.setCreatetime(date);
//            bean.setFlag("0");
            bean.setTpjd("123.12");
            bean.setTpwd("123.12");
            bean.setJczt("符合检查标准");
            if(i == 0){
                bean.setJcx("施工标志摆放正确(是,否)");

            }else if(i == 1){
                bean.setJcx("施工人员标志服、标志帽(是,否)");

            }else if(i == 2){
                bean.setJcx("施工程序符合安全作业规程(是,否)");

            }else if(i == 3){
                bean.setJcx("施工车辆有明显施工作业标志(是,否)");

            }else if(i == 4){
                bean.setJcx("施工车辆安全行驶(是,否)");

            }else if(i == 5){
                bean.setJcx("施工现场有安全管理员(是,否)");

            }

            maintenanceOfOrderItemBeens.add(bean);
        }


    }

    private void initRadioGroup() {
//

        maintenanceoforder_bzbf_radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.maintenanceoforder_bzbf_yes:
                        maintenanceOfOrderItemBeens.get(0).setJczt("符合检查标准");
                        break;
                    case R.id.maintenanceoforder_bzbf_no:
                        maintenanceOfOrderItemBeens.get(0).setJczt("不符合检查标准");
                        break;
                }
            }
        });

        maintenanceoforder_bzfm_radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.maintenanceoforder_bzfm_yes:
                        maintenanceOfOrderItemBeens.get(1).setJczt("符合检查标准");
                        break;
                    case R.id.maintenanceoforder_bzfm_no:
                        maintenanceOfOrderItemBeens.get(1).setJczt("不符合检查标准");
                        break;
                }
            }
        });
        maintenanceoforder_aqzy_radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.maintenanceoforder_aqzy_yes:
                        maintenanceOfOrderItemBeens.get(2).setJczt("符合检查标准");
                        break;
                    case R.id.maintenanceoforder_aqzy_no:
                        maintenanceOfOrderItemBeens.get(2).setJczt("不符合检查标准");
                        break;
                }
            }
        });
        maintenanceoforder_sgzy_radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.maintenanceoforder_sgzy_yes:
                        maintenanceOfOrderItemBeens.get(3).setJczt("符合检查标准");
                        break;
                    case R.id.maintenanceoforder_sgzy_no:
                        maintenanceOfOrderItemBeens.get(3).setJczt("不符合检查标准");
                        break;
                }
            }
        });

        maintenanceoforder_aqxs_radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.maintenanceoforder_aqxs_yes:
                        maintenanceOfOrderItemBeens.get(4).setJczt("符合检查标准");
                        break;
                    case R.id.maintenanceoforder_aqxs_no:
                        maintenanceOfOrderItemBeens.get(4).setJczt("不符合检查标准");
                        break;
                }
            }
        });

        maintenanceoforder_aqzgly_radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.maintenanceoforder_aqzgly_yes:
                        maintenanceOfOrderItemBeens.get(5).setJczt("符合检查标准");
                        break;
                    case R.id.maintenanceoforder_aqzgly_no:
                        maintenanceOfOrderItemBeens.get(5).setJczt("不符合检查标准");
                        break;
                }
            }
        });

    }

    private void initTime() {
        Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH)+1;
        mDay = c.get(Calendar.DATE);
        maintenanceoforder_checkDate_ev.setText(getTime(mYear, mMonth, mDay));
        maintenanceoforder_checkDate_ev.setKeyListener(null);
        maintenanceoforder_checkDate_ev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker();
            }
        });
    }
    public String getTime(int year,int month,int day){
        return  year + "-" + (month <= 9 ? ("0" + month) : month) + "-" + (day <= 9 ? ("0" + day) : day);
    }
    public void showDatePicker(){
        DatePickerDialog dlg = new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                mYear = year;
                mMonth = month + 1;
                mDay = day;
                maintenanceoforder_checkDate_ev.setText(getTime(mYear, mMonth, mDay));
            }
        }, mYear, mMonth - 1, mDay);
        dlg.setTitle("日期：");
        dlg.show();

    }


    public void setSpinnerAdapter(){
        bzbfAdapter = new SpinnerAdapter();
        bzbfAdapter.setItem(maintenanceOfOrderItemBeens.get(0).getmImages());
        maintenanceoforder_bzbf_imgSpinner.setAdapter(bzbfAdapter);

        bzfmAdapter = new SpinnerAdapter();
        bzfmAdapter.setItem(maintenanceOfOrderItemBeens.get(1).getmImages());
        maintenanceoforder_bzfm_imgSpinner.setAdapter(bzfmAdapter);

        aqzyAdapter = new SpinnerAdapter();
        aqzyAdapter.setItem(maintenanceOfOrderItemBeens.get(2).getmImages());
        maintenanceoforder_aqzy_imgSpinner.setAdapter(aqzyAdapter);

        sgzyAdapter = new SpinnerAdapter();
        sgzyAdapter.setItem(maintenanceOfOrderItemBeens.get(3).getmImages());
        maintenanceoforder_sgzy_imgSpinner.setAdapter(sgzyAdapter);

        aqxsAdapter = new SpinnerAdapter();
        aqxsAdapter.setItem(maintenanceOfOrderItemBeens.get(4).getmImages());
        maintenanceoforder_aqxs_imgSpinner.setAdapter(aqxsAdapter);

        aqzglyAdapter = new SpinnerAdapter();
        aqzglyAdapter.setItem(maintenanceOfOrderItemBeens.get(5).getmImages());
        maintenanceoforder_aqzgly_imgSpinner.setAdapter(aqzglyAdapter);
    }
    private void initSpinner() {
        maintenanceoforder_weather_spinner = (Spinner) findViewById(R.id.maintenanceoforder_weather_spinner);
        mStringArrayWeather = getResources().getStringArray(R.array.spinnerWeather);
        mArrayWeatherAdapter = new TestArrayAdapter(mContext, mStringArrayWeather);
        //设置下拉列表风格(这句不些也行)
        //mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        maintenanceoforder_weather_spinner.setAdapter(mArrayWeatherAdapter);
        //监听Item选中事件
        maintenanceoforder_weather_spinner.setOnItemSelectedListener(new ItemSelectedListenerImpl());

    }

    boolean mIsGpsSuccess;
    double latitude, longitude;
    @Override
    public void onLocationFinished(LocationResult result) {

        if(this == null || ((BaseActivity)this).isDestroyed() || this.isFinishing()){
            return;
        }

        if (result.isSuccess) {
            mIsGpsSuccess = true;
            Logger.e("aaa","经度:" + result.latitude);
            Logger.e("aaa","纬度:" + result.longitude);
            latitude =  result.latitude;
            longitude =  result.longitude;

            Toast.makeText(this, "定位成功 经度:" + result.latitude+",纬度:" + result.longitude, Toast.LENGTH_SHORT).show();
//            mjingdu.setText("经度:" + result.latitude);
//            mWeidu.setText("纬度:" + result.longitude);
//            TextView tv = (TextView) getActivity().findViewById(R.id.syncLocationTv);
            gps_text.setText("定位成功");
            gps_text.setTextColor(Color.WHITE);
        } else if(!mIsGpsSuccess){
            Toast.makeText(this, "定位失败！请您到空旷的地点从新定位，绝就不要在室内！", Toast.LENGTH_LONG).show();
//            TextView tv = (TextView) getActivity().findViewById(R.id.syncLocationTv);
            gps_text.setText("定位失败，点击从新定位");
            gps_text.setTextColor(Color.RED);
        }
    }

    private class ItemSelectedListenerImpl implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long arg3) {
            strWeather = mStringArrayWeather[position];
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }

    public void onClick(View view){
        switch (view.getId()) {
            case R.id.maintenanceoforder_back:
                back();
                break;
            case R.id.maintenanceoforder_save:
                saveDialog();
                break;
        }

    }

    public void back() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提醒");
        builder.setMessage("返回将丢失当前未保存信息");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    public void saveDialog(){
        new AlertDialog.Builder(mContext)
                .setTitle("保存数据")
                .setMessage("是否保存当前数据？")
                .setPositiveButton("保存", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

//                        if(!mIsGpsSuccess){
//                            Toast.makeText(mContext, "正在定位...\n" +
//                                    "请您到空旷的地点从新定位，绝就不要在室内", Toast.LENGTH_SHORT).show();
//                            return;
//                        }

                        String gydw = maintenanceoforder_gydw_ev.getText().toString();
                        String checkDate = maintenanceoforder_checkDate_ev.getText().toString();
                        String content = maintenanceoforder_content_ev.getText().toString();
                        String qtqk = maintenanceoforder_qtqk_ev.getText().toString();
                        String yj = maintenanceoforder_yj_ev.getText().toString();
                        String jcr = maintenanceoforder_jcr_ev.getText().toString();
                        String jlr = maintenanceoforder_jlr_ev.getText().toString();
                        if(TextUtil.isEmptyString(content)){
                            toast("“施工项目及内容”不可为空！");
                            return;
                        }
                        if(TextUtil.isEmptyString(jcr)){
                            toast("“检查人”不可为空！");
                            return;
                        }
                        if(TextUtil.isEmptyString(jlr)){
                            toast("“安全管理员”不可为空！");
                            return;
                        }
                        MaintenanceOfOrderBean bean = new MaintenanceOfOrderBean();
                        bean.setGldwName(gydw);
                        bean.setJcsj(checkDate);
                        bean.setWeather(strWeather);
                        bean.setXcnr(content);
                        bean.setQtqk(qtqk);
//                        bean.setClyj(yj);
                        bean.setJcry(jcr);
                        bean.setAqgly(jlr);
                        if (id != 0) {
                            bean.setId(id);
                        }


                        Logger.e("aaa","maintenanceTableBean.toString()===="+bean.toString());
                        if (id != 0) {
                            maintenanceOfOrderDao.update(bean);
                        }else{
                            maintenanceOfOrderDao.add(bean);
                        }


                        for (int j = 0; j < maintenanceOfOrderItemBeens.size(); j++) {
                            MaintenanceOfOrderItemBean  itemBean = maintenanceOfOrderItemBeens.get(j);
                            itemBean.setTpjd(latitude+"");
                            itemBean.setTpwd(longitude+"");
                            itemBean.setMaintenanceOfOrderBean(bean);
                            if (itemBean.getId() != 0) {
                                maintenanceOfOrderDao.updateItem(itemBean);
                            }else {
                                maintenanceOfOrderDao.addItem(itemBean);
                            }

                            List<IVDesc> imagesDescList = itemBean.getmImages();
                            List<IVDesc> videoDescList = itemBean.getmVideo();
                            for(int q = 0; q < imagesDescList.size(); q++){
                                IVDesc imageDesc = imagesDescList.get(q);
                                imageDesc.setImageMaintenanceOfOrderItemBean(itemBean);
                                if (imageDesc.getId() != 0) {
                                    ivDescDao.update(imageDesc);
                                }else {
                                    ivDescDao.add(imageDesc);
                                }
                            }
                            for(int w = 0; w < videoDescList.size(); w++){
                                IVDesc videoDesc = videoDescList.get(w);
                                videoDesc.setVideoMaintenanceOfOrderItemBean(itemBean);
                                if (videoDesc.getId() != 0) {
                                    ivDescDao.update(videoDesc);
                                }else {
                                    ivDescDao.add(videoDesc);
                                }
                            }
                        }




                        Logger.e("aaa", "queryAll===" + ivDescDao.queryAll().toString());
                        finish();




                    }
                })
                .setNegativeButton("取消，再改改", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .show();


    }

    private Uri mOutPutFileUri = null;
    File mPlayerFile;
    private int mPosition;
    //    private FormItemController mEditController;
    public void jumpToMedia(int position, int requestCode, IVDesc desc) {
//        mEditController = con;
        mPosition = position;
        String path = Environment.getExternalStorageDirectory().toString() + File.separator + getPackageName();
        File path1 = new File(path);
        if (!path1.exists()) {
            path1.mkdirs();
        }
        String name = "";
        if (requestCode == Constants.REQUEST_CODE_CAMERA) {
            name = path1 + File.separator + generateMediaName(true);
        } else if (requestCode == Constants.REQUEST_CODE_EDIT_IMG) {
            name = desc.path;
        } else {
            name = path1 + File.separator + generateMediaName(false);
        }
        mPlayerFile = new File(name);
        mOutPutFileUri = Uri.fromFile(mPlayerFile);
        Intent intent = new Intent();
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mOutPutFileUri);
        if (requestCode == Constants.REQUEST_CODE_CAMERA) {
            intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, requestCode);
        } else if (requestCode == Constants.REQUEST_CODE_EDIT_IMG) {
            intent.setClass(this, ImageditorActivity.class);
            startActivityForResult(intent, requestCode);
        } else {
            // intent.setClass(this, RecorderActivity.class);
            intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 0);// 参数设置可以省略
            intent.setAction(MediaStore.ACTION_VIDEO_CAPTURE);
            startActivityForResult(intent, requestCode);
        }
    }

    String videofileName;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Logger.e("aaa", "requestCode===" + requestCode);
        File f = null;
        if(resultCode == RESULT_OK) {
            try {
                f = new File(new URI(mOutPutFileUri.toString()));
                if (!f.exists()) {
//                f.mkdirs();
                }
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
        Logger.e("aaa", "requestCode===" + requestCode);
        if (requestCode == Constants.REQUEST_CODE_CAMERA && resultCode == RESULT_OK) {
            IVDesc desc = new IVDesc();
            desc.name = f.getName();
            desc.path = f.getPath();
            Logger.e("aaa", " desc.name===" + desc.name);
            Logger.e("aaa", " desc.path===" + desc.path);

            maintenanceOfOrderItemBeens.get(mPosition).getmImages().add(desc);

            List<IVDesc> images = maintenanceOfOrderItemBeens.get(mPosition).getmImages();
            int size = images.size();
            Logger.e("aaa", "position==" + mPosition);
            Logger.e("aaa", "size==" + size);
            if(mPosition == 0){
                bzbfAdapter.setItem(images);
                bzbfAdapter.notifyDataSetChanged();
                maintenanceoforder_bzbf_imgNum.setText(size + "");
            }else if(mPosition == 1){
                bzfmAdapter.setItem(images);
                bzfmAdapter.notifyDataSetChanged();
                maintenanceoforder_bzfm_imgNum.setText(size + "");
            }else if(mPosition == 2){
                aqzyAdapter.setItem(images);
                aqzyAdapter.notifyDataSetChanged();
                maintenanceoforder_aqzy_imgNum.setText(size + "");
            }else if(mPosition == 3){
                sgzyAdapter.setItem(images);
                sgzyAdapter.notifyDataSetChanged();
                maintenanceoforder_sgzy_imgNum.setText(size + "");
            }else if(mPosition == 4){
                aqxsAdapter.setItem(images);
                aqxsAdapter.notifyDataSetChanged();
                maintenanceoforder_aqxs_imgNum.setText(size + "");
            }else if(mPosition == 5){
                aqzglyAdapter.setItem(images);
                aqzglyAdapter.notifyDataSetChanged();
                maintenanceoforder_aqzgly_imgNum.setText(size + "");
            }
//            mAdapter.setData(maintenanceTableItemBeen);
//            mAdapter.notifyDataSetChanged();

//            mEditController.updateImg(desc);
        } else if (requestCode == Constants.REQUEST_CODE_EDIT_IMG) {
            // 保存在原先的图片中所以不处理

        } else if (requestCode == Constants.REQUEST_CODE_VIDEO && resultCode == RESULT_OK) {
            String str = null;
            IVDesc desc = new IVDesc();
            try {
                Log.e("aaa", "333333");
                desc.name = mPlayerFile.getName();
                desc.path = mPlayerFile.getPath();
                Logger.e("aaa", " REQUEST_CODE_VIDEO  +====== desc.name===" + desc.name);
                Logger.e("aaa", " REQUEST_CODE_VIDEO  +====== desc.path===" + desc.path);


                Uri uri = Uri.parse(data.getData().toString());

                ContentResolver cr = this.getContentResolver();

                Cursor cursor = cr.query(uri, null, null, null, null);
                cursor.moveToFirst();
                str = cursor.getString(1);
                videofileName = cursor.getString(2);
                cursor.close();

                File srcfile = new File(str);

                FileUtils.moveFileTo(srcfile, mPlayerFile);

                super.onActivityResult(requestCode, resultCode, data);
            }
            catch(Exception e) {

            }




            maintenanceOfOrderItemBeens.get(mPosition).getmVideo().add(desc);
            List<IVDesc> videos = maintenanceOfOrderItemBeens.get(mPosition).getmVideo();
            int size = videos.size();

            if(mPosition == 0){
                maintenanceoforder_bzbf_videoNum.setText(size + "");
            }else if(mPosition == 1){
                maintenanceoforder_bzfm_videoNum.setText(size + "");
            }else if(mPosition == 2){
                maintenanceoforder_aqzy_videoNum.setText(size + "");
            }else if(mPosition == 3){
                maintenanceoforder_sgzy_videoNum.setText(size + "");
            }else if(mPosition == 4){
                maintenanceoforder_aqxs_videoNum.setText(size + "");
            }else if(mPosition == 5){
                maintenanceoforder_aqzgly_videoNum.setText(size + "");
            }


//            mAdapter.setData(maintenanceTableItemBeen);
//            mAdapter.notifyDataSetChanged();
//            mEditController.updateVideo(desc);
        }
    }

    public String generateMediaName(boolean isImg) {
        if (isImg) {
            return "pic-" + System.currentTimeMillis() + "-image.png";
        } else {
            return "vdo-" + System.currentTimeMillis() + "-video.3gp";
        }
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
                    jumpToMedia(mPosition, Constants.REQUEST_CODE_EDIT_IMG, desc);
                }
            });
            return view;
        }


    }



}
