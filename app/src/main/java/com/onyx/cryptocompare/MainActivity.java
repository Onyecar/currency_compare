package com.onyx.cryptocompare;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private ExchangeAdapter mExchangeAdapter;
    private RecyclerView mRecyclerView;
    private TextView mErrorMessageDisplay;
    private ProgressBar mLoadingIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_exchange);
        mErrorMessageDisplay = (TextView) findViewById(R.id.tv_error_message_display);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setHasFixedSize(true);

        mExchangeAdapter = new ExchangeAdapter();

        mRecyclerView.setAdapter(mExchangeAdapter);

        mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);
        loadData();
    }

    private void showData(){
        mErrorMessageDisplay.setVisibility(View.INVISIBLE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }
    private void hideData(){
        mRecyclerView.setVisibility(View.INVISIBLE);
        mErrorMessageDisplay.setVisibility(View.VISIBLE);
    }

    private void loadData(){
        showData();
        new FetchRateTask().execute();
    }
    public class FetchRateTask extends AsyncTask<String, Void, ExchangeItem[]> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingIndicator.setVisibility(View.VISIBLE);
            Log.d(TAG, "in pre execute");
        }

        @Override
        protected ExchangeItem[] doInBackground(String... params) {
            Log.d(TAG, "in pre execute");
            URL weatherRequestUrl = NetworkUtils.buildUrl();

            try {
                String jsonResponse = NetworkUtils.getResponse(weatherRequestUrl);
                JSONHandler jh = new JSONHandler();

                ExchangeItem[] rateData = jh.formatJson(jsonResponse);

                return rateData;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(ExchangeItem[] rateData) {
            mLoadingIndicator.setVisibility(View.INVISIBLE);
            if (rateData != null) {
                showData();
                mExchangeAdapter.setCurrencyData(rateData);
            } else {
                hideData();
            }
        }
    }
}
