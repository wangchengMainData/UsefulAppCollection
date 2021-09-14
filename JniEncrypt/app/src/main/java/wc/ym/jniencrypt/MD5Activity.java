package wc.ym.jniencrypt;

import android.app.Activity;
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

import java.io.File;

@EActivity(R.layout.md5encode_fragment)
@Fullscreen
public class MD5Activity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @ViewById
    Button bt1,bt2,bt3,bt4;
    @ViewById
    TextView tv1,tv2;
    @ViewById
    EditText et1,et2;

    @AfterViews
    public void set(){
        bt1.setText("MD5加密");
        bt2.setText("清除文本");
        String str = name + ":" +age;
        et1.setText(str);
        et2.setText("/sdcard/1.txt");
    }

    @Extra("name")
    String name;
    @Extra("age")
    int age;
    @Extra("defaultpath")
    String path;

    @Click(R.id.bt1)
    public void Bt1onClick(){
        String userdata = et1.getText().toString();
        if(userdata.equals("")){
            Toast.makeText(getBaseContext(),"no str to encode!",Toast.LENGTH_SHORT).show();
        }else {
            String encrypt_data = new String(Cmethod.md5_Encode(userdata));
            tv1.setText(encrypt_data);
        }
    }

    @Click(R.id.bt2)
    public void Bt2onClick(){
        et1.setText("");
    }

    @Click(R.id.bt3)
    public void Bt3onClick(){
        String filepath = et2.getText().toString();
        if(filepath.equals("")){
            Toast.makeText(getBaseContext(),"no file path!",Toast.LENGTH_SHORT).show();
        }else {
            File file = new File(filepath);
            if(!file.exists()){
                Toast.makeText(getBaseContext(),"no file exist!",Toast.LENGTH_SHORT).show();
            } else {
                String encrypt_data = new String(Cmethod.md5_fileEncode(filepath));
                tv2.setText(encrypt_data);
            }
        }
    }

    @Click(R.id.bt4)
    public void Bt4onClick(){
        et2.setText("");
    }

}
