package com.repository;

import okhttp3.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class CryptoClient {
    private final String baseUrl;
    private final String APIKey;

    public CryptoClient(String baseUrl, String APIKey) {
        this.baseUrl = baseUrl;
        this.APIKey = APIKey;
    }
    // {"t":1596944700000,"o":11752.38,"h":11754.77,"l":11746.65,"c":11753.64,"v":3.694583}
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
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("src/main/java/com/repository/candle.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
            JSONObject candleRawData = (JSONObject) obj;
            JSONObject candleResult = (JSONObject) candleRawData.get("result");
            JSONArray candleData = (JSONArray) candleResult.get("data");

            return candleData;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    };


//    {"dataTime":1591710787500,"d":465533770081010000,"s":"BUY","p":3.159,"q":1.65,"t":1591710787498,"i":"ATOM_USDT"},
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
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("src/main/java/com/repository/trade.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
            JSONObject tradeRawData = (JSONObject) obj;
            JSONArray tradeResult = (JSONArray) tradeRawData.get("result");
            return tradeResult;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    };
}
