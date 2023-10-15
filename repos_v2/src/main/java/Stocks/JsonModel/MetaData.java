package Stocks.JsonModel;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MetaData {
    @JsonProperty("1. Information")
    private String information;

    @JsonProperty("2. Symbol")
    private String symbol;

    @JsonProperty("3. Last Refreshed")
    private String lastRefreshed;

    @JsonProperty("4. Output Size")
    private String outputSize;

    @JsonProperty("5. Time Zone")
    private String timeZone;

    // Getter method for 'Information'
    public String getInformation() {
        return information;
    }

    // Setter method for 'Information'
    public void setInformation(String Information) {
        this.information = Information;
    }

    // Getter method for 'Symbol'
    public String getSymbol() {
        return symbol;
    }

    // Setter method for 'Symbol'
    public void setSymbol(String Symbol) {
        this.symbol = Symbol;
    }

    // Getter method for 'LastRefreshed'
    public String getLastRefreshed() {
        return lastRefreshed;
    }

    // Setter method for 'LastRefreshed'
    public void setLastRefreshed(String LastRefreshed) {
        this.lastRefreshed = LastRefreshed;
    }

    // Getter method for 'OutputSize'
    public String getOutputSize() {
        return outputSize;
    }

    // Setter method for 'OutputSize'
    public void setOutputSize(String OutputSize) {
        this.outputSize = OutputSize;
    }

    // Getter method for 'TimeZone'
    public String getTimeZone() {
        return timeZone;
    }

    // Setter method for 'TimeZone'
    public void setTimeZone(String TimeZone) {
        this.timeZone = TimeZone;
    }
}
