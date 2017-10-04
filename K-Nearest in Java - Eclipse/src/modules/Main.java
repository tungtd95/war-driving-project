package modules;

import java.util.ArrayList;
import java.util.Collections;

import objects.APData;
import objects.Distance;

public class Main {
	public static void main(String[] args) {
		// int k_mean =5;
		DBC dbc = DBC.getInstance();
		ArrayList<APData> listSurvey, listTest = new ArrayList<>();
		listSurvey = dbc.getAPDataSurvey();
		listTest = dbc.getAPDataTest();
		System.out.println(listSurvey.size() + " " + listTest.size());
		for (int k_mean = 1; k_mean < 15; k_mean++) {
			ArrayList<Float> listAcurracy = new ArrayList<>();
			for (int test = 0; test < listTest.size(); test++) {
				float dist = 0;
				ArrayList<Distance> listDistance = new ArrayList<>();
				Distance distance;
				for (int sur = 0; sur < listSurvey.size(); sur++) {
					dist = (float) Math
							.sqrt(0 
									+ Math.pow(listSurvey.get(sur).getP_mac1() - listTest.get(test).getP_mac1(), 2)
									+ Math.pow(listSurvey.get(sur).getP_mac2() - listTest.get(test).getP_mac2(), 2)
									+ Math.pow(listSurvey.get(sur).getP_mac3() - listTest.get(test).getP_mac3(), 2)
									+ Math.pow(listSurvey.get(sur).getP_mac4() - listTest.get(test).getP_mac4(), 2)
									+ Math.pow(listSurvey.get(sur).getP_mac5() - listTest.get(test).getP_mac5(), 2)
									+ Math.pow(listSurvey.get(sur).getP_mac6() - listTest.get(test).getP_mac6(), 2)
									+ Math.pow(listSurvey.get(sur).getP_mac7() - listTest.get(test).getP_mac7(), 2)
//									+ Math.pow(listSurvey.get(sur).getP_mac8() - listTest.get(test).getP_mac8(), 2)
									);
					distance = new Distance(listSurvey.get(sur).getX(), listSurvey.get(sur).getY(), dist);
					listDistance.add(distance);
				}
				Collections.sort(listDistance);
				float x_aver = 0;
				float y_aver = 0;
				for (int i = 0; i < k_mean; i++) {
					x_aver += listDistance.get(i).getX();
					y_aver += listDistance.get(i).getY();
				}
				x_aver = x_aver / (float) k_mean;
				y_aver = y_aver / (float) k_mean;
				float acurracy = (float) Math.sqrt(Math.pow(x_aver - listTest.get(test).getX(), 2)
						+ Math.pow(y_aver - listTest.get(test).getY(), 2));
//				System.out.println("acurracy = " + acurracy);
				listAcurracy.add(acurracy);
			}
			float sum = 0;
			for (int i = 0; i < listAcurracy.size(); i++) {
				sum += listAcurracy.get(i);
			}
			System.out.println(sum / listAcurracy.size());

		}

		// ArrayList<APs> listAP = dbc.getAPs();
		// System.out.println("listAP size = "+listAP.size());
		// for(int i=0; i<listAP.size(); i++){
		// dbc.getSizeWithAP(listAP.get(i).getMac(), listAP.get(i).getSsid());
		// }
		// ArrayList<Position> list = dbc.getSurveyPosition();
		// float level1[] = new float[8];
		// System.out.println(list.size());
		// for(int i=0; i<list.size(); i++){
		// for(int j=0; j<listAP.size();j++){
		// float level = dbc.getLevel(list.get(i).getId(),
		// listAP.get(j).getMac());
		// System.out.println(listAP.get(j).getMac()+" "+level);
		// level1[j] = level;
		// }
		//
		// System.out.println("i = "+i+" \n");
		// dbc.insertToApDataSurVey(list.get(i).getX(), list.get(i).getY(),
		// level1);
		// for(int i1=0; i1<8; i1++){
		// System.out.print(level1[i1]+" ");
		// }
		//
		// }
	}
}
