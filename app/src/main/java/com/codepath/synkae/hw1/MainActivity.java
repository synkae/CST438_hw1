package com.codepath.synkae.hw1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView tvUserId;
    private TextView tvID;
    private TextView tvTitle;
    private TextView tvBody;
    private TextView tvWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvWelcome = findViewById(R.id.tvWelcome);
        tvUserId = findViewById(R.id.tvUserID);
        tvID = findViewById(R.id.tvID);
        tvTitle = findViewById(R.id.tvTitle);
        tvBody = findViewById(R.id.tvBody);

        Bundle bundle = new Bundle();
        bundle = getIntent().getExtras();
        final String username = bundle.getString("username");
        final int id = Integer.parseInt(username);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<List<Post>> call = jsonPlaceHolderApi.getPosts();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()){
                    tvTitle.setText("Code: " + response.code());
                    return;
                }
                List<Post> posts = response.body();
                for (Post p : posts){
                    if(p.getId() == id){
                        tvUserId.setText("userID: " + p.getId());
                        tvID.setText("ID: " + p.getId());
                        tvTitle.setText("Title: " + p.getTitle());
                        tvBody.setText("Body: " + p.getText());
                        tvWelcome.setText("Welcome: " + username);
                        return;
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                tvTitle.setText("Code: " + t.getMessage());
            }
        });
    }

    public static Intent mainActivityIntent(Context context, String username){
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("username", username);
        return intent;
    }
}