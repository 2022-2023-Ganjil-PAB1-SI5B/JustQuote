package com.ahmfarisi.justquote.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ahmfarisi.justquote.API.APIRequestData;
import com.ahmfarisi.justquote.API.RetroServer;
import com.ahmfarisi.justquote.Adapter.AdapterQuote;
import com.ahmfarisi.justquote.Model.QuoteModel;
import com.ahmfarisi.justquote.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvQuote;
    private ProgressBar pbQuote;
    private List<QuoteModel> listQuote;
    private AdapterQuote adQuote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvQuote = findViewById(R.id.rv_quote);
        pbQuote = findViewById(R.id.pb_quote);
        rvQuote.setLayoutManager(new LinearLayoutManager(this));
        retrieveData();
    }

    private void retrieveData(){
        pbQuote.setVisibility(View.VISIBLE);

        APIRequestData ARD = RetroServer.connectRetrofit().create(APIRequestData.class);
        Call<List<QuoteModel>> process = ARD.ardRetrieveQuote();

        process.enqueue(new Callback<List<QuoteModel>>() {
            @Override
            public void onResponse(Call<List<QuoteModel>> call, Response<List<QuoteModel>> response) {
                listQuote = response.body();
                adQuote = new AdapterQuote(listQuote, MainActivity.this);
                rvQuote.setAdapter(adQuote);
                pbQuote.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<QuoteModel>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Can't Connect to Server!", Toast.LENGTH_SHORT).show();
                pbQuote.setVisibility(View.GONE);
            }
        });
    }
}