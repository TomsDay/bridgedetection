package com.suken.bridgedetection.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.suken.bridgedetection.Constants;
import com.suken.bridgedetection.R;
import com.suken.bridgedetection.activity.BaseActivity;
import com.suken.bridgedetection.activity.QualityInspectionActivity;
import com.suken.bridgedetection.bean.ProjacceptBean;
import com.suken.bridgedetection.bean.ProjacceptItemBean;
import com.suken.bridgedetection.location.LocationManager;
import com.suken.bridgedetection.location.LocationResult;
import com.suken.bridgedetection.location.OnLocationFinishedListener;
import com.suken.bridgedetection.util.Logger;
import com.suken.bridgedetection.util.TextUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/9/20.
 */
public class QualityInspectionExpandableListAdapter extends BaseExpandableListAdapter {

    private QualityInspectionActivity mActivity = null;
    //    public String[] groups = { "单价", "颜色","单价", "颜色" };
//    public String[][] children = { { "一块", "二块", "三块" },{ "黄色", "绿色", "白色" },{ "一块", "二块", "三块" },{ "黄色", "绿色", "白色" } };
    private List<ProjacceptBean> projacceptBeens = new ArrayList<ProjacceptBean>();
    public static HashMap<String, Boolean> statusHashMap;
    //    private CheckBox childBox;
    public QualityInspectionExpandableListAdapter(QualityInspectionActivity context) {
        this.mActivity = context;
        statusHashMap = new HashMap<String, Boolean>();
        // 初始时,让所有的子选项均未被选中
//        for (int i = 0; i < children.length; i++) {
//            for (int a = 0; a < children[i].length; a++) {
//                statusHashMap.put(children[i][a], false);
//            }
//        }
    }
    public void setData(List<ProjacceptBean> list){
        projacceptBeens = list;
    }
    public List<ProjacceptBean> getData(){
        return projacceptBeens;
    }

    // 取得指定的子项
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return projacceptBeens.get(groupPosition);
    }

    // 取得子项ID
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    //点击事件发生后:先执行事件监听,然后调用此getChildView()
    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {// 返回子项组件
        ChildHolderView holderView = null;
        if (convertView == null) {
            LayoutInflater inflater =
                    (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.quality_inspection_childitem, null);
            holderView = new ChildHolderView(convertView);
            convertView.setTag(holderView);
        }else{
            holderView = (ChildHolderView) convertView.getTag();
        }
        ProjacceptBean bean = (ProjacceptBean) getChild(groupPosition, childPosition);
        ProjacceptItemBean projacceptItemBean = bean.getProjacceptDetailList().get(childPosition);

        holderView.quality_inspection_childitem_row1.setText(projacceptItemBean.getYhzh()+projacceptItemBean.getFx());
        holderView.quality_inspection_childitem_row2.setText(projacceptItemBean.getBhmc());
        holderView.quality_inspection_childitem_row3.setText(bean.getSgdwmc());
        holderView.quality_inspection_childitem_row4.setText(projacceptItemBean.getWxsl()+projacceptItemBean.getDw());
        holderView.quality_inspection_childitem_row5.setText(bean.getSgks()+" - "+bean.getSgjs());
        holderView.quality_inspection_childitem_row6.setText(bean.getYsrq());
        String ysjg = projacceptItemBean.getYsjg();

        if(!TextUtil.isEmptyString(ysjg)&&"相符".equals(ysjg)){
            projacceptBeens.get(groupPosition).getProjacceptDetailList().get(childPosition).setYsjg("合格");
            holderView.quality_inspection_childitem_row7.setText("合格");
        }else{
            holderView.quality_inspection_childitem_row7.setText(projacceptItemBean.getYsjg());
        }
        holderView.quality_inspection_childitem_row8.setText(projacceptItemBean.getmImages().size()+"");
        holderView.quality_inspection_childitem_row9.setText(projacceptItemBean.getmVideo().size()+"");

        holderView.quality_inspection_childitem_row8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ClickImagePositon = position;
                mActivity.jumpToMedia(groupPosition,childPosition, Constants.REQUEST_CODE_CAPTURE, null);
                LocationManager.getInstance().syncLocation(new OnLocationFinishedListener() {
                    @Override
                    public void onLocationFinished(LocationResult result) {
                        if(mActivity == null || ((BaseActivity)mActivity).isDestroyed() || mActivity.isFinishing()){
                            return;
                        }
                        boolean mIsGpsSuccess = false;
                        if (result.isSuccess) {
//                            mIsGpsSuccess = true;
                            projacceptBeens.get(groupPosition).getProjacceptDetailList().get(childPosition).setTpjd(result.longitude + "");
                            projacceptBeens.get(groupPosition).getProjacceptDetailList().get(childPosition).setTpwd(result.latitude + "");
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
        holderView.quality_inspection_childitem_row9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mActivity.jumpToMedia(groupPosition,childPosition, Constants.REQUEST_CODE_VIDEO, null);
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
                            projacceptBeens.get(groupPosition).getProjacceptDetailList().get(childPosition).setTpjd(result.longitude + "");
                            projacceptBeens.get(groupPosition).getProjacceptDetailList().get(childPosition).setTpwd(result.latitude + "");
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

        setYsjgClick(holderView, groupPosition, childPosition);
        return convertView;
    }
    public void setYsjgClick(final ChildHolderView holder, final int groupPosition, final int childPosition){
        holder.quality_inspection_childitem_row7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String[] names = { "质量合格，数量相符", "不合格" };
                new AlertDialog.Builder(mActivity,R.style.NOmengceng_dialog)
                        .setItems(names, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                Logger.e("aaa", "which++" + which);
                                String str = names[which];
                                projacceptBeens.get(groupPosition).getProjacceptDetailList().get(childPosition).setYsjg(str);
                                holder.quality_inspection_childitem_row7.setText(str);


                            }
                        })
                        .show();
            }
        });
    }
    class ChildHolderView {
        private TextView quality_inspection_childitem_row1,
                quality_inspection_childitem_row2,
                quality_inspection_childitem_row3,
                quality_inspection_childitem_row4,
                quality_inspection_childitem_row5,
                quality_inspection_childitem_row6,
                quality_inspection_childitem_row7,
                quality_inspection_childitem_row8,
                quality_inspection_childitem_row9;

        public ChildHolderView(View convertView) {
            quality_inspection_childitem_row1 = (TextView) convertView.findViewById(R.id.quality_inspection_childitem_row1);
            quality_inspection_childitem_row2 = (TextView) convertView.findViewById(R.id.quality_inspection_childitem_row2);
            quality_inspection_childitem_row3 = (TextView) convertView.findViewById(R.id.quality_inspection_childitem_row3);
            quality_inspection_childitem_row4 = (TextView) convertView.findViewById(R.id.quality_inspection_childitem_row4);
            quality_inspection_childitem_row5 = (TextView) convertView.findViewById(R.id.quality_inspection_childitem_row5);
            quality_inspection_childitem_row6 = (TextView) convertView.findViewById(R.id.quality_inspection_childitem_row6);
            quality_inspection_childitem_row7 = (TextView) convertView.findViewById(R.id.quality_inspection_childitem_row7);
            quality_inspection_childitem_row8 = (TextView) convertView.findViewById(R.id.quality_inspection_childitem_row8);
            quality_inspection_childitem_row9 = (TextView) convertView.findViewById(R.id.quality_inspection_childitem_row9);
        }
    }

    // 取得子项个数
    @Override
    public int getChildrenCount(int groupPosition) {
        return projacceptBeens.get(groupPosition).getProjacceptDetailList().size();
    }

    // 取得组对象
    @Override
    public Object getGroup(int groupPosition) {
        return this.projacceptBeens.get(groupPosition).getYhtzdno();
    }

    // 取得组个数
    @Override
    public int getGroupCount() {
        return projacceptBeens.size();
    }

    // 取得组ID
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    // 取得组显示组件
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater =
                    (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.quality_inspection_groupitem, null);
        }
        // 建立TextView组件
        TextView textView = (TextView) convertView.findViewById(R.id.quality_inspection_groupitem_name);
        // 设置文字
        textView.setText("验收单编号："+this.getGroup(groupPosition).toString());
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,12);
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    // 建立TextView,用于显示组
    public TextView buildTextView() {
        AbsListView.LayoutParams param = new AbsListView.LayoutParams
                (ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
        TextView textView = new TextView(this.mActivity);
        textView.setLayoutParams(param);
        textView.setTextSize(25.0f);
        textView.setGravity(Gravity.LEFT);
        textView.setPadding(40, 8, 3, 3);
        return textView;
    }


}
