package com.suken.bridgedetection.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.suken.bridgedetection.R;

/**
 * Created by Administrator on 2016/7/13.
 */
public class CheckUpLoadDialog  extends AlertDialog {

    public CheckUpLoadDialog(Context context, ClickListener clickListener) {
        super(context, R.style.NOmengceng_dialog);
        this.clickListener = clickListener;
    }

private ClickListener clickListener;
    private Button update,delete, exit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkupload_dialog);
        update = (Button) findViewById(R.id.update);
        delete = (Button) findViewById(R.id.delete);
        exit = (Button) findViewById(R.id.exit);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.clickUpdate();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.clickDelete();
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }
    public interface ClickListener{
        public void clickUpdate();
        public void clickDelete();
    }
}
