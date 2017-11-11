package com.onyx.cryptocompare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class CurrencyDetail extends AppCompatActivity {

    private final static String TAG = CurrencyDetail.class.getSimpleName();

    private TextView mCurrencyName, mCurrencyCode, mBTCValue, mETHValue;
    ExchangeItem rateData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_detail);

        mCurrencyName = (TextView) findViewById(R.id.tv_currency_name);
        mCurrencyCode = (TextView) findViewById(R.id.tv_currency_code);
        mBTCValue = (TextView) findViewById(R.id.tv_btc_value);
        mETHValue = (TextView) findViewById(R.id.tv_eth_value);

        Intent previousIntent = getIntent();

        if (previousIntent != null && previousIntent.hasExtra(Data.EXCHANGE_ITEM))
            {
                Bundle bundle = previousIntent.getBundleExtra(Data.EXCHANGE_ITEM);
                mCurrencyName.setText(bundle.getString(Data.CURRENCY_NAME));
                mCurrencyCode.setText(bundle.getString(Data.CURRENCY_CODE));
                mBTCValue.setText(bundle.getString(Data.BTC_VALUE));
                mETHValue.setText(bundle.getString(Data.ETH_VALUE));
            }

    }
}
