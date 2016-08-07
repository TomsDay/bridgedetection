package com.suken.bridgedetection.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.j256.ormlite.dao.CloseableIterator;
import com.j256.ormlite.dao.ForeignCollection;
import com.suken.bridgedetection.BridgeDetectionApplication;
import com.suken.bridgedetection.Constants;
import com.suken.bridgedetection.R;
import com.suken.bridgedetection.adapter.ProjectAcceptanceAdapter;
import com.suken.bridgedetection.adapter.ProjectHorizontalListViewAdapter;
import com.suken.bridgedetection.adapter.TestArrayAdapter;
import com.suken.bridgedetection.bean.IVDesc;
import com.suken.bridgedetection.bean.IVDescDao;
import com.suken.bridgedetection.bean.MaintenanceLogItemBean;
import com.suken.bridgedetection.bean.ProjacceptBean;
import com.suken.bridgedetection.bean.ProjacceptItemBean;
import com.suken.bridgedetection.bean.ProjectAcceptanceBean;
import com.suken.bridgedetection.bean.ProjectAcceptanceDao;
import com.suken.bridgedetection.location.LocationManager;
import com.suken.bridgedetection.location.LocationResult;
import com.suken.bridgedetection.location.OnLocationFinishedListener;
import com.suken.bridgedetection.signname.WriteDialogListener;
import com.suken.bridgedetection.signname.WritePadDialog;
import com.suken.bridgedetection.util.DateUtil;
import com.suken.bridgedetection.util.FileUtils;
import com.suken.bridgedetection.util.Logger;
import com.suken.bridgedetection.util.TextUtil;
import com.suken.bridgedetection.widget.HorizontalListView;
import com.suken.bridgedetection.widget.ListViewForScrollView;
import com.suken.imageditor.ImageditorActivity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 速公路维修保养工程验收单
 */
public class ProjectAcceptanceActivity extends BaseActivity implements OnLocationFinishedListener {
    private Context mContext;
    private EditText projectacceptance_gydw_ev,
            projectacceptance_bh_ev,
            projectacceptance_wxbydw_ev,
            projectacceptance_sgrqs_ev,
            projectacceptance_sgrqe_ev,
            projectacceptance_return_ev;

    ImageView projectacceptance_xsfzr_sign;


    private TextView saveBtn,gps_text;
    private Bitmap mSignBitmap;

    private Spinner projectacceptance_weather_spinner;

    private int mSYear,mSMonth, mSDay,mEYear,mEMonth, mEDay;

    private String[] mStringArrayWeather;
    private ArrayAdapter<String> mArrayWeatherAdapter;
    private String strWeather = "晴";
    private int selsctWeather;
    private Long id;

    private ProjectAcceptanceDao projectAcceptanceDao;

    private List<ProjectAcceptanceBean> projectAcceptanceBeen = new ArrayList<ProjectAcceptanceBean>();
    private List<ProjacceptItemBean> projacceptItemBeen = new ArrayList<ProjacceptItemBean>();


    public static List<IVDesc> ivDescs = new ArrayList<IVDesc>();
    private ProjacceptBean projacceptBean;
    private IVDescDao ivDescDao;
    ProjectAcceptanceBean bean;

    private HorizontalListView mHorizontalListView;
    private ProjectHorizontalListViewAdapter projectHorizontalListViewAdapter;
    private ListViewForScrollView projectacceptance_return_listview;
    private ProjectAcceptanceAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_acceptance);
        mContext = this;
        projectAcceptanceDao = new ProjectAcceptanceDao();
        ivDescDao = new IVDescDao();
        bean = new ProjectAcceptanceBean();
        id = getIntent().getLongExtra("id", 0);
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
        projectacceptance_return_ev = (EditText) findViewById(R.id.projectacceptance_return_ev);
//        projectacceptance_xsfzr_ev = (EditText) findViewById(R.id.projectacceptance_xsfzr_ev);

        projectacceptance_weather_spinner = (Spinner) findViewById(R.id.projectacceptance_weather_spinner);
        projectacceptance_xsfzr_sign = (ImageView) findViewById(R.id.projectacceptance_xsfzr_sign);

        mHorizontalListView = (HorizontalListView) findViewById(R.id.HorizontalListView);
        projectHorizontalListViewAdapter = new ProjectHorizontalListViewAdapter(ProjectAcceptanceActivity.this);

        projectacceptance_return_listview = (ListViewForScrollView) findViewById(R.id.projectacceptance_return_listview);

        mHorizontalListView.setAdapter(projectHorizontalListViewAdapter);
        gps_text = (TextView) findViewById(R.id.gps_text);
        gps_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(gps_text.getText().toString().equals("定位失败，点击从新定位")){
                    LocationManager.getInstance().syncLocation(ProjectAcceptanceActivity.this);
                }
            }
        });

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
                if(ivDescs.size() >= 5){
                    toast("您当前最多可以有5张签名，请您删除其中一张，再进行签名！");
                    return;
                }
                WritePadDialog mWritePadDialog = new WritePadDialog(
                        ProjectAcceptanceActivity.this, new WriteDialogListener() {

                    @Override
                    public void onPaintDone(Object object) {
                        mSignBitmap = (Bitmap) object;
                        createSignFile();
//                        projectacceptance_xsfzr_sign.setImageBitmap(mSignBitmap);

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
                ivDescs = ivDescDao.getImageProjectAcceptanceBeanByUserId(bean.getId());

                projectHorizontalListViewAdapter.setList(ivDescs);
                projectHorizontalListViewAdapter.notifyDataSetChanged();

                projectacceptance_gydw_ev.setText(bean.getGydwName());
                projectacceptance_bh_ev.setText(bean.getYhtzdno());
                projectacceptance_wxbydw_ev.setText(bean.getSgdwmc());
                projectacceptance_sgrqs_ev.setText(bean.getSgks());
                projectacceptance_sgrqe_ev.setText(bean.getSgjs());
                projectacceptance_return_ev.setText(bean.getYsjg());
//                projectacceptance_xsfzr_ev.setText(bean.getYsrq());
                strWeather = bean.getWeather();
                for (int i = 0; i < mStringArrayWeather.length; i++) {
                    if(mStringArrayWeather[i].equals(strWeather)){
                        selsctWeather = i;
                        break;
                    }
                }
                projectacceptance_weather_spinner.setSelection(selsctWeather);
                ForeignCollection<ProjacceptItemBean> orders = bean.getProjacceptItemBeen();
                CloseableIterator<ProjacceptItemBean> iterator = orders.closeableIterator();
                try {
                    while (iterator.hasNext()) {
                        ProjacceptItemBean b = iterator.next();

                        List<IVDesc> imageDesc = ivDescDao.getImageProjacceptItemBeanByUserId(b.getIds());
                        b.setmImages(imageDesc);

                        List<IVDesc> videoDesc = ivDescDao.getVideoProjacceptItemBeanByUserId(b.getIds());
                        b.setmVideo(videoDesc);

                        projacceptItemBeen.add(b);
                        Logger.e("aaa", b.toString());
                    }
                } finally {
                    try {
                        iterator.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }



            }else{
                setData();
            }

        }else{
            setData();
        }

        mAdapter = new ProjectAcceptanceAdapter(ProjectAcceptanceActivity.this);
        mAdapter.setData(projacceptItemBeen);
        projectacceptance_return_listview.setAdapter(mAdapter);

    }
    public void setData(){

        projectacceptance_gydw_ev.setText(projacceptBean.getGydwName());
        projectacceptance_bh_ev.setText(projacceptBean.getBno());
        projectacceptance_wxbydw_ev.setText(projacceptBean.getSgdwmc());
        projectacceptance_sgrqs_ev.setText(projacceptBean.getSgks());
        projectacceptance_sgrqe_ev.setText(projacceptBean.getSgjs());
        projacceptItemBeen = projacceptBean.getProjacceptDetailList();

    }
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.projectacceptance_back:
                back();
                break;
            case R.id.projectacceptance_save:
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

    boolean mIsGpsSuccess = false;
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
                        if(!mIsGpsSuccess){
                            Toast.makeText(mContext, "正在定位...\n" +
                                    "请您到空旷的地点从新定位，绝就不要在室内", Toast.LENGTH_SHORT).show();
                            return;
                        }


                        String gydw = projectacceptance_gydw_ev.getText().toString();
                        String bh = projectacceptance_bh_ev.getText().toString();
                        String wxbydw = projectacceptance_wxbydw_ev.getText().toString();
                        String sgrqs = projectacceptance_sgrqs_ev.getText().toString();
                        String sgrqe = projectacceptance_sgrqe_ev.getText().toString();
                        String returnContent = projectacceptance_return_ev.getText().toString();
//                        String xsfzr = projectacceptance_xsfzr_ev.getText().toString();





                        bean.setGydwName(gydw);
                        bean.setBno(bh);
                        bean.setWeather(strWeather);
                        bean.setSgdwmc(wxbydw);
                        bean.setSgks(sgrqs);
                        bean.setSgjs(sgrqe);
//                        bean.setQrzs(content);
                        bean.setYsjg(returnContent);
                        bean.setYsrq(DateUtil.getDateEndDay2());
                        bean.setTpjd(latitude+"");
                        bean.setTpwd(longitude+"");
                        if (id != 0) {
                            bean.setId(id);
                        }else{
                            //确认添加的数据
                            bean.setIds(projacceptBean.getId());
                            bean.setUpdateBy(projacceptBean.getUpdateBy());
                            bean.setUpdatetime(projacceptBean.getUpdatetime());
                            bean.setUpdator(projacceptBean.getUpdator());
                            bean.setVersionno(projacceptBean.getVersionno());
                            bean.setCreator(projacceptBean.getCreator());
                            bean.setCreateBy(projacceptBean.getCreateBy());
                            bean.setCreatetime(projacceptBean.getCreatetime());
                            bean.setGydwId(projacceptBean.getGydwId());
                            bean.setYhtzid(projacceptBean.getYhtzid());
                            bean.setYhtzdno(projacceptBean.getYhtzdno());
                            bean.setSgdwid(projacceptBean.getSgdwid());
                            bean.setSgfzry(projacceptBean.getSgfzry());
                            bean.setSgfzdate(projacceptBean.getSgfzdate());
                            bean.setYsry(BridgeDetectionApplication.mCurrentUser.getUserName());
                        }


                        Logger.e("aaa","maintenanceTableBean.toString()===="+bean.toString());
                        if (id != 0) {
                            projectAcceptanceDao.update(bean);
                        }else{
                            projectAcceptanceDao.add(bean);
                        }

//                        List<IVDesc> imagesDescList = bean.getmImages();
                        for(int q = 0; q < ivDescs.size(); q++){
                            IVDesc imageDesc = ivDescs.get(q);
                            imageDesc.setImageProjectAcceptanceBean(bean);
                            if (imageDesc.getId() != 0) {
                                ivDescDao.update(imageDesc);
                            }else {
                                ivDescDao.add(imageDesc);
                            }
                        }

                        projacceptItemBeen = mAdapter.getData();
                        for (int j = 0; j < projacceptItemBeen.size(); j++) {
                            ProjacceptItemBean itemBean = projacceptItemBeen.get(j);
                            if (itemBean.getTpjd() != null) {
                                itemBean.setTpjd(latitude+"");
                                itemBean.setTpwd(longitude+"");
                            }
                            String fx = itemBean.getFx();
                            itemBean.setFx(fx != null ? fx : "上行内侧");

                            itemBean.setProjectAcceptanceBean(bean);
                            if (itemBean.getIds() != 0) {
                                projectAcceptanceDao.updateItem(itemBean);
                            }else {
                                projectAcceptanceDao.addItem(itemBean);
                            }
                            List<IVDesc> imagesDescList = itemBean.getmImages();
                            List<IVDesc> videoDescList = itemBean.getmVideo();
                            for(int q = 0; q < imagesDescList.size(); q++){
                                IVDesc imageDesc = imagesDescList.get(q);
                                imageDesc.setImageProjacceptItemBean(itemBean);
                                if (imageDesc.getId() != 0) {
                                    ivDescDao.update(imageDesc);
                                }else {
                                    ivDescDao.add(imageDesc);
                                }
                            }
                            for(int w = 0; w < videoDescList.size(); w++){
                                IVDesc videoDesc = videoDescList.get(w);
                                videoDesc.setVideoProjacceptItemBean(itemBean);
                                if (videoDesc.getId() != 0) {
                                    ivDescDao.update(videoDesc);
                                }else {
                                    ivDescDao.add(videoDesc);
                                }
                            }
                        }

                        Logger.e("aaa", "=======11111====="+projectAcceptanceDao.queryAll().toString());
                        Logger.e("aaa", "=======11111====="+projectAcceptanceDao.queryItemAll().toString());
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
            ivDescs.add(ivDesc);
            projectHorizontalListViewAdapter.setList(ivDescs);
            projectHorizontalListViewAdapter.notifyDataSetChanged();

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

    private int mPosition;
    private Uri mOutPutFileUri = null;
    File mPlayerFile;
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
        if (requestCode == Constants.REQUEST_CODE_CAMERA ) {
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
        if(requestCode == 222){
            projectHorizontalListViewAdapter.setList(ivDescs);
            projectHorizontalListViewAdapter.notifyDataSetChanged();
            return;
        }
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
            projacceptItemBeen.get(mPosition).getmImages().add(desc);
            mAdapter.setData(projacceptItemBeen);
            mAdapter.notifyDataSetChanged();

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
            catch(Exception e) {;

            }




            projacceptItemBeen.get(mPosition).getmVideo().add(desc);
            mAdapter.setData(projacceptItemBeen);
            mAdapter.notifyDataSetChanged();
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

}
