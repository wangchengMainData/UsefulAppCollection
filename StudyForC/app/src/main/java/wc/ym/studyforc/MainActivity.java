package wc.ym.studyforc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Arrays;


public class MainActivity extends AppCompatActivity {

    Button bt1,bt2,bt3;
    EditText tv1;
    String str_to_Encode = "";//等待加密
    String str_Encoded = "";//等待解密

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt1 = findViewById(R.id.bt1);
        bt2 = findViewById(R.id.bt2);
        bt3 = findViewById(R.id.bt3);
        tv1 = findViewById(R.id.tv1);
        bt1.setTag(1);
        bt2.setTag(2);
        bt3.setTag(3);
        bt1.setOnClickListener(mClickListener);
        bt2.setOnClickListener(mClickListener);
        bt3.setOnClickListener(mClickListener);
    }

    View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int i = (int)view.getTag();
            switch (i){
                case 1:
                    str_to_Encode = tv1.getText().toString();
                    if(str_to_Encode.equals("")) {//未输入文本
                        Toast.makeText(getBaseContext(), "none str to encode!", Toast.LENGTH_SHORT).show();
                        Cmethod.EncodeString("str",3);
                    }
                    else {
                        str_Encoded = Cmethod.encode(str_to_Encode);
                        tv1.setText(str_Encoded);
                    }
                    break;
                case 2:
                    if(str_Encoded.equals("")){
                        Toast.makeText(getBaseContext(),"none str to decode!",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getBaseContext(),str_Encoded,Toast.LENGTH_SHORT).show();
                        tv1.setText(Cmethod.decode(str_Encoded));
                    }
                    break;
                case 3:
                    tv1.setText("");
                    str_to_Encode = str_Encoded = "";
                    break;
            }
        }
    };


}
