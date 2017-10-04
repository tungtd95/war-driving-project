package modules;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import objects.APsimulator;

import java.awt.Color;

public class SOMSimulator extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	DBC dbc;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SOMSimulator frame = new SOMSimulator();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SOMSimulator() {
		setTitle("SOM Simulator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 700);
		this.setLocation(250, 10);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		dbc = DBC.getInstance();
		
		//khoi tao gia tri ban dau cho cac toa do (gia su chua biet toa do, da biet gia tri cua cac APs)
		ArrayList<APsimulator> listAPSimulator = dbc.getAPsimulator();
		
		MyPanel myPanel = new MyPanel(listAPSimulator);
		myPanel.setBackground(Color.LIGHT_GRAY);
		contentPane.add(myPanel, BorderLayout.CENTER);
		
	}
	
	public class MyPanel extends JPanel{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private int x = 100, y = 100;
		private static final int h = 25;
		ArrayList<APsimulator> listAP;
		
		public MyPanel(ArrayList<APsimulator> listAPSimulator) {
			// TODO Auto-generated constructor stub
			this.listAP = listAPSimulator;
		}
		@Override
		protected void paintComponent(Graphics g) {
			// TODO Auto-generated method stub
			super.paintComponent(g);
			g.setColor(Color.RED);
			for(int i=0; i<listAP.size(); i++){
				this.x = listAP.get(i).getX0();
				this.y = listAP.get(i).getY0();
				g.drawOval(x, y, h, h);
				g.fillOval(x, y, h, h);
			}
		}
	}

}
