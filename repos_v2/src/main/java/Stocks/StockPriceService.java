package Stocks;


import Stocks.JsonModel.MetaData;
import Stocks.JsonModel.StockPrice;
import Stocks.JsonModel.StockPriceHistory;
import Stocks.JsonModel.TimeSeries;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class StockPriceService {

    private static String API_KEY = "ZXGT1UWNWSNAZYT4";

    public StockPriceService(){

    }

    public StockPriceHistory getStockPriceHistory(String ticker){
        StockPriceHistory stockPriceHistory = new StockPriceHistory();

        String jsonString = connectToAlphaVantageAndGetJsonString(ticker);

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(jsonString);

            // Parse Meta Data
            JsonNode metaDataNode = root.get("Meta Data");
            MetaData metaData = mapper.treeToValue(metaDataNode, MetaData.class);

            // Parse Time Series (Daily)
            JsonNode timeSeriesNode = root.get("Time Series (Daily)");
            TimeSeries timeSeries = mapper.treeToValue(timeSeriesNode, TimeSeries.class);
            Map<String, StockPrice> stockPriceMap = timeSeries.getTimeSeriesData();

            stockPriceHistory.setMetaData(metaData);
            stockPriceHistory.setTimeSeries(timeSeries);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return stockPriceHistory;
    }

    private String connectToAlphaVantageAndGetJsonString(String ticker){
        URL url;
        HttpURLConnection con;
        StringBuffer content = new StringBuffer();

        try {
            url = new URL("https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=" + ticker +"&outputsize=full&apikey=" + API_KEY);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.ISO_8859_1));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
        } catch (Exception e){
            System.out.println(e);
        }
        return content.toString();
    }
}
