package com.example.tungvu.wardriving.Objects;

import java.io.Serializable;

public class Sample implements Serializable {
	private String mac;
    private String ssid;
    private int level;
    private int id_sample;

    public Sample(String mac, String ssid, int level, int id_sample){
        this.mac = mac;
        this.ssid = ssid;
        this.level = level;
        this.id_sample = id_sample;
    }

    public String getMac() {
        return mac;
    }

    public String getSsid() {
        return ssid;
    }

    public int getLevel() {
        return level;
    }

    public int getId_sample(){return id_sample; }
}
