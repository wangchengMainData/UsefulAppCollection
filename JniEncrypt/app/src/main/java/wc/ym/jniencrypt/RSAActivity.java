package wc.ym.jniencrypt;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.Fullscreen;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.encode_fragment)
@Fullscreen
public class RSAActivity extends Activity {
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

    String str_to_Encode = "";//等待加密
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Extra("name")
    String name;
    @Extra("age")
    int age;
    @AfterViews
    public void set(){
        bt1.setText("RSA加密");
        bt2.setText("RSA解密");
        String data = name + ":" +age;
        et1.setText(data);
        tv1.setTextColor(Color.BLACK);
        bt2.setEnabled(false);
    }

    @Click(R.id.bt1)
    public void bt1onClick(){
        str_to_Encode = et1.getText().toString();
        if(str_to_Encode.equals("")){
            Toast.makeText(getBaseContext(),"no str to encode!",Toast.LENGTH_SHORT).show();
        }else{
            byte[] secret = Cmethod.rsa_Encode(str_to_Encode);
            String str = new String(secret);
            tv1.setText(str);
            bt2.setEnabled(true);
        }
    }
    @Click(R.id.bt2)
    public void bt2onClick(){
        if(et1.getText().toString().equals("")) {
            Toast.makeText(getBaseContext(), "no str to dncode!", Toast.LENGTH_SHORT).show();
        }else {
            tv1.setText(Cmethod.rsa_Dncode());
//            Cmethod.rsa_Dncode();
        }
    }
    @Click(R.id.bt3)
    public void bt3onClick(){
        et1.setText("");
    }
}
