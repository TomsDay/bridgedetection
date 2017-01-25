package com.suken.bridgedetection.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.suken.bridgedetection.R;
import com.suken.bridgedetection.activity.MaintenanceLogActivity;
import com.suken.bridgedetection.activity.ProjectAcceptanceActivity;
import com.suken.bridgedetection.bean.GeteMaterialBean;
import com.suken.bridgedetection.util.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/29.
 */
public class CLAdapter extends BaseAdapter {
    private MaintenanceLogActivity mActivity;
    List<GeteMaterialBean> clItemBeens = new ArrayList<GeteMaterialBean>();
    private LayoutInflater inflater;
    public CLAdapter(MaintenanceLogActivity activity){
        this.mActivity = activity;
        inflater = LayoutInflater.from(mActivity);
    }
    public void setData(List<GeteMaterialBean> list) {
        this.clItemBeens = list;
    }

    public List<GeteMaterialBean> getData() {
        return clItemBeens;
    }

    @Override
    public int getCount() {
        return clItemBeens.size();
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
        view = inflater.inflate(R.layout.cl_item, null);
        holder = new HolderView(view);
        view.setTag(holder);

        GeteMaterialBean bean = clItemBeens.get(position);

        holder.cl_item_tv1.setText(bean.getClmc());
        holder.cl_item_tv2.setText(bean.getGg());
        holder.cl_item_tv3.setText(bean.getXh());
        holder.cl_item_tv4.setText(bean.getClsl());
        holder.cl_item_tv5.setText(bean.getDw());

        holder.cl_item_tv4.setTag(position);
//        holder.cl_item_tv4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                InputMethodManager imm = (InputMethodManager) mActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
//                imm.toggleSoftInput(0,InputMethodManager.HIDE_NOT_ALWAYS);
//
//            }
//        });
        holder.cl_item_tv4.addTextChangedListener(new Watcher(holder.cl_item_tv4));
        
        holder.cl_item_btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clItemBeens.remove(position);
                notifyDataSetChanged();
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
        }

        @Override
        public void afterTextChanged(Editable editable) {
            String content = editable.toString();

            int position = (Integer)editTextID.getTag();

            switch (editTextID.getId()) {

                case R.id.cl_item_tv4:
//                    Logger.e("aaa","diseaseName_edit="+content+"=====position"+position);
//                    clItemBeens.get(position).setWxsl(content!=null&&!"".equals(content)?content:"");
                    mClslListener.getClsl(content!=null&&!"".equals(content)?content:"0",position);
                    break;
            }
        }
    }
    class HolderView {
        private TextView cl_item_tv1,
                cl_item_tv2,
                cl_item_tv3,
                cl_item_tv5;
        private Button cl_item_btn6;

        private EditText cl_item_tv4;

        private View cl_item_line5;

        public HolderView(View v){
            cl_item_tv1 = (TextView) v.findViewById(R.id.cl_item_tv1);
            cl_item_tv2 = (TextView) v.findViewById(R.id.cl_item_tv2);
            cl_item_tv3 = (TextView) v.findViewById(R.id.cl_item_tv3);
            cl_item_tv5 = (TextView) v.findViewById(R.id.cl_item_tv5);

            cl_item_tv4 = (EditText) v.findViewById(R.id.cl_item_tv4);

            cl_item_btn6 = (Button) v.findViewById(R.id.cl_item_btn6);



        }
    }
    public interface ClslListener{
        public void getClsl(String clsl,int position);
    }

    ClslListener mClslListener;
    public void setClslListener( ClslListener clslListener){
        mClslListener = clslListener;
    }
}
