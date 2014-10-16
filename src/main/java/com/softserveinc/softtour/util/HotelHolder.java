package com.softserveinc.softtour.util;

import java.util.HashMap;
import java.util.Map;

public class HotelHolder {
    private static HotelHolder hotelHolder;
    private static Map<String, String> hotelMap = new HashMap<>();

    private HotelHolder(){

    }

    public static HotelHolder getInstance(){
        if(hotelHolder == null){
            return new HotelHolder();
        } else return hotelHolder;
    }

    public void putHotel(String key, String value){
        hotelMap.put(key, value);
    }

    public String getHotelPicture(String key){
        return hotelMap.get(key);
    }

    public boolean containsHotel(String key){
        if(hotelMap.containsKey(key)){
            return true;
        } else return false;
    }
}
