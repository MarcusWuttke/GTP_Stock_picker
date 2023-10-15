package Stocks.JsonModel;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;


import java.util.HashMap;
import java.util.Map;

public class TimeSeries {

    private Map<String, StockPrice> timeSeriesData = new HashMap<>();

    @JsonAnySetter
    public void setData(String date, StockPrice stockDataPoint) {
        timeSeriesData.put(date, stockDataPoint);
    }

    public Map<String, StockPrice>  getTimeSeriesData() {
        return timeSeriesData;
    }

    public void setTimeSeriesData(Map<String, StockPrice>  timeSeriesData) {
        this.timeSeriesData = timeSeriesData;
    }
}
