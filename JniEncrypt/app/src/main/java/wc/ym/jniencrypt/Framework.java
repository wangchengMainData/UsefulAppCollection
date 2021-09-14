package wc.ym.jniencrypt;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import androidx.annotation.Nullable;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Fullscreen;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.fragment)
@Fullscreen
public class Framework extends Activity {

    private static final String TAG = Framework.class.getSimpleName();

    @ViewById(R.id.bt_aes)
    Button bt1;
    @ViewById(R.id.bt_rsa)
    Button bt2;
    @ViewById(R.id.bt_md5)
    Button bt3;
    @ViewById(R.id.bt_base64)
    Button bt4;
    @ViewById(R.id.bt_sha256)
    Button bt5;

    private Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG,"Main Thread :" +Thread.currentThread().getId());
    }

    @Click(R.id.bt_aes)
    public void AESonClick(){
        startIntent(AESActivity_.class);
    }

    @Click(R.id.bt_rsa)
    public void RSAonClick(){
        startIntent(RSAActivity_.class);
//        printBack();
    }

    @Click(R.id.bt_md5)
    public void MD5onClick(){
        intent = new Intent(this,MD5Activity_.class);
        intent.putExtra("name","wangcheng");//测试＠Extra
        intent.putExtra("age",26);
        intent.putExtra("defaultpath","/storage/emulated/0/1.txt");//测试＠Extra
        startActivity(intent);
    }

    @Click(R.id.bt_base64)
    public void BASE64onClick(){
        startIntent(Base64Activity_.class);
    }

    @Click(R.id.bt_sha256)
    public void SHA256onClick(){
        startIntent(SHAActivity_.class);
    }

    @AfterViews/*onCreate执行完毕后的初始化*/
    public void set(){
        /*诸如setText等等*/
    }

    @Background
    public void printBack(){
        Log.e("wc","thread =" + Thread.currentThread().getId());
        printFront();
    }

    @UiThread
    public void printFront(){
        bt1.setTextColor(Color.GREEN);
        Log.e("wc","thread(front) = " + Thread.currentThread().getId());
    }

    private void startIntent(Class cls){
        intent = new Intent(this,cls);
        intent.putExtra("name","wangcheng");//测试＠Extra
        intent.putExtra("age",26);
        startActivity(intent);
    }
}
