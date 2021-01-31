### Please install gradle first -- https://gradle.org/

Run the following command to test API

`gradle test`

# Design:
## Secret key management:
The API key is saved in `test/resources/secret.properties` file for better security.
The user should config in the server instead of changing in code and push it to git.

## Dynamic test case setting
For this testing task, developer can modify the content in `test/resources/testTradeAndCandles.csv` for different test cases.
For example:

    instrument_name,interval,test_candle_size
    BTC_USDT,5m,0
    ETH_USDT,15m,2

It means we are going to test
1. all candles obtained in 5mins chart of BTC_USDT to trade data.
2. first 2 candles obtained in 15mins chart of BTC_USDT to trade data.

# TODO:
API call is not supported due to absense of API key.
The test case and function calling now use JSON file instead.

If we have API key, we can use class `CryptoClientAPI` to get data.