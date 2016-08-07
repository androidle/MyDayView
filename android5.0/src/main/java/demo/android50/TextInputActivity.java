package demo.android50;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class TextInputActivity extends AppCompatActivity {

    @InjectView(R.id.til_username)
    TextInputLayout til_name;


    @InjectView(R.id.til_pwd)
    TextInputLayout til_Pwd;


    @InjectView(R.id.et_name)
    EditText et_name;


    @InjectView(R.id.et_pwd)
    EditText et_pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_input);
        ButterKnife.inject(this);

        til_name.setErrorEnabled(true);

        til_name.setError("您输入有误");

        til_Pwd.setCounterEnabled(true);


        et_pwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()>5){
                    til_Pwd.setErrorEnabled(true);
                    til_Pwd.setError("密码不能超过5位");
                }else{
                    til_Pwd.setErrorEnabled(false);
                    til_Pwd.setError("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }
}
