package objects;

public class Distance implements Comparable<Object>{
	float x,y,distance;
	public Distance(float x, float y, float distance){
		this.x = x;
		this.y = y;
		this.distance = distance;
	}
	public float getX() {
		return x;
	}
	public float getY() {
		return y;
	}
	public float getDistance() {
		return distance;
	}
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		Distance other = (Distance) o;
		if(this.distance>other.distance) return 1;
		else if(this.distance<other.distance) return -1;
		return 0;
	}
	
}
