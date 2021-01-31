package com.repository;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class CryptoClientAPI implements CryptoClientInterface {
    private final String baseUrl;
    private final String APIKey;

    public CryptoClientAPI(String baseUrl, String APIKey) {
        this.baseUrl = baseUrl;
        this.APIKey = APIKey;
    }

    public JSONArray getOHLCListFromGetCandleStick(String instrument_name, String interval){
//        OkHttpClient client = new OkHttpClient();
//        Request.Builder reqBuild = new Request.Builder();
//        HttpUrl.Builder urlBuilder =HttpUrl.parse(baseUrl).newBuilder();
//        urlBuilder.addQueryParameter("instrument_name", instrument_name);
//        urlBuilder.addQueryParameter("timeframe", interval);
//        reqBuild.url(urlBuilder.build());
//        Request request = reqBuild.build();
//        Call call = client.newCall(request);
//        try {
//            Response response = call.execute();
//        }
//        catch(Exception e) {
//            System.out.println(e);
//        }
//       return response.getContents();
        return null;
    };

    public JSONArray getTradeDataFromGetTrade(String instrument_name){
//        OkHttpClient client = new OkHttpClient();
//        Request.Builder reqBuild = new Request.Builder();
//        HttpUrl.Builder urlBuilder =HttpUrl.parse(baseUrl).newBuilder();
//        urlBuilder.addQueryParameter("instrument_name", instrument_name);
//        reqBuild.url(urlBuilder.build());
//        Request request = reqBuild.build();
//        Call call = client.newCall(request);
//        try {
//            Response response = call.execute();
//        }
//        catch(Exception e) {
//            System.out.println(e);
//        }
//        return response.getContents();
        return null;
    };
}
