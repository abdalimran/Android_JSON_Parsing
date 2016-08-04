package com.github.abdalimran.jsonparsing;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/*
Using the public API: http://ip-api.com/json
*/

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView= (TextView) findViewById(R.id.textView);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API_Interface ipService = retrofit.create(API_Interface.class);
        Call<IPModelPOJO> call = ipService.getIPDetails();


        call.enqueue(new Callback<IPModelPOJO>() {
            @Override
            public void onResponse(Call<IPModelPOJO> call, Response<IPModelPOJO> response) {
                textView.setText(response.body().toString());
                Log.d("result",response.body().toString());
            }

            @Override
            public void onFailure(Call<IPModelPOJO> call, Throwable t) {
                textView.setText("Something went wrong: " + t.getMessage());
            }
        });
    }
}
