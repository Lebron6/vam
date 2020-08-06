package com.dy.vam.ui.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.dy.vam.R;

/**
 * Created by Administrator on 2016/7/5.
 */
public class CustomerOperationDialog extends Dialog  {


    public CustomerOperationDialog(Context context) {
        super(context);
    }

    public CustomerOperationDialog(Context context, int theme, DialogClickListener dialogClickListener, int position) {
        super(context, theme);
        this.dialogClickListener=dialogClickListener;
        this.position=position;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_customer_opera);
        initView();
    }

    private void initView() {
        TextView tvEdit=findViewById(R.id.tv_edit);
        TextView tvDelete=findViewById(R.id.tv_delete);
        tvEdit.setOnClickListener(onClickListener );
        tvDelete.setOnClickListener(onClickListener );
    }

    View.OnClickListener onClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.tv_edit:
                    dialogClickListener.edit(position);dismiss();
                    break;
                case R.id.tv_delete:
                    dialogClickListener.delete(position);dismiss();
                    break;
            }
        }
    };

    public int position;
    public DialogClickListener dialogClickListener;

    public interface DialogClickListener{
       void edit(int position);
       void delete(int position);
    }
}
