package wc.ym.jniencrypt;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Fullscreen;

@EActivity(R.layout.encode_fragment)
@Fullscreen
public class SHAActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}

