package com.onyx.cryptocompare;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Scanner;

/**
 * Created by onyekaanene on 04/11/2017.
 */

public class NetworkUtils {
    private static final String TAG = MainActivity.class.getSimpleName();

    private static final String BASE_URL = "https://min-api.cryptocompare.com/data/pricemulti";

    final static String CRYPTO_PARAM = "fsyms";
    final static String MONEY_PARAM = "tsyms";

    public static URL buildUrl() {
        Uri builtUri = Uri.parse(BASE_URL).buildUpon()
                .appendQueryParameter(CRYPTO_PARAM, formCryptoQuery())
                .appendQueryParameter(MONEY_PARAM, formMoneyQuery())
                .build();

        URL url = null;
        try {
            url = new URL(URLDecoder.decode(builtUri.toString()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }
    private static String formCryptoQuery(){
        String cryptos = "";
        String[] cryptoCurrencies = Data.DEFAULT_CRYPTO_CURRENCIES;
        for (int i=0; i<cryptoCurrencies.length;i++){
            cryptos=cryptos+cryptoCurrencies[i]+",";
        }
        Log.d(TAG, cryptos);
        return cryptos;
    }
    private static String formMoneyQuery(){
        String money = "";
        String[] currencies = Data.DEFAULT_CURRENCIES;
        for (int i=0; i<currencies.length;i++){
            money=money+currencies[i]+",";
        }
        return money;
    }
    public static String getResponse(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
