package modules;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import objects.APData;
import objects.APsimulator;

public class DBC {
	private Connection connection;
	Statement statement;
	ResultSet resultSet;
	
	private static DBC dbc = null;
	public DBC(){
		try{
			connection = DriverManager.getConnection("jdbc:mysql://localhost:"
                    + "3306/war_driving_analysis?useSSL=false", "root", "Needtocode");
		}catch (SQLException e) {
			e.toString();
		}
	}
	public static DBC getInstance(){
		if(dbc==null){
			dbc = new DBC();
		}
		return dbc;
	}
	
//	public ArrayList<Position> getSurveyPosition(){
//		ArrayList<Position> listPosition = new ArrayList<>();
//		String query = "SELECT * FROM war_driving.position where isSurvey=0;";
//		try{
//			statement = connection.createStatement();
//			resultSet = statement.executeQuery(query);
//			Position p;
//			while(resultSet.next()){
//				p = new Position(resultSet.getInt("id_position"), 
//resultSet.getFloat("x"), resultSet.getFloat("y"), resultSet.getBoolean("isSurvey"));
//				listPosition.add(p);
//			}
//		}catch (SQLException e) {
//			// TODO: handle exception
//			e.toString();
//			return null;
//		}
//		return listPosition;
//	}
//	public ArrayList<APs> getAPs(){
//		ArrayList<APs> listAP = new ArrayList<>();
//		String query ="SELECT * FROM war_driving_analysis.ap_valid;";
//		try{
//			statement = connection.createStatement();
//			resultSet = statement.executeQuery(query);
//			APs ap;
//			while(resultSet.next()){
//				ap = new APs(resultSet.getString("mac"), resultSet.getString("ssid"));
//				listAP.add(ap);
//			}
//		}catch (SQLException e) {
//			// TODO: handle exception
//			e.toString();
//			return null;
//		}
//		return listAP;
//	}
	public ArrayList<APData> getAPDataSurvey(){
		ArrayList<APData> list = new ArrayList<>();
		String query = "SELECT * FROM war_driving_analysis.ap_data_survey;";
		try{
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			APData ap;
			while(resultSet.next()){
				ap = new APData(resultSet.getFloat("x"),resultSet.getFloat("y"),resultSet.getFloat("p_mac1"),
						resultSet.getFloat("p_mac2"),resultSet.getFloat("p_mac3"),resultSet.getFloat("p_mac4"),
						resultSet.getFloat("p_mac5"),resultSet.getFloat("p_mac6"),resultSet.getFloat("p_mac7"),
						resultSet.getFloat("p_mac8"));
				list.add(ap);
			}
		}catch (SQLException e) {
			// TODO: handle exception
			e.toString();
			return null;
		}
		return list;
	}
	public ArrayList<APsimulator> getAPsimulator(){
		ArrayList<APsimulator> list = new ArrayList<>();
		String query = "SELECT * FROM war_driving_analysis.ap_data_survey;";
		try{
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			APsimulator ap;
			while(resultSet.next()){
				ap = new APsimulator(resultSet.getFloat("x"),resultSet.getFloat("y"),resultSet.getFloat("p_mac1"),
						resultSet.getFloat("p_mac2"),resultSet.getFloat("p_mac3"),resultSet.getFloat("p_mac4"),
						resultSet.getFloat("p_mac5"),resultSet.getFloat("p_mac6"),resultSet.getFloat("p_mac7"),
						resultSet.getFloat("p_mac8"));
				list.add(ap);
			}
		}catch (SQLException e) {
			// TODO: handle exception
			e.toString();
			return null;
		}
		return list;
	}
	public ArrayList<APData> getAPDataTest(){
		ArrayList<APData> list = new ArrayList<>();
		String query = "SELECT * FROM war_driving_analysis.ap_data_test;";
		try{
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			APData ap;
			while(resultSet.next()){
				ap = new APData(resultSet.getFloat("x"),resultSet.getFloat("y"),resultSet.getFloat("p_mac1"),
						resultSet.getFloat("p_mac2"),resultSet.getFloat("p_mac3"),resultSet.getFloat("p_mac4"),
						resultSet.getFloat("p_mac5"),resultSet.getFloat("p_mac6"),resultSet.getFloat("p_mac7"),
						resultSet.getFloat("p_mac8"));
				list.add(ap);
			}
		}catch (SQLException e) {
			// TODO: handle exception
			e.toString();
			return null;
		}
		return list;
	}
//	public float getLevel(int id_position, String mac){
//		int result = 0;
//		String query = "SELECT * FROM war_driving.`signal` where id_position = "+id_position+
//				" and mac = '"+mac+"';";
//		try{
//			statement = connection.createStatement();
//			resultSet = statement.executeQuery(query);
//			while(resultSet.next()){
//				result+=resultSet.getInt("level");
//			}
//		}catch (SQLException e) {
//			// TODO: handle exception
//			e.toString();
//		}
//		return result/(float)100;
//	}
//	public void insertToApDataSurVey(float x, float y, float l[]){
//		String query = "INSERT INTO `war_driving_analysis`.`ap_data_test` "
//				+ "(`x`, `y`, `p_mac1`, `p_mac2`, `p_mac3`, `p_mac4`, `p_mac5`, `p_mac6`, `p_mac7`, `p_mac8`) "
//				+ "VALUES ('"+x+"', '"+y+"', '"+l[0]+"', '"+l[1]+"', '"+
//				l[2]+"', '"+l[3]+"', '"+l[4]+"', '"+l[5]+"', '"+l[6]+"', '"+l[7]+"');";
//		try{
//			statement = connection.createStatement();
//			statement.execute(query);
//		}catch (SQLException e) {
//			// TODO: handle exception
//			e.toString();
//		}
//	}
//	public void getSizeWithAP(String mac, String ssid){
//		String query = "SELECT * FROM war_driving.`signal` where mac = '"+mac+"';";
//		try{
//			statement = connection.createStatement();
//			resultSet = statement.executeQuery(query);
//			int count = 0;
//			while(resultSet.next()){
//				count++;
//			}
//			if(count>=10900){
//				String query2 = "INSERT INTO `war_driving_analysis`.`ap_valid` "
//						+ "(`mac`, `ssid`) VALUES('"+
//						mac+"', '"+
//						ssid+"');";
//				statement = connection.createStatement();
//				statement.execute(query2);
//				 System.out.println("AP "+ssid+" sample quantity = "+count);
//			}
//		}catch (SQLException e) {
//			// TODO: handle exception
//			e.toString();
//		}
//	}
}
