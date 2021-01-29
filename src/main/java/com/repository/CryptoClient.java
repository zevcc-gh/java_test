import okhttp3.*;

public class CryptoClient {
    private final String baseUrl;

    public CryptoClient(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getCandles(){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(baseUrl + "get-candlestick?instrument_name=BTC_USDT&timeframe=5m")
                .build();
        Call call = client.newCall(request);
        try {
            Response response = call.execute();
        }
        catch(Exception e) {
            System.out.println(e);
            //  Block of code to handle errors
        }
        return "123";
//        return response.getContents().getQuotes().get(0).getQuote();
    }

    public static void main(String[] args){
        CryptoClient c = new CryptoClient("https://uat-api.3ona.co/v2/");
        c.getCandles();
    }
}
