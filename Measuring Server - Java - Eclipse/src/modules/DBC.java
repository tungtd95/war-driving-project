package modules;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.example.tungvu.wardriving.Objects.Sample;
import com.example.tungvu.wardriving.Objects.SendToServer;

public class DBC {
	Connection connection;
	Statement statement;
	ResultSet resultSet;
	public DBC(){
		try{
			connection = DriverManager.getConnection("jdbc:mysql://localhost:"
                    + "3306/war_driving?useSSL=false", "root", "Javafirst");
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	public void addRawData(SendToServer sendToServer, int id_thread){
		int id_sample, id_position;
		float x, y;
		boolean isSurvey = sendToServer.isSurvey();
		int is_s;
		ArrayList<Sample> listSample = sendToServer.getListSample();
		Sample sample;
	
		if(isSurvey) is_s = 1;
		else is_s = 0;
		x = sendToServer.getX();
		y = sendToServer.getY();
		//add position
		id_position = addPosition(x, y, is_s);
		
		System.out.println("Updating database");
		int percentage = 0;
		for(int i=0; i<listSample.size(); i++){
			
			int percentage_current = i*100/listSample.size();
			if(percentage_current>percentage){
				percentage = percentage_current;
				System.out.print("="+id_thread);
			}
			
			sample = listSample.get(i);
			//add access point
			addAPs(sample);
			
			//insert into signal
			String signal = "SELECT * FROM war_driving.`signal` "
					+ "where id_sample = "+sample.getId_sample()+" and"
					+ " id_position = "+id_position+" and"
					+ " mac = \""+sample.getMac()+"\";";
			try{
				statement = connection.createStatement();
				resultSet = statement.executeQuery(signal);
				if(resultSet.next()){
					String edit_signal = "UPDATE `war_driving`.`signal` "
							+ "SET `level`='"+sample.getLevel()+"' "
							+ "WHERE `id_sample`='"+sample.getId_sample()+"'"
							+ " and `id_position`='"+id_position+"' "
							+ "and `mac`='"+sample.getMac()+"';";
					if(sample.getLevel()!=resultSet.getInt("level")){

						statement.execute(edit_signal);
					}	
				}else{
					String insert_signal = "INSERT INTO `war_driving`.`signal` "
							+ "(`id_sample`, `id_position`, `mac`, `level`) "
							+ "VALUES ('"+sample.getId_sample()+"', '"+id_position+"', "
									+ "'"+sample.getMac()+"', '"+sample.getLevel()+"');";
					boolean check = statement.execute(insert_signal);
				}
			}catch(SQLException e){
			}
			
		}
		System.out.println("\nDatabase updated");
		
	}
	public void addAPs(Sample sample){

		String aps = "INSERT INTO `war_driving`.`ap` "
				+ "(`mac`, `ssid`) VALUES "
				+ "('"+sample.getMac()+"', '"+sample.getSsid()+"');";
		try{
			statement = connection.createStatement();
			statement.execute(aps);
		}catch(SQLException e){
		}
	}
	public int addPosition(float x, float y, int is_s){
		int id_position=-1;
		String query = "SELECT * FROM war_driving.position where x="+x+
				" and y = "+y+" and isSurvey = "+is_s+";";
		try{
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			//check if (X,Y) has existed yet
			if(resultSet.next()){
				id_position = resultSet.getInt("id_position");
				System.out.println("Position existed");
			}else{
				//if not, then add (X,Y)
				String query2 = "INSERT INTO `war_driving`.`position` (`x`, `y`, `isSurvey`) "
						+ "VALUES ('"+x+"', '"+y+"', '"+is_s+"');";
				statement.execute(query2);
				resultSet = statement.executeQuery(query);
				if(resultSet.next()){
					id_position = resultSet.getInt("id_position");
				}
				System.out.println("Position is added");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return id_position;
	}
}
