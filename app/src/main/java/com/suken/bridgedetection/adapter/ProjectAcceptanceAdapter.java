package com.suken.bridgedetection.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.suken.bridgedetection.Constants;
import com.suken.bridgedetection.R;
import com.suken.bridgedetection.activity.BaseActivity;
import com.suken.bridgedetection.activity.ProjectAcceptanceActivity;
import com.suken.bridgedetection.bean.ProjacceptBean;
import com.suken.bridgedetection.bean.ProjacceptItemBean;
import com.suken.bridgedetection.bean.ProjectAcceptanceBean;
import com.suken.bridgedetection.location.LocationManager;
import com.suken.bridgedetection.location.LocationResult;
import com.suken.bridgedetection.location.OnLocationFinishedListener;
import com.suken.bridgedetection.util.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/17.
 */
public class ProjectAcceptanceAdapter extends BaseAdapter{
    private ProjectAcceptanceActivity mActivity;
    List<ProjacceptItemBean> projacceptItemBeens = new ArrayList<ProjacceptItemBean>();
    private LayoutInflater inflater;
    public ProjectAcceptanceAdapter(ProjectAcceptanceActivity activity){
        this.mActivity = activity;
        inflater = LayoutInflater.from(mActivity);
    }
    public void setData(List<ProjacceptItemBean> list) {
        this.projacceptItemBeens = list;
    }

    public List<ProjacceptItemBean> getData() {
        return projacceptItemBeens;
    }

    @Override
    public int getCount() {
        return projacceptItemBeens.size();
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
//        if(view == null){
            view = inflater.inflate(R.layout.projaccept_item, null);
            holder = new HolderView(view);
            view.setTag(holder);
//        }else{
//            holder = (HolderView) view.getTag();
//        }

        holder.projaccept_item_tv5.setTag(position);
        holder.projaccept_item_tv5.addTextChangedListener(new Watcher(holder.projaccept_item_tv5));

        ProjacceptItemBean bean = projacceptItemBeens.get(position);
        holder.projaccept_item_tv1.setText(bean.getYhzh()+"");
        holder.projaccept_item_tv2.setText(bean.getFx()+"");
        holder.projaccept_item_tv3.setText(bean.getBhmc()+"");
        holder.projaccept_item_tv4.setText(bean.getDw()+"");
        holder.projaccept_item_tv5.setText(bean.getWxsl()+"");

        holder.projaccept_item_tv6.setText(bean.getClmc()+"");
        holder.projaccept_item_tv7.setText(bean.getClgg()+"");
        holder.projaccept_item_tv8.setText(bean.getClxh()+"");
        holder.projaccept_item_tv9.setText(bean.getCldw()+"");
        holder.projaccept_item_tv10.setText(bean.getClsl()+"");

        String ysjg = bean.getYsjg();
        if(ysjg == null){
            holder.projaccept_item_tv11.setText("相符");
            projacceptItemBeens.get(position).setYsjg("相符");
        }else {
            if(ysjg.length() == 0){
                holder.projaccept_item_tv11.setText("相符");
                projacceptItemBeens.get(position).setYsjg("相符");
            }else{
                holder.projaccept_item_tv11.setText(ysjg);
            }
        }
        holder.img_num.setText(bean.getmImages().size()+"");
        holder.video_num.setText(bean.getmVideo().size()+"");

        setYsjgClick(holder, position);

        holder.img_num.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ClickImagePositon = position;
                mActivity.jumpToMedia(position, Constants.REQUEST_CODE_CAPTURE, null);
                LocationManager.getInstance().syncLocation(new OnLocationFinishedListener() {
                    @Override
                    public void onLocationFinished(LocationResult result) {
                        if(mActivity == null || ((BaseActivity)mActivity).isDestroyed() || mActivity.isFinishing()){
                            return;
                        }
                        boolean mIsGpsSuccess = false;
                        if (result.isSuccess) {
//                            mIsGpsSuccess = true;
                            projacceptItemBeens.get(position).setTpjd(result.longitude + "");
                            projacceptItemBeens.get(position).setTpwd(result.latitude + "");
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
        holder.video_num.setOnClickListener(new View.OnClickListener() {
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
                            projacceptItemBeens.get(position).setTpjd(result.longitude + "");
                            projacceptItemBeens.get(position).setTpwd(result.latitude + "");
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

                case R.id.projaccept_item_tv5:
                    Logger.e("aaa","diseaseName_edit="+content+"=====position"+position);
                    projacceptItemBeens.get(position).setWxsl(content!=null&&!"".equals(content)?content:"");
                    break;
            }
        }
    }



    public void setYsjgClick(final HolderView holder,final int position){
        holder.projaccept_item_tv11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String[] names = { "相符", "不符" };
                new AlertDialog.Builder(mActivity,R.style.NOmengceng_dialog)
                        .setItems(names, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                Logger.e("aaa", "which++" + which);
                                String str = names[which];
                                projacceptItemBeens.get(position).setYsjg(str);
                                holder.projaccept_item_tv11.setText(str);


                            }
                        })
                        .show();
            }
        });
    }
    class HolderView {
        private TextView projaccept_item_tv1,
                projaccept_item_tv2,
                projaccept_item_tv3,
                projaccept_item_tv4,
                projaccept_item_tv6,
                projaccept_item_tv7,
                projaccept_item_tv8,
                projaccept_item_tv9,
                projaccept_item_tv10,
                projaccept_item_tv11,
                img_num,
                video_num;

        private EditText projaccept_item_tv5;

//        private ImageView xiangji, video;

        private View projaccept_item_line5;

        public HolderView(View v){
            projaccept_item_tv1 = (TextView) v.findViewById(R.id.projaccept_item_tv1);
            projaccept_item_tv2 = (TextView) v.findViewById(R.id.projaccept_item_tv2);
            projaccept_item_tv3 = (TextView) v.findViewById(R.id.projaccept_item_tv3);
            projaccept_item_tv4 = (TextView) v.findViewById(R.id.projaccept_item_tv4);
            projaccept_item_tv6 = (TextView) v.findViewById(R.id.projaccept_item_tv6);
            projaccept_item_tv7 = (TextView) v.findViewById(R.id.projaccept_item_tv7);
            projaccept_item_tv8 = (TextView) v.findViewById(R.id.projaccept_item_tv8);
            projaccept_item_tv9 = (TextView) v.findViewById(R.id.projaccept_item_tv9);
            projaccept_item_tv10 = (TextView) v.findViewById(R.id.projaccept_item_tv10);
            projaccept_item_tv11 = (TextView) v.findViewById(R.id.projaccept_item_tv11);

            img_num = (TextView) v.findViewById(R.id.img_num);
            video_num = (TextView) v.findViewById(R.id.video_num);

            projaccept_item_tv5 = (EditText) v.findViewById(R.id.projaccept_item_tv5);

//            xiangji = (ImageView) v.findViewById(R.id.xiangji);
//            video = (ImageView) v.findViewById(R.id.video);

        }
    }
}
