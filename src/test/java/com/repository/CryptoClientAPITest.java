package com.repository;

import org.junit.jupiter.api.BeforeAll;
import com.repository.CryptoClientMock.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.Hashtable;
import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;

class CryptoClientAPITest {
    private static String baseUrl;
    private static String APIKey;

    public static Hashtable<String, Long> getIntervalToTimeStamp() {
        final Hashtable<String, Long> intervalToTimestamp = new Hashtable<String, Long>();
        intervalToTimestamp.put("1m", 60000L);
        intervalToTimestamp.put("5m", 300000L);
        intervalToTimestamp.put("15m", 900000L);
        intervalToTimestamp.put("30m", 1800000L);
        intervalToTimestamp.put("1h", 3600000L);
        intervalToTimestamp.put("4h", 14400000L);
        intervalToTimestamp.put("6h", 21600000L);
        intervalToTimestamp.put("12h", 43200000L);
        intervalToTimestamp.put("1D", 86400000L);
        intervalToTimestamp.put("7D", 604800000L);
        intervalToTimestamp.put("14D", 1209600000L);
        intervalToTimestamp.put("1M", 2592000000L);

        return intervalToTimestamp;
    }

    @BeforeAll
    public static void initializeSetting(){
        try (InputStream input = CryptoClientAPITest.class.getClassLoader().getResourceAsStream("secret.properties")) {
            Properties prop = new Properties();

            if (input == null) {
                System.out.println("Unable to find secret.properties");
                return;
            }

            //load a properties file from class path, inside static method
            prop.load(input);

            //get the property value
            baseUrl = prop.getProperty("CryptocomBaseUrl");
            APIKey = prop.getProperty("CryptocomAPIkey");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testTradeAndCandles.csv", numLinesToSkip = 1)
    public void testTradeAndCandles(String instrument_name, String interval, int test_candle_size){
        Hashtable<String, Long> intervalToTimestamp = getIntervalToTimeStamp();
        CryptoClientMock clientMock = new CryptoClientMock();

        JSONArray candles = clientMock.getOHLCListFromGetCandleStick(instrument_name, interval);
        JSONArray trades = clientMock.getTradeDataFromGetTrade(instrument_name);
        // those two arrays should be ordered by timestamp
        // we can compare them directly without further sorting

        // get candle first
        if (test_candle_size == 0){
            test_candle_size = candles.size();
        };
        // will loop trade later, need to save the current index
        int trade_cur_index = 0;
        for (int i = 0; i < test_candle_size; ++i){
            JSONObject candle = (JSONObject) candles.get(i);
            BigDecimal candle_o = new BigDecimal(candle.get("o").toString());
            BigDecimal candle_h = new BigDecimal(candle.get("h").toString());
            BigDecimal candle_l = new BigDecimal(candle.get("l").toString());
            BigDecimal candle_c = new BigDecimal(candle.get("c").toString());
            Long candle_start_timestamp = (Long) candle.get("t");
            Long time_diff = intervalToTimestamp.get(interval);

            //get the trade data with current index for reference
            JSONObject trade = (JSONObject) trades.get(trade_cur_index);
            Long trade_timestamp = (Long) trade.get("t");
            BigDecimal trade_price = new BigDecimal(trade.get("p").toString());
            BigDecimal trade_o = trade_price; //open price = first trade price
            BigDecimal trade_h = trade_price;
            BigDecimal trade_l = trade_price;
            BigDecimal trade_c = trade_price;

            //handle the related trades data for the current candle
            while ((trade_cur_index != trades.size() - 1) && (candle_start_timestamp <= trade_timestamp) && (candle_start_timestamp + time_diff >= trade_timestamp)) {
                //load next tick data
                trade_cur_index++;
                trade = (JSONObject) trades.get(trade_cur_index);
                trade_timestamp = (Long) trade.get("t");
                trade_price = new BigDecimal(trade.get("p").toString());
                // public int compareTo(BigDecimal val)
                // returns -1 if the BigDecimal is less than val, 1 if the BigDecimal is greater than val and 0 if the BigDecimal is equal to val
                if (trade_price.compareTo(trade_h) == 1){
                    trade_h = trade_price;
                }
                if (trade_price.compareTo(trade_l) == -1) {
                    trade_l = trade_price;
                }
            }
            trade_c = trade_price; //close price = last trade price


            //compare candle data and trade data
            assertEquals(candle_o, trade_o);
            assertEquals(candle_h, trade_h);
            assertEquals(candle_l, trade_l);
            assertEquals(candle_c, trade_c);
        }
    }
}