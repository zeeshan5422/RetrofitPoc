package com.zeeshan.retrofitpoc.errorhandling;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.util.ToastUtils;
import com.zeeshan.retrofitpoc.R;
import com.zeeshan.retrofitpoc.models.User;

import java.io.IOException;
import java.util.List;

import retrofit2.Response;

public class PocMainActivity extends AppCompatActivity {

    private static final String TAG = PocMainActivity.class.getSimpleName();
    TextView tv_res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PocApi.setGlobalListener(mGlobalListener);

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
        PocApiInterface pocApiInterface = PocApi.getApi().create(PocApiInterface.class);
        pocApiInterface.getUsers().enqueue(myCallback);
    }

    private MyCallback<List<User>> myCallback = new MyCallback<List<User>>() {
        @Override
        public void success(Response<List<User>> response) {
            System.out.println("SUCCESS! ");
            StringBuilder sb = new StringBuilder();
            sb.append("");
            for (User user : response.body()) {
                sb.append(user.getTitle()).append("    ");
            }
            tv_res.setText(sb.toString());
        }

        @Override
        public void unauthenticated(Response<?> response) {
            System.out.println("UNAUTHENTICATED");
        }

        @Override
        public void clientError(Response<?> response) {
            System.out.println("CLIENT ERROR " + response.code() + " " + response.message());
        }

        @Override
        public void serverError(Response<?> response) {
            System.out.println("SERVER ERROR " + response.code() + " " + response.message());
        }

        @Override
        public void networkError(IOException e) {
            System.err.println("NETWORK ERROR " + e.getMessage());
        }

        @Override
        public void unexpectedError(Throwable t) {
            System.err.println("FATAL ERROR " + t.getMessage());
        }
    };


    private IGlobalCallback mGlobalListener = new IGlobalCallback() {
        @Override
        public void onSuccess() {
            Log.e("PocMainActivity", "Global::onSuccess");

        }

        @Override
        public void onUnAuthenticated() {
            Log.e("PocMainActivity", "Global::onUnAuthenticated");
        }

        @Override
        public void onInternetConnectionError() {
            ToastUtils.showShort("Internet is not available");
            Log.e("PocMainActivity", "Global::onInternetConnectionError");
        }

        @Override
        public void onDayEndError() {
            Log.e("PocMainActivity", "Global::onDayEndError");
        }
    };

}
