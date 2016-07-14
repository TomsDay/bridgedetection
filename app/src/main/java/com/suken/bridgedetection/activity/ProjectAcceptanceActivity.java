package com.suken.bridgedetection.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.suken.bridgedetection.BridgeDetectionApplication;
import com.suken.bridgedetection.R;
import com.suken.bridgedetection.adapter.ProjectAcceptanceListAdapter;
import com.suken.bridgedetection.adapter.TestArrayAdapter;
import com.suken.bridgedetection.bean.IVDesc;
import com.suken.bridgedetection.bean.IVDescDao;
import com.suken.bridgedetection.bean.MaintenanceTableItemBean;
import com.suken.bridgedetection.bean.ProjacceptBean;
import com.suken.bridgedetection.bean.ProjectAcceptanceBean;
import com.suken.bridgedetection.bean.ProjectAcceptanceDao;
import com.suken.bridgedetection.location.LocationManager;
import com.suken.bridgedetection.location.LocationResult;
import com.suken.bridgedetection.location.OnLocationFinishedListener;
import com.suken.bridgedetection.signname.WriteDialogListener;
import com.suken.bridgedetection.signname.WritePadDialog;
import com.suken.bridgedetection.util.Logger;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

/**
 * 速公路维修保养工程验收单
 */
public class ProjectAcceptanceActivity extends BaseActivity implements OnLocationFinishedListener {
    private ProjectAcceptanceListAdapter mAdapter;
    private Context mContext;
    private EditText projectacceptance_gydw_ev,
            projectacceptance_bh_ev,
            projectacceptance_wxbydw_ev,
            projectacceptance_sgrqs_ev,
            projectacceptance_sgrqe_ev,
            projectacceptance_content_ev,
            projectacceptance_return_ev,
            projectacceptance_xsfzr_ev;

    ImageView projectacceptance_xsfzr_sign;

    private TextView saveBtn;
    private Bitmap mSignBitmap;

    private Spinner projectacceptance_weather_spinner;

    private int mSYear,mSMonth, mSDay,mEYear,mEMonth, mEDay;

    private String[] mStringArrayWeather;
    private ArrayAdapter<String> mArrayWeatherAdapter;
    private String strWeather = "晴";
    private int selsctWeather;
    private int id;

    private ProjectAcceptanceDao projectAcceptanceDao;
    private List<ProjectAcceptanceBean> projectAcceptanceBeen = new ArrayList<ProjectAcceptanceBean>();
    private ProjacceptBean projacceptBean;
    private IVDescDao ivDescDao;
    ProjectAcceptanceBean bean;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_acceptance);
        mContext = this;
        projectAcceptanceDao = new ProjectAcceptanceDao();
        ivDescDao = new IVDescDao();
        bean = new ProjectAcceptanceBean();
        id = getIntent().getIntExtra("id", 0);
        projacceptBean = (ProjacceptBean) getIntent().getSerializableExtra("bean");
        LocationManager.getInstance().syncLocation(this);
        initView();
    }

    private void initView() {
        projectacceptance_gydw_ev = (EditText) findViewById(R.id.projectacceptance_gydw_ev);
        projectacceptance_bh_ev = (EditText) findViewById(R.id.projectacceptance_bh_ev);
        projectacceptance_wxbydw_ev = (EditText) findViewById(R.id.projectacceptance_wxbydw_ev);
        projectacceptance_sgrqs_ev = (EditText) findViewById(R.id.projectacceptance_sgrqs_ev);
        projectacceptance_sgrqe_ev = (EditText) findViewById(R.id.projectacceptance_sgrqe_ev);
        projectacceptance_content_ev = (EditText) findViewById(R.id.projectacceptance_content_ev);
        projectacceptance_return_ev = (EditText) findViewById(R.id.projectacceptance_return_ev);
        projectacceptance_xsfzr_ev = (EditText) findViewById(R.id.projectacceptance_xsfzr_ev);

        projectacceptance_weather_spinner = (Spinner) findViewById(R.id.projectacceptance_weather_spinner);
        projectacceptance_xsfzr_sign = (ImageView) findViewById(R.id.projectacceptance_xsfzr_sign);

        projectacceptance_gydw_ev.setKeyListener(null);
        projectacceptance_bh_ev.setKeyListener(null);
        projectacceptance_wxbydw_ev.setKeyListener(null);

        saveBtn = (TextView) findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveDialog();
            }
        });

        projectacceptance_xsfzr_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WritePadDialog mWritePadDialog = new WritePadDialog(
                        ProjectAcceptanceActivity.this, new WriteDialogListener() {

                    @Override
                    public void onPaintDone(Object object) {
                        mSignBitmap = (Bitmap) object;
                        createSignFile();
                        projectacceptance_xsfzr_sign.setImageBitmap(mSignBitmap);
//                        mTVSign.setVisibility(View.GONE);
                    }
                });

                mWritePadDialog.show();
            }
        });

        initTime();
        initSpinner();

        if (id != 0) {
            projectAcceptanceBeen = (ArrayList<ProjectAcceptanceBean>)  projectAcceptanceDao.queryByID(id);
            Logger.e("aaa","maintenanceTableBeanList++"+projectAcceptanceBeen.toString());
            if(projectAcceptanceBeen.size()>0){
                ProjectAcceptanceBean bean = projectAcceptanceBeen.get(0);

                projectacceptance_gydw_ev.setText(bean.getGydwName());
                projectacceptance_bh_ev.setText(bean.getBno());
                projectacceptance_wxbydw_ev.setText(bean.getSgdwmc());
                projectacceptance_sgrqs_ev.setText(bean.getSgks());
                projectacceptance_sgrqe_ev.setText(bean.getSgjs());
                projectacceptance_content_ev.setText(bean.getQrzs());
                projectacceptance_return_ev.setText(bean.getYsjg());
                projectacceptance_xsfzr_ev.setText(bean.getYsrq());

                strWeather = bean.getWeather();
                for (int i = 0; i < mStringArrayWeather.length; i++) {
                    if(mStringArrayWeather[i].equals(strWeather)){
                        selsctWeather = i;
                        break;
                    }
                }
                projectacceptance_weather_spinner.setSelection(selsctWeather);

            }else{
                setData();
            }

        }else{
            setData();
        }




    }
    public void setData(){
        projectacceptance_gydw_ev.setText(projacceptBean.getGydwName());
        projectacceptance_bh_ev.setText(projacceptBean.getBno());
        projectacceptance_wxbydw_ev.setText(projacceptBean.getSgdwmc());
        projectacceptance_sgrqs_ev.setText(projacceptBean.getSgks());
        projectacceptance_sgrqe_ev.setText(projacceptBean.getSgjs());
        projectacceptance_content_ev.setText(projacceptBean.getProjacceptDetailList().toString());
        projectacceptance_xsfzr_ev.setText(projacceptBean.getYsrq());


    }
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.projectacceptance_back:
                finish();
                break;
            case R.id.projectacceptance_save:
                saveDialog();
                break;

        }

    }
    private void initSpinner() {
        projectacceptance_weather_spinner = (Spinner) findViewById(R.id.projectacceptance_weather_spinner);
        mStringArrayWeather = getResources().getStringArray(R.array.spinnerWeather);
        mArrayWeatherAdapter = new TestArrayAdapter(mContext, mStringArrayWeather);
        //设置下拉列表风格(这句不些也行)
        //mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        projectacceptance_weather_spinner.setAdapter(mArrayWeatherAdapter);
        //监听Item选中事件
        projectacceptance_weather_spinner.setOnItemSelectedListener(new ItemSelectedListenerImpl());

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
//            tv.setText("定位成功");
//            tv.setTextColor(Color.WHITE);
        } else if(!mIsGpsSuccess){
            Toast.makeText(this, "定位失败！请您到空旷的地点从新定位，绝就不要在室内！", Toast.LENGTH_LONG).show();
//            TextView tv = (TextView) getActivity().findViewById(R.id.syncLocationTv);
//            tv.setText("定位失败");
//            tv.setTextColor(Color.RED);
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
        mSYear = c.get(Calendar.YEAR);
        mSMonth = c.get(Calendar.MONTH)+1;
        mSDay = c.get(Calendar.DATE);
        mEYear = c.get(Calendar.YEAR);
        mEMonth = c.get(Calendar.MONTH)+1;
        mEDay = c.get(Calendar.DATE);
        projectacceptance_sgrqs_ev.setText(getTime(mSYear, mSMonth, mSDay));
        projectacceptance_sgrqs_ev.setKeyListener(null);
        projectacceptance_sgrqs_ev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker(true);
            }
        });
        projectacceptance_sgrqe_ev.setText(getTime(mEYear, mEMonth, mEDay));
        projectacceptance_sgrqe_ev.setKeyListener(null);
        projectacceptance_sgrqe_ev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker(false);
            }
        });
    }
    public String getTime(int year,int month,int day){
        return  year + "-" + (month <= 9 ? ("0" + month) : month) + "-" + (day <= 9 ? ("0" + day) : day);
    }
    public void showDatePicker(final boolean isSorE){
        DatePickerDialog dlg = new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                if (isSorE) {
                    mSYear = year;
                    mSMonth = month + 1;
                    mSDay = day;
                    projectacceptance_sgrqs_ev.setText(getTime(mSYear, mSMonth, mSDay));
                } else {
                    mEYear = year;
                    mEMonth = month + 1;
                    mEDay = day;
                    projectacceptance_sgrqe_ev.setText(getTime(mEYear, mEMonth, mEDay));
                }
            }
        }, isSorE ? mSYear : mEYear, isSorE ? (mSMonth - 1) : (mEMonth - 1), isSorE ? mSDay : mEDay);
        dlg.setTitle(isSorE ?"开始日期：":"结束日期：");
        dlg.show();

    }

    public void saveDialog(){
        new AlertDialog.Builder(mContext)
                .setTitle("保存数据")
                .setMessage("是否保存当前数据？")
                .setPositiveButton("保存", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(mIsGpsSuccess){
                            Toast.makeText(mContext, "正在定位...\n" +
                                    "请您到空旷的地点从新定位，绝就不要在室内", Toast.LENGTH_SHORT).show();
                            return;
                        }


                        String gydw = projectacceptance_gydw_ev.getText().toString();
                        String bh = projectacceptance_bh_ev.getText().toString();
                        String wxbydw = projectacceptance_wxbydw_ev.getText().toString();
                        String sgrqs = projectacceptance_sgrqs_ev.getText().toString();
                        String sgrqe = projectacceptance_sgrqe_ev.getText().toString();
                        String content = projectacceptance_content_ev.getText().toString();
                        String returnContent = projectacceptance_return_ev.getText().toString();
                        String xsfzr = projectacceptance_xsfzr_ev.getText().toString();


                        bean.setGydwName(gydw);
                        bean.setBno(bh);
                        bean.setWeather(strWeather);
                        bean.setSgdwmc(wxbydw);
                        bean.setSgks(sgrqs);
                        bean.setSgjs(sgrqe);
                        bean.setQrzs(content);
                        bean.setYsjg(returnContent);
                        bean.setYsrq(xsfzr);
                        bean.setTpjd(latitude+"");
                        bean.setTpwd(longitude+"");
                        if (id != 0) {
                            bean.setId(id);
                        }


                        Logger.e("aaa","maintenanceTableBean.toString()===="+bean.toString());
                        if (id != 0) {
                            projectAcceptanceDao.update(bean);
                        }else{
                            projectAcceptanceDao.add(bean);
                        }

                        List<IVDesc> imagesDescList = bean.getmImages();
                        for(int q = 0; q < imagesDescList.size(); q++){
                            IVDesc imageDesc = imagesDescList.get(q);
                            imageDesc.setImageProjectAcceptanceBean(bean);
                            if (imageDesc.getId() != 0) {
                                ivDescDao.update(imageDesc);
                            }else {
                                ivDescDao.add(imageDesc);
                            }
                        }


                        Logger.e("aaa", "=======11111====="+projectAcceptanceDao.queryAll().toString());
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

    /**保存签名图片数据*/
    private void createSignFile() {
        ByteArrayOutputStream baos = null;
        FileOutputStream fos = null;
        String path = null;
        File file = null;
        String name = null;
        try {
            name = "signature-" + System.currentTimeMillis() + "-image.jpg";
            path = Environment.getExternalStorageDirectory() + File.separator + getPackageName() + File.separator +
                    name;

            Log.i("aaa","path ====== "+path);
            file = new File(path);
            fos = new FileOutputStream(file);
            baos = new ByteArrayOutputStream();
            //如果设置成Bitmap.compress(CompressFormat.JPEG, 100, fos) 图片的背景都是黑色的
            mSignBitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
            Log.i("aaa","mSignBitmap ===== "+mSignBitmap.getWidth()+"mSignBitmap h ==="+mSignBitmap.getHeight());
            byte[] b = baos.toByteArray();
            if (b != null) {
                fos.write(b);
            }
            IVDesc ivDesc = new IVDesc();
            ivDesc.setName(name);
            ivDesc.setPath(path);
            bean.getmImages().add(ivDesc);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {


            try {
                if (fos != null) {
                    fos.close();
                }
                if (baos != null) {
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
