package com.onyx.cryptocompare;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by onyekaanene on 04/11/2017.
 */

public class ExchangeAdapter extends RecyclerView.Adapter<ExchangeAdapter.ExchangeAdapterViewHolder> {
    private ExchangeItem[] mCurrencyData;
    public ExchangeAdapter(){}
    public class ExchangeAdapterViewHolder extends RecyclerView.ViewHolder{
        public final TextView mCurrencyTextView;
        public final TextView mBtcRateTextView;
        public final TextView mEthRateTextView;
        public ExchangeAdapterViewHolder(View view){
            super(view);
            mBtcRateTextView = (TextView)view.findViewById(R.id.tv_btc_value);
            mCurrencyTextView = (TextView)view.findViewById(R.id.tv_currency);
            mEthRateTextView = (TextView)view.findViewById(R.id.tv_eth_value);
        }
    }
    @Override
    public ExchangeAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.exchange_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        return new ExchangeAdapterViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ExchangeAdapterViewHolder exchangeAdapterViewHolder, int position) {
        ExchangeItem currencyDetails = mCurrencyData[position];
        exchangeAdapterViewHolder.mBtcRateTextView.setText(String.valueOf(currencyDetails.getBtcRate()));
        exchangeAdapterViewHolder.mCurrencyTextView.setText(currencyDetails.getCurrency());
        exchangeAdapterViewHolder.mEthRateTextView.setText(String.valueOf(currencyDetails.getEthRate()));
    }
    @Override
    public int getItemCount() {
        if (null == mCurrencyData) return 0;
        return mCurrencyData.length;
    }
    public void setCurrencyData(ExchangeItem[] currencyData) {
        mCurrencyData = currencyData;
        notifyDataSetChanged();
    }
}
