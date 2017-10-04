package objects;

public class Position {
	private int id;
	private float x, y;
	private boolean isSurvey;
	public Position(int id, float x, float y, boolean isSurvey){
		this.id = id;
		this.x = x;
		this.y = y;
		this.isSurvey = isSurvey;
	}
	public int getId() {
		return id;
	}
	public float getX() {
		return x;
	}
	public float getY() {
		return y;
	}
	public boolean isSurvey() {
		return isSurvey;
	}
	
}
