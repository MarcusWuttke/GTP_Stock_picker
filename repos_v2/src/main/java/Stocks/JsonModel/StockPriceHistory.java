package Stocks.JsonModel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StockPriceHistory {

    private MetaData metaData;
    private TimeSeries timeSeries;

    // Getter method for 'metaData'
    public MetaData getMetaData() {
        return metaData;
    }

    // Setter method for 'metaData'
    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

    // Getter method for 'timeSeries'
    public TimeSeries getTimeSeries() {
        return timeSeries;
    }

    // Setter method for 'timeSeries'
    public void setTimeSeries(TimeSeries timeSeries) {
        this.timeSeries = timeSeries;
    }
}
