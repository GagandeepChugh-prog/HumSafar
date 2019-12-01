package com.inducesmile.humsafar;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;


public class LocationChecker {
    public Double[] getLocationFromAddress(Context context, String strAddress) {

        Geocoder coder = new Geocoder(context);
        List<Address> address;
        Double[] loc = new Double[2];
        String longi="";
        String lati="";
        try {
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return null;
            }
            Address location = address.get(0);
            loc[0] =location.getLatitude();
            loc[1] =location.getLongitude();


            //lati=lat.toString();
            //longi=lng.toString();

        } catch (Exception ex) {

            ex.printStackTrace();
        }


        return loc;
    }

}
