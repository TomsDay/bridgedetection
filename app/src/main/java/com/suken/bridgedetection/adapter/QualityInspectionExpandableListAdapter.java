package com.suken.bridgedetection.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.suken.bridgedetection.R;
import com.suken.bridgedetection.activity.QualityInspectionActivity;
import com.suken.bridgedetection.bean.ProjacceptBean;
import com.suken.bridgedetection.bean.ProjacceptItemBean;

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
    private TextView childTextView;
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
        return projacceptBeens.get(groupPosition).getProjacceptDetailList().get(childPosition);
    }

    // 取得子项ID
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    //点击事件发生后:先执行事件监听,然后调用此getChildView()
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {// 返回子项组件
        if (convertView == null) {
            LayoutInflater inflater =
                    (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.quality_inspection_childitem, null);
        }
        ProjacceptItemBean bean = (ProjacceptItemBean) getChild(groupPosition, childPosition);
        childTextView = (TextView) convertView.findViewById(R.id.textView);
        childTextView.setText(getChild(groupPosition, childPosition).toString());
//        childBox = (CheckBox) convertView.findViewById(R.id.checkBox);
        //获取当前子项CheckBox的状态
//        Boolean nowStatus = statusHashMap.get(children[groupPosition][childPosition]);
//        childBox.setChecked(nowStatus);
        return convertView;
    }

    // 取得子项个数
    @Override
    public int getChildrenCount(int groupPosition) {
        return projacceptBeens.get(groupPosition).getProjacceptDetailList().size();
    }

    // 取得组对象
    @Override
    public Object getGroup(int groupPosition) {
        return this.projacceptBeens.get(groupPosition);
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
        // 建立TextView组件
        TextView textView = buildTextView();
        // 设置文字
        textView.setText(this.getGroup(groupPosition).toString());
        textView.setBackgroundColor(mActivity.getResources().getColor(R.color.white));
        return textView;
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
