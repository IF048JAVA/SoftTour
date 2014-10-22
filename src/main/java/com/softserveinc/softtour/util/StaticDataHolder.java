package com.softserveinc.softtour.util;

import java.util.HashMap;
import java.util.Map;

public class StaticDataHolder {
    private StaticDataHolder dataHolder;
    private Map<Integer, String> countryMap = new HashMap<>(); //<countryName, countryCode>
    private Map<Integer, String> regionMap = new HashMap<>(); //<regionName, regionCode>
    private Map<Integer, String> hotelMap = new HashMap<>(); // <hotelName, hotelCode>
    private Map<Integer, Integer> regionCountryRelatedMap = new HashMap<>(); //<countryCode, regionCode>
    private Map<Integer, Integer> hotelRegionRelatedMap = new HashMap<>(); //<regionCode, hotelCode>

    public StaticDataHolder(){}

    public void saveCountry(int countryCode, String countryName){
        countryMap.put(countryCode, countryName);
    }

    public void saveRegion(int countryCode, int regionCode, String regionName){
        regionMap.put(regionCode, regionName);
        regionCountryRelatedMap.put(regionCode, countryCode);
    }

    public void saveHotel(int regionCode, int hotelCode, String hotelName){
        hotelMap.put(hotelCode, hotelName);
        hotelRegionRelatedMap.put(hotelCode, regionCode);
    }

    public Map<Integer, String> getCountryMap() {
        return countryMap;
    }

    public Map<Integer, String> getRegionMap() {
        return regionMap;
    }

    public Map<Integer, String> getHotelMap() {
        return hotelMap;
    }

    public Map<Integer, Integer> getRegionCountryRelatedMap() {
        return regionCountryRelatedMap;
    }

    public Map<Integer, Integer> getHotelRegionRelatedMap() {
        return hotelRegionRelatedMap;
    }
}
