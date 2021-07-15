package com.example.retrofit1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private EditText etuserid;
    private Button mbtncall;
    private TextView tvname;
    private TextView tvlastname;
    private TextView tvemail;
    private ImageView ivavatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void init(){
        etuserid=findViewById(R.id.etUserId);
        mbtncall=findViewById(R.id.btnCallApi);
        tvname=findViewById(R.id.tvfirstName);
        tvlastname=findViewById(R.id.tvLastName);
        tvemail=findViewById(R.id.tvEmail);
        ivavatar=findViewById(R.id.ivAvatar);
        mbtncall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiService apiService=Network.getInstance().create(ApiService.class);
                int userId=Integer.parseInt(etuserid.getText().toString());
                apiService.getUser(userId).enqueue(new Callback<ResponseModel>() {
                    @Override
                    public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                        ResponseModel model=response.body();
                        String firstname=model.getData().getFirstName();
                        String lastname=model.getData().getLastName();
                        String email=model.getData().getEmail();
                        tvname.setText(firstname);
                        tvlastname.setText(lastname);
                        tvemail.setText(email);
                        Glide.with(ivavatar).load(model.getData().getAvatar()).into(ivavatar);
                    }

                    @Override
                    public void onFailure(Call<ResponseModel> call, Throwable t) {

                    }
                });




            }
        });
    }
}