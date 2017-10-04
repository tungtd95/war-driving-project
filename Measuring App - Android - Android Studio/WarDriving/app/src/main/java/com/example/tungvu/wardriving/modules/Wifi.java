package com.example.tungvu.wardriving.modules;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.util.Log;

import com.example.tungvu.wardriving.Objects.AccessPoint;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tung on 3/1/17.
 */

public class Wifi {
    WifiManager wifiManager;
    static Activity activity;

    public Wifi(Activity activity){
        this.activity = activity;
        wifiManager = (WifiManager) activity.getSystemService(Context.WIFI_SERVICE);
    }

    public ArrayList<AccessPoint> getListWifi(){
        ArrayList<AccessPoint> listWifi = new ArrayList<>();
        List<ScanResult> s ;
        try{
            wifiManager.startScan();
            s = wifiManager.getScanResults();
            for (int i = 0; i<s.size(); i++){
                listWifi.add(new AccessPoint(s.get(i).SSID, s.get(i).BSSID, s.get(i).level));
            }
    //        Log.i("Main", "listWifi.size = "+listWifi.size());
        }catch (Exception e1){
            Log.i("Wifi", "getListWifi error = "+e1.toString());
        }
        return listWifi;
    }
}
