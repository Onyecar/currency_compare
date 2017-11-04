package com.onyx.cryptocompare;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

/**
 * Created by onyekaanene on 04/11/2017.
 */

public class JSONHandler {
    public ExchangeItem[] formatJson (String jsonString) throws JSONException {
        JSONObject json = new JSONObject(jsonString);
        JSONObject btcJson = json.getJSONObject("BTC");
        JSONObject ethJson = json.getJSONObject("ETH");
        int arrayLength = btcJson.length();
        ExchangeItem[] items = new ExchangeItem[arrayLength];
        String[] currencies = new String[arrayLength];

        int index = 0;
        Iterator iterator = btcJson.keys();
        while (iterator.hasNext()){
            String currency = (String) iterator.next();
            items[index].setCurrency(currency);
            items[index].setBtcRate(btcJson.getDouble(currency));
            items[index].setEthRate(ethJson.getDouble(currency));
            index++;
        }
        return items;
    }
}
