package com.suken.bridgedetection.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.googlecode.androidannotations.api.BackgroundExecutor;
import com.j256.ormlite.dao.CloseableIterator;
import com.j256.ormlite.dao.ForeignCollection;
import com.suken.bridgedetection.BridgeDetectionApplication;
import com.suken.bridgedetection.Constants;
import com.suken.bridgedetection.R;
import com.suken.bridgedetection.RequestType;
import com.suken.bridgedetection.adapter.TestArrayAdapter;
import com.suken.bridgedetection.bean.MaintenanceDiseaseBean;
import com.suken.bridgedetection.bean.MaintenanceLogBean;
import com.suken.bridgedetection.bean.MaintenanceLogItemBean;
import com.suken.bridgedetection.bean.MaintenanceOfOrderBean;
import com.suken.bridgedetection.bean.MaintenanceOfOrderDao;
import com.suken.bridgedetection.bean.MaintenanceTableBean;
import com.suken.bridgedetection.bean.MaintenanceTableItemBean;
import com.suken.bridgedetection.http.HttpTask;
import com.suken.bridgedetection.http.OnReceivedHttpResponseListener;
import com.suken.bridgedetection.util.FileUtils;
import com.suken.bridgedetection.util.Logger;
import com.suken.imageditor.ImageditorActivity;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 高速公路施工安全检查表
 */
public class MaintenanceOfOrderActivity extends Activity {

    private EditText maintenanceoforder_gydw_ev,
            maintenanceoforder_checkDate_ev,
            maintenanceoforder_weather_ev,
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
            maintenanceoforder_aqzgly_videoNum;

    private int mYear,mMonth, mDay;

    private List<MaintenanceOfOrderBean> maintenanceOfOrderBeen = new ArrayList<MaintenanceOfOrderBean>();
    private MaintenanceOfOrderBean maintenanceOfOrderBean;
    private MaintenanceOfOrderDao maintenanceOfOrderDao;

    private String[] mStringArrayWeather;
    private ArrayAdapter<String> mArrayWeatherAdapter;
    private String strWeather = "晴";
    private int id;


    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance_of_order);
        mContext = this;
        maintenanceOfOrderDao = new MaintenanceOfOrderDao();
        initView();
    }

    private void initView() {

        maintenanceoforder_gydw_ev = (EditText) findViewById(R.id.maintenanceoforder_gydw_ev);
        maintenanceoforder_checkDate_ev = (EditText) findViewById(R.id.maintenanceoforder_checkDate_ev);
        maintenanceoforder_weather_ev = (EditText) findViewById(R.id.maintenanceoforder_weather_ev);
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
        maintenanceoforder_bzbf_xiangji = (ImageView) findViewById(R.id.maintenanceoforder_bzbf_xiangji);
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
        initTime();
        initSpinner();

        if (id != 0) {
            maintenanceOfOrderBeen = (ArrayList<MaintenanceOfOrderBean>)  maintenanceOfOrderDao.queryByID(id);
            Logger.e("aaa","maintenanceTableBeanList++"+maintenanceOfOrderBeen.toString());
            if(maintenanceOfOrderBeen.size()>0){
                MaintenanceOfOrderBean bean = maintenanceOfOrderBeen.get(0);

                maintenanceoforder_gydw_ev.setText(bean.getCustodyUnit());
                maintenanceoforder_checkDate_ev.setText(bean.getCheckDate());
                maintenanceoforder_content_ev.setText(bean.getContent());
                maintenanceoforder_qtqk_ev.setText(bean.getOtherSituations());
                maintenanceoforder_yj_ev.setText(bean.getProcessingOpinion());
                maintenanceoforder_jcr_ev.setText(bean.getCheckPeople());
                maintenanceoforder_jlr_ev.setText(bean.getSecurityAdministrator());

            }else{

            }

        }else{

        }


    }
    private void initTime() {
        Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH)+1;
        mDay = c.get(Calendar.DATE);
        maintenanceoforder_checkDate_ev = (EditText) findViewById(R.id.maintenancelog_data_ev);
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
                finish();
                break;
            case R.id.maintenanceoforder_save:

                break;
        }

    }
    public void saveDialog(){
        new AlertDialog.Builder(mContext)
                .setTitle("保存数据")
                .setMessage("是否保存当前数据？")
                .setPositiveButton("保存", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        String gydw = maintenanceoforder_gydw_ev.getText().toString();
                        String checkDate = maintenanceoforder_checkDate_ev.getText().toString();
                        String content = maintenanceoforder_content_ev.getText().toString();
                        String qtqk = maintenanceoforder_qtqk_ev.getText().toString();
                        String yj = maintenanceoforder_yj_ev.getText().toString();
                        String jcr = maintenanceoforder_jcr_ev.getText().toString();
                        String jlr = maintenanceoforder_jlr_ev.getText().toString();

                        MaintenanceOfOrderBean bean = new MaintenanceOfOrderBean();
                        bean.setCustodyUnit(gydw);
                        bean.setCheckDate(checkDate);
                        bean.setWeather(strWeather);
                        bean.setContent(content);
                        bean.setOtherSituations(qtqk);
                        bean.setProcessingOpinion(yj);
                        bean.setCheckPeople(jcr);
                        bean.setSecurityAdministrator(jlr);
                        if (id != 0) {
                            bean.setId(id);
                        }


                        Logger.e("aaa","maintenanceTableBean.toString()===="+bean.toString());
                        if (id != 0) {
                            maintenanceOfOrderDao.update(bean);
                        }else{
                            maintenanceOfOrderDao.add(bean);
                        }

                        Logger.e("aaa", "=======11111====="+maintenanceOfOrderDao.queryAll().toString());




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
    public void jumpToMedia(int position, int requestCode, MaintenanceTableItemBean.ImageDesc desc) {
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
        try {
            f = new File(new URI(mOutPutFileUri.toString()));
            if (!f.exists()) {
//                f.mkdirs();
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        Logger.e("aaa", "requestCode===" + requestCode);
        if (requestCode == Constants.REQUEST_CODE_CAMERA) {
            MaintenanceTableItemBean.ImageDesc desc = new MaintenanceTableItemBean.ImageDesc();
            desc.name = f.getName();
            desc.path = f.getPath();
            Logger.e("aaa", " desc.name===" + desc.name);
            Logger.e("aaa", " desc.path===" + desc.path);

//            maintenanceTableItemBeen.get(mPosition).getmImages().add(desc);
//            mAdapter.setData(maintenanceTableItemBeen);
//            mAdapter.notifyDataSetChanged();

//            mEditController.updateImg(desc);
        } else if (requestCode == Constants.REQUEST_CODE_EDIT_IMG) {
            // 保存在原先的图片中所以不处理

        } else if (requestCode == Constants.REQUEST_CODE_VIDEO) {
            String str = null;
            MaintenanceTableItemBean.VideoDesc desc = new MaintenanceTableItemBean.VideoDesc();
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
            catch(Exception e) {;

            }




//            maintenanceTableItemBeen.get(mPosition).getmVideo().add(desc);
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

    private void loadDate() {
        final OnReceivedHttpResponseListener onReceivedHttpResponseListener = new OnReceivedHttpResponseListener() {
            @Override
            public void onRequestSuccess(RequestType type, JSONObject result) {
                Logger.e("aaa", "result.toString()" + result.toString());
                JSONArray array = result.getJSONArray("datas");
                String datas = array.getString(0);
                Logger.e("aaa", "datas==" + datas);
                Gson gson = new Gson();
                MaintenanceDiseaseBean bean = gson.fromJson(datas, MaintenanceDiseaseBean.class);
                Logger.e("aaa", bean.toString());
            }

            @Override
            public void onRequestFail(RequestType type, String resultCode, String result) {
                Logger.e("aaa", result + "(" + resultCode + ")");
            }
        };

        BackgroundExecutor.execute(new Runnable() {

            @Override
            public void run() {
                List<NameValuePair> list = new ArrayList<NameValuePair>();
                BasicNameValuePair pair = new BasicNameValuePair("userId", BridgeDetectionApplication.mCurrentUser.getUserId());
                list.add(pair);
                pair = new BasicNameValuePair("token", BridgeDetectionApplication.mCurrentUser.getToken());
                list.add(pair);
                pair = new BasicNameValuePair("did", BridgeDetectionApplication.mDeviceId);
                list.add(pair);
                new HttpTask(onReceivedHttpResponseListener, RequestType.geteDeseaseByUID).executePost(list);
            }
        });
    }


}
