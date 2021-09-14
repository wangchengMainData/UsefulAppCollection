package wc.ym.jniencrypt;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
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
public class Base64Activity extends Activity {
    private static final String TAG = Base64Activity.class.getSimpleName();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Extra("name")
    String name;
    @Extra("age")
    int age;

    @ViewById
    Button bt1,bt2,bt3;
    @ViewById
    TextView tv1;
    @ViewById
    EditText et1;
    @AfterViews
    public void set(){
        bt1.setText("Base64加密");
        bt2.setText("Base64解密");
        String str = name + ":" + age;
        et1.setText(str);
    }

    @Click(R.id.bt1)
    public void Bt1onClick(){
        String userdata = et1.getText().toString();
        if(userdata.equals("")){
            Toast.makeText(getBaseContext(),"no str to encode!",Toast.LENGTH_SHORT).show();
        }else{
            Log.e(TAG,"success");
            String encode = new String(Cmethod.base64_encode(userdata));
            tv1.setText(encode);
        }
    }
    @Click(R.id.bt2)
    public void Bt2onClick(){
        //decode
        tv1.setText(Cmethod.base64_decode());
    }
    @Click(R.id.bt3)
    public void Bt3onClick(){
        et1.setText("");
    }
}
