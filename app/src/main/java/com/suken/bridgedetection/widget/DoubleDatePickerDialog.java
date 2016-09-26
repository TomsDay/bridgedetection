/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.suken.bridgedetection.widget;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;

import com.suken.bridgedetection.R;
import com.suken.bridgedetection.util.Logger;


public class DoubleDatePickerDialog extends AlertDialog implements OnClickListener, TimePicker.OnTimeChangedListener {

//	private static final String START_YEAR = "start_year";
//	private static final String END_YEAR = "end_year";
//	private static final String START_MONTH = "start_month";
//	private static final String END_MONTH = "end_month";
//	private static final String START_DAY = "start_day";
//	private static final String END_DAY = "end_day";

    private String checkTime;
    private TimePicker mTimePicker_start;
    private TimePicker mTimePicker_end;
    private final OnDateSetListener mCallBack;
    private int startHour;
    private int startMinute;
    private int endHour;
    private int endMinute;
    private Context mContext;



    /**
     * The callback used to indicate the user is done filling in the date.
     */
    public interface OnDateSetListener {


        void onDateSet(String time);
    }




    public DoubleDatePickerDialog(Context context, OnDateSetListener callBack, String time) {
        super(context, 0);
        checkTime = time;

        mContext = context;
        String[] timeStr = time.split("-");
        String[] startTime = timeStr[0].split(":");
        String[] endTime = timeStr[1].split(":");


        mCallBack = callBack;

        setButton(BUTTON_POSITIVE, "确 定", this);
        setButton(BUTTON_NEGATIVE, "取 消", this);
        // setButton(BUTTON_POSITIVE,
        // themeContext.getText(android.R.string.date_time_done), this);
        setIcon(0);

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.date_picker_dialog, null);
        setView(view);
        mTimePicker_start = (TimePicker) view.findViewById(R.id.timePickerStart);
        mTimePicker_end = (TimePicker) view.findViewById(R.id.timePickerEnd);

        mTimePicker_start.setIs24HourView(true);
        mTimePicker_end.setIs24HourView(true);

        mTimePicker_start.setOnTimeChangedListener(this);
        mTimePicker_end.setOnTimeChangedListener(this);


        startHour = Integer.parseInt(startTime[0]);
        startMinute = Integer.parseInt(startTime[1]);
        Logger.e("aaa", "startMinute==" + startMinute);
        endHour = Integer.parseInt(endTime[0]);
        endMinute = Integer.parseInt(endTime[1]);
        Logger.e("aaa", "endMinute==" + endMinute);

        mTimePicker_start.setCurrentMinute(startMinute);
        mTimePicker_start.setCurrentHour(startHour);

        mTimePicker_end.setCurrentMinute(endMinute);
        mTimePicker_end.setCurrentHour(endHour);
        setCanceledOnTouchOutside(true);// 设置点击屏幕Dialog不消失


    }



    public void onClick(DialogInterface dialog, int which) {
        // Log.d(this.getClass().getSimpleName(), String.format("which:%d",
        // which));
        // 如果是“取 消”按钮，则返回，如果是“确 定”按钮，则往下执行
        if (which == BUTTON_POSITIVE)
            tryNotifyDateSet();
    }

    @Override
    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
        switch (view.getId()) {
            case R.id.timePickerStart:
                startHour = hourOfDay;
                startMinute = minute;
                break;
            case R.id.timePickerEnd:
                endHour = hourOfDay;
                endMinute = minute;
                break;
        }

    }





    private void tryNotifyDateSet() {
        if (mCallBack != null) {
            int startMin = startHour * 60 + startMinute;
            int endMin = endHour * 60 + endMinute;
            if (startMin >= endMin) {
                Toast.makeText(mContext, "开始时间不能比结束时间大", Toast.LENGTH_SHORT).show();
                return;
            }
            mTimePicker_start.clearFocus();
            mTimePicker_end.clearFocus();
            checkTime = (startHour < 10 ? "0" + startHour : startHour)
                    + ":" +
                    (startMinute < 10 ? "0" + startMinute : startMinute) +
                    ("-") +
                    (endHour < 10 ? "0" + endHour : endHour) +
                    (":") +
                    (endMinute < 10 ? "0" + endMinute : endMinute);
            mCallBack.onDateSet(checkTime);
        }
    }

    @Override
    protected void onStop() {
        // tryNotifyDateSet();
        super.onStop();
    }

//	@Override
//	public Bundle onSaveInstanceState() {
//		Bundle state = super.onSaveInstanceState();
//		state.putInt(START_YEAR, mTimePicker_start.getYear());
//		state.putInt(START_MONTH, mTimePicker_start.getMonth());
//		state.putInt(START_DAY, mTimePicker_start.getDayOfMonth());
//		state.putInt(END_YEAR, mTimePicker_end.getYear());
//		state.putInt(END_MONTH, mTimePicker_end.getMonth());
//		state.putInt(END_DAY, mTimePicker_end.getDayOfMonth());
//		return state;
//	}
//
//	@Override
//	public void onRestoreInstanceState(Bundle savedInstanceState) {
//		super.onRestoreInstanceState(savedInstanceState);
//		int start_year = savedInstanceState.getInt(START_YEAR);
//		int start_month = savedInstanceState.getInt(START_MONTH);
//		int start_day = savedInstanceState.getInt(START_DAY);
//		mTimePicker_start.init(start_year, start_month, start_day, this);
//
//		int end_year = savedInstanceState.getInt(END_YEAR);
//		int end_month = savedInstanceState.getInt(END_MONTH);
//		int end_day = savedInstanceState.getInt(END_DAY);
//		mTimePicker_end.init(end_year, end_month, end_day, this);
//
//	}
}