package com.repository;

import org.json.simple.JSONArray;

interface CryptoClientInterface {
    // {"t":1596944700000,"o":11752.38,"h":11754.77,"l":11746.65,"c":11753.64,"v":3.694583}
    public JSONArray getOHLCListFromGetCandleStick(String instrument_name, String interval);
    // {"dataTime":1591710787500,"d":465533770081010000,"s":"BUY","p":3.159,"q":1.65,"t":1591710787498,"i":"ATOM_USDT"},
    public JSONArray getTradeDataFromGetTrade(String instrument_name);
}