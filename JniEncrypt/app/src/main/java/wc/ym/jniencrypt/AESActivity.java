package wc.ym.jniencrypt;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.Fullscreen;
import org.androidannotations.annotations.ViewById;
import java.lang.String;

@EActivity(R.layout.encode_fragment)
@Fullscreen
public class AESActivity extends Activity {

    private static final String TAG = AESActivity.class.getSimpleName();

    @ViewById
    Button bt1;
    @ViewById
    Button bt2;
    @ViewById
    Button bt3;
    @ViewById
    EditText et1;
    @ViewById
    TextView tv1;

    /*测试@Extra Activity之间传递数据*/
    @Extra("name")
    String name;
    @Extra("age")
    int age;

    String str_to_Encode = "";//等待加密

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG,"onstart");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG,"oncreate");
        super.onCreate(savedInstanceState);
    }

    @AfterViews
    public void setColor(){
        bt1.setText("AES加密");
        bt2.setText("AES解密");
        String data = name + ":" + age;
        et1.setText(data);
        tv1.setTextColor(Color.BLACK);
        bt2.setEnabled(false);
    }
    @Click(R.id.bt1)
    public void bt1Onclick(){
        str_to_Encode = et1.getText().toString();
        if(str_to_Encode.equals("")) {//未输入文本
            Toast.makeText(getBaseContext(), "none str to encode!", Toast.LENGTH_SHORT).show();
        }
        else {
            byte[] bytes = Cmethod.aes_Encode(str_to_Encode);
            String str = new String(bytes);
            tv1.setText("密文：" +str);
            bt2.setEnabled(true);
        }
    }

    @Click(R.id.bt2)
    public void bt2onclick(){
        if(et1.getText().toString().equals("")){
            Toast.makeText(getBaseContext(), "none str to decode!", Toast.LENGTH_SHORT).show();
        }else {
            tv1.setText(Cmethod.aes_Dncode());
        }
    }

    @Click(R.id.bt3)
    public void bt3Onclick(){
        et1.setText("");
    }

}
