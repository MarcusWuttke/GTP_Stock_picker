package Stocks.JsonModel;


import com.fasterxml.jackson.annotation.JsonProperty;

public class StockPrice {
    @JsonProperty("1. open")
    private String open;

    @JsonProperty("2. high")
    private String high;

    @JsonProperty("3. low")
    private String low;

    @JsonProperty("4. close")
    private String close;

    @JsonProperty("5. volume")
    private String volume;

    // Getter method for 'open'
    public String getOpen() {
        return open;
    }

    // Setter method for 'open'
    public void setOpen(String open) {
        this.open = open;
    }

    // Getter method for 'high'
    public String getHigh() {
        return high;
    }

    // Setter method for 'high'
    public void setHigh(String high) {
        this.high = high;
    }

    // Getter method for 'low'
    public String getLow() {
        return low;
    }

    // Setter method for 'low'
    public void setLow(String low) {
        this.low = low;
    }

    // Getter method for 'close'
    public String getClose() {
        return close;
    }

    // Setter method for 'close'
    public void setClose(String close) {
        this.close = close;
    }

    // Getter method for 'volume'
    public String getVolume() {
        return volume;
    }

    // Setter method for 'volume'
    public void setVolume(String volume) {
        this.volume = volume;
    }

}
