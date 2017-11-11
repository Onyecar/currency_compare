package com.onyx.cryptocompare;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

/**
 * Created by onyekaanene on 04/11/2017.
 */

public class JSONHandler {
    private static final String TAG = JSONHandler.class.getSimpleName();
    public ExchangeItem[] formatJson (String excJsonString, String currJsonString) throws JSONException {

        JSONObject json = new JSONObject(excJsonString);
        JSONObject btcJson = json.getJSONObject("BTC");
        JSONObject ethJson = json.getJSONObject("ETH");
        JSONObject currJson = new JSONObject(currJsonString);
        JSONObject currenciesJson = currJson.getJSONObject("currencies");

        int arrayLength = btcJson.length();
        ExchangeItem[] items = new ExchangeItem[arrayLength];
        String[] currencies = new String[arrayLength];

        int index = 0;
        Iterator iterator = btcJson.keys();
        while (iterator.hasNext()){
            String currency = (String) iterator.next();
            Log.d(TAG, currency);

            items[index]= new ExchangeItem();
            items[index].setCurrency(currency);
            items[index].setBtcRate(btcJson.getDouble(currency));
            items[index].setEthRate(ethJson.getDouble(currency));
            items[index].setCurrencyName(currenciesJson.getString(currency));
            index++;
        }
        return items;
    }
}
