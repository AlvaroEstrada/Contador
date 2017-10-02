package com.alvaropedrajas.contador;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public Integer cont = 0;
    CheckBox cb;
    TextView tv_num;
    Button btnMinus;
    EditText et_num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnPlus = (Button) findViewById(R.id.btnPlus);
        btnMinus = (Button) findViewById(R.id.btnMinus);
        Button btnReset = (Button) findViewById(R.id.btnReset);
        et_num = (EditText) findViewById(R.id.et_num);
        tv_num = (TextView) findViewById(R.id.tv_num);
        cb = (CheckBox) findViewById(R.id.cb);
        btnPlus.setOnClickListener(this);
        btnMinus.setOnClickListener(this);
        btnReset.setOnClickListener(this);
        cb.setOnClickListener(this);

        tv_num.setText(cont.toString());
        if (cont == 0 && !cb.isChecked()){
            btnMinus.setEnabled(false);
        }
        if (cont == 0 && cb.isChecked()){
            btnMinus.setEnabled(true);
        }
    }

    public void up(){
        cont += 1;
        tv_num.setText(cont.toString());
        btnMinus.setEnabled(true);
    }

    public void down(){
        if (cont <= 0){
            if (cb.isChecked()){
                cont -= 1;
                tv_num.setText(cont.toString());
            }else{
                btnMinus.setEnabled(false);
            }
        }
        if (cont > 0){
            cont -= 1;
            tv_num.setText(cont.toString());
        }
    }

    public void reset(){
        if (!TextUtils.isEmpty(et_num.getText().toString())){
            cont = Integer.parseInt(et_num.getText().toString());
            tv_num.setText(cont.toString());
        }else{
            cont = 0;
            tv_num.setText(cont.toString());
        }

        if (cont < 0){
            btnMinus.setEnabled(true);
            cb.setChecked(true);
        }else{
            cb.setChecked(false);
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt("CONT", cont);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        cont = savedInstanceState.getInt("CONT");
        tv_num.setText(String.valueOf(cont));
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnPlus:
                up();
                break;
            case R.id.btnMinus:
                down();
                break;
            case R.id.btnReset:
                reset();
                break;
            case R.id.cb:
                btnMinus.setEnabled(true);
        }

    }
}
