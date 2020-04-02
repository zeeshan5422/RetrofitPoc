package com.zeeshan.retrofitpoc;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.zeeshan.retrofitpoc.genericApiReqHandler.GenericRequestHandler;
import com.zeeshan.retrofitpoc.listeners.InternetConnectionListener;
import com.zeeshan.retrofitpoc.models.User;
import com.zeeshan.retrofitpoc.newOne.MyResource;
import com.zeeshan.retrofitpoc.newOne.MyResponse;

import retrofit2.Call;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button testBtn = findViewById(R.id.test_btn);
        testBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                testIt();
            }
        });

        Api.setInternetConnectionListener(mConnectionListener);
    }

    private void testIt() {

//        new NfsApi.Builder()..onResponse(mOnResponse).api();

//        NetworkHandler networkHandler = new NetworkHandler();
//        networkHandler.enqueue(networkHandler.getApi().getUsers(), mOnResponse);


//        new NetworkHandler.Builder().build();
//        new ApiInterfaceCallbacks().getUsers()


//        new NfsApi().getApi().getUsers().enqueue(mOnResponse);
//        NfsApi.getInstance(ApiInterface.class).getApi().getUsers().enqueue(mOnResponse);

//        ApiInterface apiInterface = Api.getApi().create(ApiInterface.class);
        try {
//            Response<List<User>> execute = networkHandler.execute(networkHandler.getApi().getUsers());
//            Response<List<User>> listResponse = apiInterface.getUsers().execute();

        } catch (Exception e) {
            e.printStackTrace();
        }

//        ApiInterface apiInterface = Api.getApi().create(ApiInterface.class);
//        apiInterface.getUserz().enqueue(mOnResponse);
    }

    private ApiCallback mOnResponse = new ApiCallback() {
        @Override
        public void onCallResponse(Call call, Response response) {
            Log.v(TAG, "child class onCallResponse callback" + response.toString());
        }

        @Override
        public void onCallFailure(Call call, Throwable t) {
            Log.v(TAG, "child class onCallFailure callback" + t.getLocalizedMessage());
        }
    };


    InternetConnectionListener mConnectionListener = new InternetConnectionListener() {

        @Override
        public void onInternetAvailable() {
            Log.v(TAG, "onInternetAvailable");
        }

        @Override
        public void onInternetUnavailable() {
            Log.v(TAG, "onInternetUnavailable");

        }
    };

    private void newOneTest() {

/*        GenericRequestHandler<List<User>> genericRequestHandler = new GenericRequestHandler<List<User>>() {

            @Override
            protected Call<Response<List<User>>> makeRequest() {
                return null;
            }
        };
        LiveData<DataWrapper<List<User>>> dataWrapperLiveData = genericRequestHandler.doRequest();
        DataWrapper<List<User>> value = dataWrapperLiveData.getValue();
        */

        NetworkHandler networkHandler = new NetworkHandler();
//        networkHandler.enqueue(networkHandler.getApi().getUserz(), mOnResponse);

//        networkHandler.getApi().getUsers();
        MyResponse.create(new MyResource<Object>() {
            @Override
            public void onLoading(boolean isLoading) {

            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onSuccess(@Nullable Object o) {

            }
        }).start();
    }

}
