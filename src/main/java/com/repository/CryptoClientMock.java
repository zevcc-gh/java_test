package com.repository;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CryptoClientMock implements CryptoClientInterface {
    public JSONArray getOHLCListFromGetCandleStick(String instrument_name, String interval) {
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("src/main/java/com/repository/candle.json")) {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public JSONArray getTradeDataFromGetTrade(String instrument_name) {
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("src/main/java/com/repository/trade.json")) {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
