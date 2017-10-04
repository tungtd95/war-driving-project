package com.example.tungvu.wardriving.Objects;

/**
 * Created by tung on 3/1/17.
 */

public class AccessPoint implements Comparable{
    private String SSID;
    private String MAC;
    private int LEVEL;
    public AccessPoint(String ssid, String mac, int level){
        this.SSID = ssid;
        this.MAC = mac;
        this.LEVEL = level;
    }

    public String getSSID() {
        return SSID;
    }

    public String getMAC() {
        return MAC;
    }

    public int getLevel() {
        return LEVEL;
    }

    @Override
    public String toString() {
        return "SSID: "+SSID+"\n"+"MAC: "+MAC+"\n"+"Level: "+LEVEL+ " dBm";
    }

    @Override
    public int compareTo(Object o) {
        AccessPoint other = (AccessPoint) o;
        if(this.LEVEL>other.LEVEL) return -1;
        else if(this.LEVEL<other.LEVEL) return 1;
        else return 0;
    }
}
