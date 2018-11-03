package com.company.Entity;

/**
 * Created by atomic on 6/14/2017.
 */
public class ExchangeEntity {
    private String ExchangeId;
    private String ExchangeGlobalId;
    private String MIC;
    private String ExchangeName;
    private String CountryId;
    private String CountryName;
    private String RegionId;
    private String RegionName;

    public String getExchangeId() {
        return ExchangeId;
    }

    public void setExchangeId(String exchangeId) {
        ExchangeId = exchangeId;
    }

    public String getExchangeGlobalId() {
        return ExchangeGlobalId;
    }

    public void setExchangeGlobalId(String exchangeGlobalId) {
        ExchangeGlobalId = exchangeGlobalId;
    }

    public String getMIC() {
        return MIC;
    }

    public void setMIC(String MIC) {
        this.MIC = MIC;
    }

    public String getExchangeName() {
        return ExchangeName;
    }

    public void setExchangeName(String exchangeName) {
        ExchangeName = exchangeName;
    }

    public String getCountryId() {
        return CountryId;
    }

    public void setCountryId(String countryId) {
        CountryId = countryId;
    }

    public String getCountryName() {
        return CountryName;
    }

    public void setCountryName(String countryName) {
        CountryName = countryName;
    }

    public String getRegionId() {
        return RegionId;
    }

    public void setRegionId(String regionId) {
        RegionId = regionId;
    }

    public String getRegionName() {
        return RegionName;
    }

    public void setRegionName(String regionName) {
        RegionName = regionName;
    }
}
