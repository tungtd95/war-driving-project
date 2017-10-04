package objects;

public class APs {
	private String mac;
	private String ssid;
	public APs(String mac, String ssid){
		this.mac = mac;
		this.ssid = ssid;
	}
	public String getMac(){
		return mac;
	}
	public String getSsid(){
		return ssid;
	}
}
