package objects;

import java.util.Random;

public class APsimulator extends APData{
	
	private int x0, y0;

	public APsimulator(float x, float y, float p_mac1, float p_mac2, float p_mac3, float p_mac4, float p_mac5,
			float p_mac6, float p_mac7, float p_mac8) {
		super(x, y, p_mac1, p_mac2, p_mac3, p_mac4, p_mac5, p_mac6, p_mac7, p_mac8);
		// TODO Auto-generated constructor stub
		Random r = new Random();
		this.x0 = r.nextInt(150)+150;
		this.y0 = r.nextInt(150)+150;
	}

	public int getX0() {
		return x0;
	}

	public void setX0(int x0) {
		this.x0 = x0;
	}

	public int getY0() {
		return y0;
	}

	public void setY0(int y0) {
		this.y0 = y0;
	}
	
	

}
