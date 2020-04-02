package com.zeeshan.retrofitpoc.genericApiReqHandler;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.zeeshan.retrofitpoc.R;
import com.zeeshan.retrofitpoc.models.User;

import java.util.List;

public class GenericPocActivity extends AppCompatActivity {

    private static final String TAG = GenericPocActivity.class.getSimpleName();
    TextView tv_res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button testBtn = findViewById(R.id.test_btn);
        tv_res = findViewById(R.id.tv_res);
        testBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                testIt();
            }
        });
    }

    private void testIt() {

        tv_res.setText("");

//        LiveData<DataWrapper<List<User>>> usersList = new UsersListApi().getUsersList();
//        usersList.observe(this, new Observer<DataWrapper<List<User>>>() {
//            @Override
//            public void onChanged(DataWrapper<List<User>> listDataWrapper) {
//                tv_res.setText(listDataWrapper.getData().toString());
//            }
//        });

        new NetworkRepo<List<User>>().provideUsers().observe(this, new Observer<DataWrapper<List<User>>>() {
            @Override
            public void onChanged(DataWrapper<List<User>> listDataWrapper) {
                if (listDataWrapper.getData() != null) {
                    tv_res.setText(listDataWrapper.getData().toString());
                } else if (listDataWrapper.getApiException() != null) {
                    tv_res.setText(listDataWrapper.getApiException().getLocalizedMessage());
                }else{
                    tv_res.setText("Unknown error occur!!!");
                }
            }
        });
    }


}
