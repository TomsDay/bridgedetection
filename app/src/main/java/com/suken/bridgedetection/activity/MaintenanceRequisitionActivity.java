package com.suken.bridgedetection.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.googlecode.androidannotations.api.BackgroundExecutor;
import com.j256.ormlite.dao.CloseableIterator;
import com.j256.ormlite.dao.ForeignCollection;
import com.suken.bridgedetection.BridgeDetectionApplication;
import com.suken.bridgedetection.R;
import com.suken.bridgedetection.RequestType;
import com.suken.bridgedetection.adapter.CooperationListAdapter;
import com.suken.bridgedetection.adapter.MaintenanceRequisitionAdapter;
import com.suken.bridgedetection.adapter.OfOrderListAdapter;
import com.suken.bridgedetection.adapter.QualityDemandListAdapter;
import com.suken.bridgedetection.adapter.TestArrayAdapter;
import com.suken.bridgedetection.bean.CooperationBean;
import com.suken.bridgedetection.bean.CooperationDao;
import com.suken.bridgedetection.bean.MaintenanceLogBean;
import com.suken.bridgedetection.bean.MaintenanceLogItemBean;
import com.suken.bridgedetection.bean.QualityDemandBean;
import com.suken.bridgedetection.bean.QualityDemandDao;
import com.suken.bridgedetection.bean.UpkeepdiseaseListBean;
import com.suken.bridgedetection.bean.UploadUpkeepnoticeBean;
import com.suken.bridgedetection.bean.UploadUpkeepnoticeDao;
import com.suken.bridgedetection.bean.WxdbhByUID;
import com.suken.bridgedetection.http.HttpTask;
import com.suken.bridgedetection.http.OnReceivedHttpResponseListener;
import com.suken.bridgedetection.location.LocationResult;
import com.suken.bridgedetection.location.OnLocationFinishedListener;
import com.suken.bridgedetection.util.DateUtil;
import com.suken.bridgedetection.util.DeviceInfoUtil;
import com.suken.bridgedetection.util.Logger;
import com.suken.bridgedetection.util.TextUtil;
import com.suken.bridgedetection.widget.DateTimePickDialogUtil;
import com.suken.bridgedetection.widget.ListViewForScrollView;
import com.yuntongxun.ecdemo.common.utils.ToastUtil;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.suken.bridgedetection.R.id.button;
import static com.suken.bridgedetection.R.id.maintenancelog_data_ev;

public class MaintenanceRequisitionActivity extends BaseActivity implements OnLocationFinishedListener {

    private EditText maintenancerequisition_gydw_ev,
            maintenancerequisition_wxbm_ev,
            maintenancerequisition_xcld_ev,
            maintenancerequisition_jcsjstart_ev,
            maintenancerequisition_jcsjend_ev,
            maintenancerequisition_zlyq_ev,
            maintenancerequisition_bcsm_ev,
            maintenancerequisition_qfr_ev,
            maintenancerequisition_qfrq_ev;

    private RadioGroup maintenancerequisition_wxlx_radio;

    private TextView maintenancerequisition_fx_tv,
            gps_text,
            maintenancerequisition_zh_tv,
            maintenancerequisition_bhmc_tv,
            maintenancerequisition_bhxq_tv,
            maintenancerequisition_dw_tv,
            maintenancerequisition_ygsl_tv;

    private Spinner maintenancerequisition_weather_spinner;

    List<WxdbhByUID> getwxdbhByUID;

    Context mContext;

    private String[] mStringArrayWeather;
    private ArrayAdapter<String> mArrayWeatherAdapter;
    private String strWeather = "晴";
    private int wxlxPosition = 1;

    private CooperationDao cooperationDao;
    private QualityDemandDao qualityDemandDao;

    List<CooperationBean> cooperationBeens;
    List<QualityDemandBean> qualityDemandBeens;

    ListViewForScrollView maintenancerequisition_listview;
    int id;

    private List<UploadUpkeepnoticeBean> uploadUpkeepnoticeBeens;
    private UploadUpkeepnoticeDao uploadUpkeepnoticeDao;

    private List<UpkeepdiseaseListBean> upkeepdiseaseListBeens = new ArrayList<UpkeepdiseaseListBean>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance_requisition);
//        toast("生成维修通知单 成功！");
        mContext = MaintenanceRequisitionActivity.this;
        uploadUpkeepnoticeDao = new UploadUpkeepnoticeDao();
        cooperationDao = new CooperationDao();
        qualityDemandDao = new QualityDemandDao();
        cooperationBeens = cooperationDao.queryAll();
        qualityDemandBeens = qualityDemandDao.queryAll();
        initView();
    }

    public void initView() {
        maintenancerequisition_gydw_ev = (EditText) findViewById(R.id.maintenancerequisition_gydw_ev);
        maintenancerequisition_xcld_ev = (EditText) findViewById(R.id.maintenancerequisition_xcld_ev);
        maintenancerequisition_xcld_ev.setKeyListener(null);
        maintenancerequisition_wxbm_ev = (EditText) findViewById(R.id.maintenancerequisition_wxbm_ev);
        maintenancerequisition_jcsjstart_ev = (EditText) findViewById(R.id.maintenancerequisition_jcsjstart_ev);
        maintenancerequisition_jcsjend_ev = (EditText) findViewById(R.id.maintenancerequisition_jcsjend_ev);
        maintenancerequisition_zlyq_ev = (EditText) findViewById(R.id.maintenancerequisition_zlyq_ev);
        maintenancerequisition_bcsm_ev = (EditText) findViewById(R.id.maintenancerequisition_bcsm_ev);
        maintenancerequisition_qfr_ev = (EditText) findViewById(R.id.maintenancerequisition_qfr_ev);
        maintenancerequisition_qfrq_ev = (EditText) findViewById(R.id.maintenancerequisition_qfrq_ev);

        maintenancerequisition_wxlx_radio = (RadioGroup) findViewById(R.id.maintenancerequisition_wxlx_radio);

        maintenancerequisition_fx_tv = (TextView) findViewById(R.id.maintenancerequisition_fx_tv);
        maintenancerequisition_zh_tv = (TextView) findViewById(R.id.maintenancerequisition_zh_tv);
        maintenancerequisition_bhmc_tv = (TextView) findViewById(R.id.maintenancerequisition_bhmc_tv);
        maintenancerequisition_bhxq_tv = (TextView) findViewById(R.id.maintenancerequisition_bhxq_tv);
        maintenancerequisition_dw_tv = (TextView) findViewById(R.id.maintenancerequisition_dw_tv);
        maintenancerequisition_ygsl_tv = (TextView) findViewById(R.id.maintenancerequisition_ygsl_tv);
        gps_text = (TextView) findViewById(R.id.gps_text);

        maintenancerequisition_listview = (ListViewForScrollView) findViewById(R.id.maintenancerequisition_listview);

        initData();
        initListDialog();

        maintenancerequisition_wxlx_radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.maintenancerequisition_wxlx_zy) {
                    wxlxPosition = 1;
                } else if (checkedId == R.id.maintenancerequisition_wxlx_by) {
                    wxlxPosition = 2;
                }
            }
        });


    }

    public void initListDialog() {
        maintenancerequisition_wxbm_ev.setKeyListener(null);
        maintenancerequisition_wxbm_ev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCooperationListDialog();
            }
        });
        maintenancerequisition_zlyq_ev.setKeyListener(null);
        maintenancerequisition_zlyq_ev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showQualityDemandListDialog();
            }
        });
    }

    MaintenanceRequisitionAdapter adapter;


    public void getAllData(){

            uploadUpkeepnoticeBeens = uploadUpkeepnoticeDao.queryByuserID(BridgeDetectionApplication.mCurrentUser.getUserId());

            if(!TextUtil.isListEmpty(uploadUpkeepnoticeBeens)){
                for(int i = 0;i<uploadUpkeepnoticeBeens.size();i++){
                    UploadUpkeepnoticeBean bean = uploadUpkeepnoticeBeens.get(i);
                    ForeignCollection<UpkeepdiseaseListBean> orders = bean.getUpkeepdiseaseListBeen();
                    CloseableIterator<UpkeepdiseaseListBean> iterator = orders.closeableIterator();
                    List<UpkeepdiseaseListBean> itemBeanList = new ArrayList<UpkeepdiseaseListBean>();
                    try {
                        while(iterator.hasNext()){
                            UpkeepdiseaseListBean b = iterator.next();
                            itemBeanList.add(b);
                            Logger.e("aaa",b.toString());
                        }
                    } finally {
                        bean.setUpkeepdiseaseList(itemBeanList);
                        uploadUpkeepnoticeBeens.set(i, bean);
                        try {
                            iterator.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }


            }
            Logger.e("aaa", "--------" + uploadUpkeepnoticeBeens.toString());
    }
    public void initData() {

        initTime();
        initSpinner();
        id = getIntent().getIntExtra("id", 0);
        adapter = new MaintenanceRequisitionAdapter(this);
        if (id != 0) {
            getAllData();
            UploadUpkeepnoticeBean bean = uploadUpkeepnoticeBeens.get(0);
            upkeepdiseaseListBeens = bean.getUpkeepdiseaseList();
            wxlxPosition = Integer.parseInt(bean.getWxlx());
            maintenancerequisition_gydw_ev.setText(bean.getGldwName());
            maintenancerequisition_qfr_ev.setText(bean.getQfry());
            maintenancerequisition_xcld_ev.setText(bean.getTzld());
            strWeather = bean.getWeather();
            Logger.e("aaa", "strWeather===" + strWeather);
            int selsctWeather = 0;
            for (int i = 0; i < mStringArrayWeather.length; i++) {
                if(mStringArrayWeather[i].equals(strWeather)){
                    selsctWeather = i;
                    break;
                }
            }
            maintenancerequisition_weather_spinner.setSelection(selsctWeather);
            maintenancerequisition_wxbm_ev.setText(bean.getWxbmmc());
            maintenancerequisition_wxlx_radio.check(
                    bean.getWxlx().equals("2")?R.id.maintenancerequisition_wxlx_by:R.id.maintenancerequisition_wxlx_zy);
            maintenancerequisition_jcsjstart_ev.setText(bean.getWxks());
            maintenancerequisition_jcsjend_ev.setText(bean.getWxjs());
            maintenancerequisition_zlyq_ev.setText(bean.getZlyq());
            maintenancerequisition_bcsm_ev.setText(bean.getBcsm());
            maintenancerequisition_qfrq_ev.setText(bean.getQfrq());

            zlyqidContent = bean.getZlyqid();

            zlyqContent = bean.getZlyq();

            String zlidArray[] = zlyqidContent.split(";");
            Logger.e("aaa","质量要求id："+zlyqidContent);
            for(QualityDemandBean qd:qualityDemandBeens){
                for(String zlid:zlidArray){
                    if(zlid.equals(qd.getId())){
                        qd.setChecked(true);
                    }
                }

            }

            upkeepdiseaseListBeens = bean.getUpkeepdiseaseList();


        } else {
            getwxdbhByUID = (List<WxdbhByUID>) getIntent().getSerializableExtra("list");

            for(WxdbhByUID getBean:getwxdbhByUID){
                UpkeepdiseaseListBean bean = new UpkeepdiseaseListBean();
                bean.setXcbhid(getBean.getId());
                bean.setXcrzno(getBean.getYhInspNo());
                bean.setBhid(getBean.getBhid());
                bean.setBhmc(getBean.getBhmc());
                bean.setBhwz(getBean.getBhwz());
                bean.setFx(getBean.getFx());
                bean.setYhzh(getBean.getYhzh());
                bean.setDw(getBean.getDw());
                bean.setYgsl(getBean.getYgsl());
                bean.setRemark(getBean.getRemark());
                upkeepdiseaseListBeens.add(bean);
            }


            maintenancerequisition_gydw_ev.setText(BridgeDetectionApplication.mCurrentUser.getDefgqName());
            maintenancerequisition_qfr_ev.setText(BridgeDetectionApplication.mCurrentUser.getUserName());
            maintenancerequisition_xcld_ev.setText(getwxdbhByUID.get(0).getXcld());



        }
        adapter.setData(upkeepdiseaseListBeens);
        maintenancerequisition_listview.setAdapter(adapter);





    }

    private void initSpinner() {
        maintenancerequisition_weather_spinner = (Spinner) findViewById(R.id.maintenancerequisition_weather_spinner);
        mStringArrayWeather = getResources().getStringArray(R.array.spinnerWeather);
        mArrayWeatherAdapter = new TestArrayAdapter(mContext, mStringArrayWeather);
//        设置下拉列表风格(这句不些也行)
//        mAdapter.setDropDownViewResource(android.R.layout.simple);
        maintenancerequisition_weather_spinner.setAdapter(mArrayWeatherAdapter);
        //监听Item选中事件
        maintenancerequisition_weather_spinner.setOnItemSelectedListener(new ItemSelectedListenerImpl());

    }

    boolean mIsGpsSuccess;
    double latitude, longitude;

    @Override
    public void onLocationFinished(LocationResult result) {
        if (this == null || ((BaseActivity) this).isDestroyed() || this.isFinishing()) {
            return;
        }

        if (result.isSuccess) {
            mIsGpsSuccess = true;
            Logger.e("aaa", "经度:" + result.longitude);
            Logger.e("aaa", "纬度:" + result.latitude);
            latitude = result.latitude;
            longitude = result.longitude;

            Toast.makeText(this, "定位成功 经度:" + result.longitude + ",纬度:" + result.latitude, Toast.LENGTH_SHORT).show();
//            mjingdu.setText("经度:" + result.latitude);
//            mWeidu.setText("纬度:" + result.longitude);
//            TextView tv = (TextView) getActivity().findViewById(R.id.syncLocationTv);
            gps_text.setText("定位成功");
            gps_text.setTextColor(Color.WHITE);
        } else if (!mIsGpsSuccess) {
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

    private void initTime() {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int day = c.get(Calendar.DATE);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        maintenancerequisition_jcsjstart_ev.setText(getTime(year, month, day));
        maintenancerequisition_jcsjstart_ev.setKeyListener(null);
        maintenancerequisition_jcsjstart_ev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker(maintenancerequisition_jcsjstart_ev.getText().toString(), true);
            }
        });
        maintenancerequisition_jcsjend_ev.setText(getTime(year, month, day));
        maintenancerequisition_jcsjend_ev.setKeyListener(null);
        maintenancerequisition_jcsjend_ev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker(maintenancerequisition_jcsjend_ev.getText().toString(), false);
            }
        });
        maintenancerequisition_qfrq_ev.setText(getTime(year, month, day,hour,minute));
        maintenancerequisition_qfrq_ev.setKeyListener(null);
        maintenancerequisition_qfrq_ev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showqfrqDatePicker(maintenancerequisition_qfrq_ev.getText().toString());
            }
        });
    }

    public void showDatePicker(String time, final boolean isStart) {

        Calendar c = DateUtil.strToCalendarLong2(time);

        DatePickerDialog dlg = new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                if (isStart) {
                    maintenancerequisition_jcsjstart_ev.setText(getTime(year, month + 1, day));
                } else {
                    maintenancerequisition_jcsjend_ev.setText(getTime(year, month + 1, day));
                }
            }
        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE));
        dlg.setTitle("日期：");
        dlg.show();

    }

    public void showqfrqDatePicker(String time) {

//        Calendar c = DateUtil.strToCalendarLongall();

//        c.setTime(new SimpleDateFormat("yyyyn年MM月dd日 HH:mm").parse(dateStr));

        DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(
                        this,DateUtil.timeTransform1(maintenancerequisition_qfrq_ev.getText().toString()) , new DateTimePickDialogUtil.ReturnTime() {
                    @Override
                    public void getTime(String time) {
                        maintenancerequisition_qfrq_ev.setText(DateUtil.timeTransform2(time));
                    }
                });

//        DatePickerDialog dlg = new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
//                maintenancerequisition_qfrq_ev.setText(getTime(year, month + 1, day));
//            }
//        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE));
//        dlg.setTitle("日期：");
//        dlg.show();

    }

    public String getTime(int year, int month, int day) {
        return year + "-" + (month <= 9 ? ("0" + month) : month) + "-" + (day <= 9 ? ("0" + day) : day);
    }
    public String getTime(int year, int month, int day,int hour,int min) {
        return year + "-" + (month <= 9 ? ("0" + month) : month) + "-" + (day <= 9 ? ("0" + day) : day)
                +" "+(hour <= 9 ? ("0" + hour) : hour)+":"+(min <= 9 ? ("0" + min) : min);
    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.maintenancerequisition_back:

                back();
                break;
            case R.id.maintenancerequisition_save:
//                toast("保存1");
                saveDialog();
                break;
            case R.id.saveBtn:
//                toast("保存2");
                saveDialog();
                break;
            case R.id.maintenancerequisition_synchronizationwxbm:
//                loadDate();
                String roles = BridgeDetectionApplication.mCurrentUser.getRoles();
                if (roles.contains("zlcjy")) {
                    synchronizationQualityDemand();
                }else{
                    toast("无权限。");
                }


                toast("同步维修部门");
                break;
            case R.id.maintenancelog_synchronizationzlyq:
//                loadDate();
                synchronizationCooperation();
                toast("同步质量要求");
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            back();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void saveDialog() {
        new AlertDialog.Builder(mContext)
                .setTitle("保存数据")
                .setMessage("是否保存当前数据？")
                .setPositiveButton("保存", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (!mIsGpsSuccess) {
                            Toast.makeText(mContext, "正在定位...\n" +
                                    "请您到空旷的地点从新定位，绝就不要在室内", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        String wxbm = maintenancerequisition_wxbm_ev.getText().toString();
                        String zlyq = maintenancerequisition_zlyq_ev.getText().toString();

                        if (TextUtil.isEmptyString(wxbm)) {
                            toast("维修部门不可为空！");
                            return;
                        }
                        if (TextUtil.isEmptyString(zlyq)) {
                            toast("质量要求不可为空！");
                            return;
                        }
                        String xcld = maintenancerequisition_xcld_ev.getText().toString();
                        String jcsjstart = maintenancerequisition_jcsjstart_ev.getText().toString();
                        String jcsjend = maintenancerequisition_jcsjend_ev.getText().toString();
                        String bcsm = maintenancerequisition_bcsm_ev.getText().toString();
                        String qfr = maintenancerequisition_qfr_ev.getText().toString();
                        String qfrq = maintenancerequisition_qfrq_ev.getText().toString();
                        UploadUpkeepnoticeBean bean = new UploadUpkeepnoticeBean();
                        if (id != 0) {
                            bean = uploadUpkeepnoticeBeens.get(0);

                        } else {
                            bean.setGldwId(BridgeDetectionApplication.mCurrentUser.getDefgqId());
                            bean.setGldwName(BridgeDetectionApplication.mCurrentUser.getDefgqName());
                            bean.setTzdzt("1");
                            bean.setUpkeepdiseaseList(upkeepdiseaseListBeens);

                        }
                        bean.setUserid(BridgeDetectionApplication.mCurrentUser.getUserId());
                        bean.setWxlx(wxlxPosition+"");

                        bean.setWxbmid(TextUtil.isEmptyObjects(cooperationBean)?
                                uploadUpkeepnoticeBeens.get(0).getWxbmid():
                                cooperationBean.getId());
                        bean.setWxbmmc(TextUtil.isEmptyObjects(cooperationBean)?
                                        uploadUpkeepnoticeBeens.get(0).getWxbmmc():
                                        cooperationBean.getDwmc());
                        bean.setZlyq(zlyq);
                        bean.setZlyqid(zlyqidContent);
                        bean.setTzld(xcld);
                        bean.setWxks(jcsjstart);
                        bean.setWxjs(jcsjend);
                        bean.setQfry(qfr);
                        bean.setQfrq(qfrq);
                        bean.setBcsm(bcsm);
                        bean.setWeather(strWeather);
                        if(id != 0){
                            uploadUpkeepnoticeDao.update(bean);
//                            uploadUpkeepnoticeDao.updateItemList(upkeepdiseaseListBeens);
                        }else{
                            uploadUpkeepnoticeDao.add(bean);
//                            uploadUpkeepnoticeDao.addItemList(upkeepdiseaseListBeens);
                        }
                        for (UpkeepdiseaseListBean b1:upkeepdiseaseListBeens){
                            b1.setUploadUpkeepnoticeBean(bean);
                            if(id != 0){
                                uploadUpkeepnoticeDao.updateItem(b1);
                            }else{
                                uploadUpkeepnoticeDao.addItem(b1);
                            }
                        }
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


    /**
     * 获取外协单位
     */
    public void synchronizationCooperation() {
        final OnReceivedHttpResponseListener onReceivedHttpResponseListener = new OnReceivedHttpResponseListener() {
            @Override
            public void onRequestSuccess(RequestType type, JSONObject result) {
                Logger.e("aaa", "result.toString()" + result.toString());
                cooperationDao.deleteAll();
                List<CooperationBean> cooperationBeens = JSON.parseArray(result.getString("datas"), CooperationBean.class);

                Logger.e("aaa", "size==" + cooperationBeens.size());

                cooperationDao.addList(cooperationBeens);

                dismissLoading();
                toast("同步质量要求库成功！");
                if (TextUtil.isListEmpty(cooperationBeens)) {
                    ToastUtil.showMessage("暂无质量要求库数据");
                }
            }

            @Override
            public void onRequestFail(RequestType type, String resultCode, String result) {
                Logger.e("aaa", result + "===(" + resultCode + ")");
                Logger.e("aaa", "type===" + type);

                dismissLoading();
                toast("同步质量要求库失败！");
            }
        };

        BackgroundExecutor.execute(new Runnable() {

            @Override
            public void run() {
                cooperationDao.deleteAll();
                List<NameValuePair> list = new ArrayList<NameValuePair>();
                BasicNameValuePair pair = new BasicNameValuePair("userId", BridgeDetectionApplication.mCurrentUser.getUserId());
                list.add(pair);
                pair = new BasicNameValuePair("token", BridgeDetectionApplication.mCurrentUser.getToken());
                list.add(pair);
                new HttpTask(onReceivedHttpResponseListener, RequestType.geteCooperationByUID).executePost(list);


            }
        });

    }

    /**
     * 质量要求
     */
    public void synchronizationQualityDemand() {
        final OnReceivedHttpResponseListener onReceivedHttpResponseListener = new OnReceivedHttpResponseListener() {
            @Override
            public void onRequestSuccess(RequestType type, JSONObject result) {
                Logger.e("aaa", "result.toString()" + result.toString());

                List<QualityDemandBean> qualityDemandBeens = JSON.parseArray(result.getString("datas"), QualityDemandBean.class);

                Logger.e("aaa", "size==" + qualityDemandBeens.size());

                qualityDemandDao.addList(qualityDemandBeens);

                dismissLoading();
                toast("同步外协单位库成功！");
                if (TextUtil.isListEmpty(qualityDemandBeens)) {
                    ToastUtil.showMessage("暂无外协单位库数据");
                }
            }

            @Override
            public void onRequestFail(RequestType type, String resultCode, String result) {
                Logger.e("aaa", result + "===(" + resultCode + ")");
                Logger.e("aaa", "type===" + type);

                dismissLoading();
                toast("同步外协单位库失败！");
            }
        };

        BackgroundExecutor.execute(new Runnable() {

            @Override
            public void run() {
                qualityDemandDao.deleteAll();
                List<NameValuePair> list = new ArrayList<NameValuePair>();
                BasicNameValuePair pair = new BasicNameValuePair("userId", BridgeDetectionApplication.mCurrentUser.getUserId());
                list.add(pair);
                pair = new BasicNameValuePair("token", BridgeDetectionApplication.mCurrentUser.getToken());
                list.add(pair);
                new HttpTask(onReceivedHttpResponseListener, RequestType.geteQualityDemandByUID).executePost(list);


            }
        });

    }

    AlertDialog qualityDemandListDialog;
    List<QualityDemandBean> getQualityDemandBeens;
    String zlyqContent;
    String zlyqidContent;
    public void showQualityDemandListDialog() {
        View dialogView = getLayoutInflater().inflate(R.layout.quality_demand_layout, null);
        ListView dialogListView = (ListView) dialogView.findViewById(R.id.quality_demand_layout_listview);
        Button quality_demand_layout_confirm = (Button) dialogView.findViewById(R.id.quality_demand_layout_confirm);
        QualityDemandListAdapter qualityDemandListAdapter = new QualityDemandListAdapter(mContext);
        qualityDemandListAdapter.setData(qualityDemandBeens);
        dialogListView.setAdapter(qualityDemandListAdapter);
        quality_demand_layout_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getQualityDemandBeens = new ArrayList<QualityDemandBean>();
                StringBuffer sbc = new StringBuffer();
                StringBuffer sbid = new StringBuffer();
                for (QualityDemandBean bean : qualityDemandBeens) {
                    if (bean.isChecked()) {
                        getQualityDemandBeens.add(bean);
                        sbc.append(bean.getYqnr()).append(";");
                        sbid.append(bean.getId()).append(";");
                    }
                }
                if(TextUtil.isEmptyString(sbc.toString())||TextUtil.isEmptyString(sbid.toString())){
                    toast("没有选择任何的选项！");
                    maintenancerequisition_zlyq_ev.setText("");
                }else{
                    zlyqContent = sbc.substring(0, sbc.lastIndexOf(";"));
                    zlyqidContent = sbid.substring(0, sbid.lastIndexOf(";"));
                    maintenancerequisition_zlyq_ev.setText(zlyqContent);
                    Logger.e("aaa", "选择的是==" + zlyqidContent);
                }

                qualityDemandListDialog.dismiss();

            }
        });

        qualityDemandListDialog = new AlertDialog.Builder(mContext)
                .setView(dialogView)
                .show();

//        WindowManager.LayoutParams params = qualityDemandListDialog.getWindow().getAttributes();
//        params.width = DeviceInfoUtil.getScreenHeight(mContext) - 200;
//                params.height = 200 ;

//        qualityDemandListDialog.getWindow().setAttributes(params);
        qualityDemandListDialog.setCanceledOnTouchOutside(true);


//

    }

    AlertDialog cooperationListDialog;
    CooperationBean cooperationBean;
    public void showCooperationListDialog() {
        View dialogView = getLayoutInflater().inflate(R.layout.cooperation_layout, null);
        ListView dialogListView = (ListView) dialogView.findViewById(R.id.cooperation_layout_listview);
        CooperationListAdapter cooperationListAdapter = new CooperationListAdapter(mContext);
        cooperationListAdapter.setData(cooperationBeens);
        dialogListView.setAdapter(cooperationListAdapter);

        cooperationListDialog = new AlertDialog.Builder(mContext)
                .setView(dialogView)
                .show();

        WindowManager.LayoutParams params = cooperationListDialog.getWindow().getAttributes();
        params.width = DeviceInfoUtil.getScreenHeight(mContext) - 200;
//                params.height = 200 ;

        cooperationListDialog.getWindow().setAttributes(params);
        cooperationListDialog.setCanceledOnTouchOutside(true);

        dialogListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {//响应listview中的item的点击事件

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                    long arg3) {
                // TODO Auto-generated method stub
                cooperationBean = cooperationBeens.get(position);
                maintenancerequisition_wxbm_ev.setText(cooperationBean.getDwmc());
                cooperationListDialog.dismiss();
//
            }
        });

    }


}
