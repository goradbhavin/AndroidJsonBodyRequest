package com.example.androidjsonbodydemo;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Button submit;
    private ProgressDialog progressDialog;
    private String baseUrl;
    private TextView tvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        submit = (Button) findViewById(R.id.submit);
        baseUrl = "http://jsonplaceholder.typicode.com/";
        tvData = (TextView) findViewById(R.id.tvData);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitData();
            }
        });
    }

    private void submitData(){
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage(getString(R.string.loading));
        progressDialog.setCancelable(false);
        progressDialog.show();
        //Defining retrofit api service
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("title", "Hello");
        jsonObject.addProperty("body", "Retrofit");
        jsonObject.addProperty("userId", "2");

        ApiService service = retrofit.create(ApiService.class);
        Call<PostResponse> call = service.postData(jsonObject);
        //calling the api
        call.enqueue(new Callback<PostResponse>() {
            @Override
            public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
                //hiding progress dialog
                progressDialog.dismiss();
                if(response.isSuccessful()){
                    Log.d("Response",response.toString());
                    tvData.setText(response.toString());
                    Toast.makeText(getApplicationContext(), "Post submitted Title: "+response.body().getTitle()+" Body: "+response.body().getBody()+" PostId: "+response.body().getId(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<PostResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

}
