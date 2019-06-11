package test_GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.tools.Tool;

import test_GUI.test.WindowHandler;

import javax.swing.JDesktopPane;
import javax.swing.JFormattedTextField;
import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JToolBar;

public class GUI extends JFrame {

	private JPanel contentPane;
	private JTextField Account;
	private String account;
	private String password;
	private JPasswordField Password;
	private boolean flag = false;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
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
	public GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1101, 578);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.WHITE);
		contentPane.add(desktopPane, BorderLayout.CENTER);
		desktopPane.setLayout(null);
		
		JLayeredPane Login = new JLayeredPane();
		Login.setBounds(223, 182, 649, 151);
		desktopPane.add(Login);
		
		JLabel label = new JLabel("\u5E33\u865F");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(168, 13, 151, 36);
		Login.add(label);
		
		Account = new JTextField();
		Account.setColumns(10);
		Account.setBounds(333, 13, 151, 36);
		Login.add(Account);
		
		JLabel label_1 = new JLabel("\u5BC6\u78BC");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(168, 62, 151, 36);
		Login.add(label_1);
		
		JToolBar ToolBar = new JToolBar();
		ToolBar.setBounds(406, 43, 294, 121);
		desktopPane.add(ToolBar);
		ToolBar.setVisible(false);
		
		JButton Search = new JButton("\u67E5\u8A62\u65C5\u9928");
		Search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(flag == false) {
					flag = true;
					JInternalFrame inf = new JInternalFrame();
					inf = new JInternalFrame("�ж��d�� ",true,true,true,true);
					
					inf.setBounds(100, 100, 1054, 485);
				    inf.setVisible(true);
				    desktopPane.add(inf);
				    Container icp=inf.getContentPane();
				    icp.setLayout(null);
					JScrollPane scrollPane = new JScrollPane();
					scrollPane.setBounds(14, 26, 452, 388);
					icp.add(scrollPane);
					
					JTextArea textArea = new JTextArea();
					scrollPane.setViewportView(textArea);
					
					JLabel label = new JLabel("\u5165\u4F4F\u65E5\u671F");
					label.setFont(new Font("�s�ө���", Font.PLAIN, 22));
					label.setBounds(480, 26, 118, 64);
					icp.add(label);
					
					JFormattedTextField EnterYear = new JFormattedTextField();
					EnterYear.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent arg0) {
							EnterYear.setText("");
						}
					});
					EnterYear.setText("\u5E74");
					EnterYear.setBounds(596, 41, 45, 40);
					icp.add(EnterYear);
					
					JLabel label_1 = new JLabel("/");
					label_1.setBounds(655, 38, 26, 43);
					icp.add(label_1);
					
					JFormattedTextField EnterMonth = new JFormattedTextField();
					EnterMonth.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							EnterMonth.setText("");
						}
					});
					EnterMonth.setText("\u6708");
					EnterMonth.setBounds(671, 41, 45, 40);
					icp.add(EnterMonth);
					
					JLabel label_2 = new JLabel("/");
					label_2.setBounds(728, 38, 26, 43);
					icp.add(label_2);
					
					JFormattedTextField EnterDay = new JFormattedTextField();
					EnterDay.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							EnterDay.setText("");
						}
					});
					EnterDay.setText("\u65E5");
					EnterDay.setBounds(741, 41, 45, 40);
					icp.add(EnterDay);
					
					JFormattedTextField OutDay = new JFormattedTextField();
					OutDay.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							OutDay.setText("");
						}
					});
					OutDay.setText("\u65E5");
					OutDay.setBounds(741, 94, 45, 40);
					icp.add(OutDay);
					
					JLabel label_3 = new JLabel("/");
					label_3.setBounds(728, 91, 26, 43);
					icp.add(label_3);
					
					JFormattedTextField OutMonth = new JFormattedTextField();
					OutMonth.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							OutMonth.setText("");
						}
					});
					OutMonth.setText("\u6708");
					OutMonth.setBounds(671, 94, 45, 40);
					icp.add(OutMonth);
					
					JLabel label_4 = new JLabel("/");
					label_4.setBounds(655, 91, 26, 43);
					icp.add(label_4);
					
					JFormattedTextField OutYear = new JFormattedTextField();
					OutYear.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							OutYear.setText("");
						}
					});
					OutYear.setText("\u5E74");
					OutYear.setBounds(596, 94, 45, 40);
					icp.add(OutYear);
					
					JLabel label_5 = new JLabel("\u9000\u623F\u65E5\u671F");
					label_5.setFont(new Font("�s�ө���", Font.PLAIN, 22));
					label_5.setBounds(480, 89, 108, 45);
					icp.add(label_5);
					
					JLabel label_6 = new JLabel("\u5165\u4F4F\u4EBA\u6578");
					label_6.setFont(new Font("�s�ө���", Font.PLAIN, 22));
					label_6.setBounds(480, 141, 108, 45);
					icp.add(label_6);
					
					JSpinner People = new JSpinner();
					People.setBounds(596, 141, 190, 42);
					icp.add(People);
					
					JLabel label_7 = new JLabel("\u661F\u7D1A");
					label_7.setFont(new Font("�s�ө���", Font.PLAIN, 22));
					label_7.setBounds(480, 199, 57, 45);
					icp.add(label_7);
					
					JSpinner Star = new JSpinner();
					Star.setBounds(596, 196, 190, 42);
					icp.add(Star);
					
					JCheckBox Agree = new JCheckBox("\u540C\u610F\u4F7F\u7528\u8005\u689D\u6B3E");
					Agree.setBounds(480, 369, 190, 45);
					icp.add(Agree);
					
					JSpinner QuadRoom = new JSpinner();
					QuadRoom.setBounds(862, 287, 120, 42);
					icp.add(QuadRoom);
					
					JCheckBox Quad = new JCheckBox("\u56DB\u4EBA");
					Quad.setBounds(864, 251, 115, 27);
					icp.add(Quad);
					
					JSpinner DoubleRoom = new JSpinner();
					DoubleRoom.setBounds(728, 287, 120, 42);
					icp.add(DoubleRoom);
					
					JCheckBox Double = new JCheckBox("\u96D9\u4EBA");
					Double.setBounds(730, 251, 115, 27);
					icp.add(Double);
					
					JSpinner SingleRoom = new JSpinner();
					SingleRoom.setBounds(596, 287, 120, 42);
					icp.add(SingleRoom);
					
					JCheckBox Single = new JCheckBox("\u55AE\u4EBA");
					Single.setBounds(598, 251, 115, 27);
					icp.add(Single);
					
					JLabel label_8 = new JLabel("\u5BA2\u623F\u6578\u91CF");
					label_8.setFont(new Font("�s�ө���", Font.PLAIN, 22));
					label_8.setBounds(480, 252, 108, 45);
					icp.add(label_8);
					
					JButton Send = new JButton("\u78BA\u8A8D");
					
					Send.setBounds(667, 365, 190, 49);
					icp.add(Send);
					
					Send.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if (Agree.isSelected()) {	
								textArea.append("Account: " + account + "\r\n");
								textArea.append("Password: " + password + "\r\n");
								textArea.append("Check-in Day: " + EnterYear.getText() + "/" + EnterMonth.getText() + "/" + EnterDay.getText() + "\r\n");
								textArea.append("Check-out Day: " + OutYear.getText() + "/" + OutMonth.getText() + "/" + OutDay.getText() + "\r\n");
								textArea.append("Nums of persons: " + People.getValue()+"\r\n");
								textArea.append("Required star: " + Star.getValue()+"\r\n");
								if(Single.isSelected()) {
									textArea.append("Single room: " + SingleRoom.getValue()+"\r\n");
								}
								if(Double.isSelected()) {
									textArea.append("Double room: " + DoubleRoom.getValue()+"\r\n");
								}
								if(Quad.isSelected()) {
									textArea.append("Quad room: " + QuadRoom.getValue()+"\r\n");
								}
							}
							else {
								textArea.setText("You don't agree."+"\r\n");
							}
						}
					});
					inf.addInternalFrameListener(new WindowHandler()); 
				}
			}
		});
		ToolBar.add(Search);
		
		JButton Reserve = new JButton("\u9810\u5B9A\u65C5\u9928");
		Reserve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (flag == false) {
					flag = true;
					
					JInternalFrame inf = new JInternalFrame();
					inf = new JInternalFrame("�w�q�ж� ",true,true,true,true);
					
					inf.setBounds(100, 100, 1055, 479);
				    inf.setVisible(true);
				    desktopPane.add(inf);
				    Container icp=inf.getContentPane();
				    icp.setLayout(null);
					
					JLabel label = new JLabel("\u65C5\u9928\u7DE8\u865F");
					label.setHorizontalAlignment(SwingConstants.CENTER);
					label.setFont(new Font("�s�ө���", Font.PLAIN, 22));
					label.setBounds(409, 123, 149, 43);
					icp.add(label);
					
					JFormattedTextField HotelID = new JFormattedTextField();
					HotelID.setBounds(572, 120, 149, 43);
					icp.add(HotelID);
					
					JLabel label_1 = new JLabel("\u5165\u4F4F\u65E5\u671F");
					label_1.setFont(new Font("�s�ө���", Font.PLAIN, 22));
					label_1.setBounds(432, 179, 118, 64);
					icp.add(label_1);
					
					JFormattedTextField EnterYear = new JFormattedTextField();
					EnterYear.setText("\u5E74");
					EnterYear.setBounds(548, 194, 45, 40);
					icp.add(EnterYear);
					
					JLabel label_2 = new JLabel("/");
					label_2.setBounds(607, 191, 26, 43);
					icp.add(label_2);
					
					JFormattedTextField EnterMonth = new JFormattedTextField();
					EnterMonth.setText("\u6708");
					EnterMonth.setBounds(623, 194, 45, 40);
					icp.add(EnterMonth);
					
					JLabel label_3 = new JLabel("/");
					label_3.setBounds(680, 191, 26, 43);
					icp.add(label_3);
					
					JFormattedTextField EnterDay = new JFormattedTextField();
					EnterDay.setText("\u65E5");
					EnterDay.setBounds(693, 194, 45, 40);
					icp.add(EnterDay);
					
					JLabel label_4 = new JLabel("\u9000\u623F\u65E5\u671F");
					label_4.setFont(new Font("�s�ө���", Font.PLAIN, 22));
					label_4.setBounds(432, 242, 108, 45);
					icp.add(label_4);
					
					JFormattedTextField OutYear = new JFormattedTextField();
					OutYear.setText("\u5E74");
					OutYear.setBounds(548, 247, 45, 40);
					icp.add(OutYear);
					
					JLabel label_5 = new JLabel("/");
					label_5.setBounds(607, 244, 26, 43);
					icp.add(label_5);
					
					JFormattedTextField OutMonth = new JFormattedTextField();
					OutMonth.setText("\u6708");
					OutMonth.setBounds(623, 247, 45, 40);
					icp.add(OutMonth);
					
					JLabel label_6 = new JLabel("/");
					label_6.setBounds(680, 244, 26, 43);
					icp.add(label_6);
					
					JFormattedTextField OutDay = new JFormattedTextField();
					OutDay.setText("\u65E5");
					OutDay.setBounds(693, 247, 45, 40);
					icp.add(OutDay);
					
					JCheckBox Agree = new JCheckBox("\u540C\u610F\u4F7F\u7528\u8005\u689D\u6B3E");
					Agree.setBounds(442, 329, 190, 45);
					icp.add(Agree);
					
					JButton Send = new JButton("\u78BA\u8A8D");
					Send.setBounds(652, 327, 190, 49);
					icp.add(Send);
					
					JTextArea textArea = new JTextArea();
					textArea.setBounds(29, 121, 364, 286);
					icp.add(textArea);
					
					JLabel label_7 = new JLabel("\u5165\u4F4F\u623F\u9593");
					label_7.setHorizontalAlignment(SwingConstants.CENTER);
					label_7.setFont(new Font("�s�ө���", Font.PLAIN, 22));
					label_7.setBounds(735, 123, 149, 43);
					icp.add(label_7);
					
					JCheckBox Single = new JCheckBox("\u55AE\u4EBA");
					Single.setBounds(893, 120, 115, 27);
					icp.add(Single);
					
					JSpinner SingleRoom = new JSpinner();
					SingleRoom.setBounds(891, 156, 120, 42);
					icp.add(SingleRoom);
					
					JCheckBox Double = new JCheckBox("\u96D9\u4EBA");
					Double.setBounds(893, 211, 115, 27);
					icp.add(Double);
					
					JSpinner DoubleRoom = new JSpinner();
					DoubleRoom.setBounds(891, 247, 120, 42);
					icp.add(DoubleRoom);
					
					JCheckBox Quad = new JCheckBox("\u56DB\u4EBA");
					Quad.setBounds(893, 298, 115, 27);
					icp.add(Quad);
					
					JSpinner QuadRoom = new JSpinner();
					QuadRoom.setBounds(891, 334, 120, 42);
					icp.add(QuadRoom);
					Send.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if (Agree.isSelected()) {	
								textArea.append("Account: " + account + "\r\n");
								textArea.append("Password: " + password + "\r\n");
								textArea.append("Check-in Day: " + EnterYear.getText() + "/" + EnterMonth.getText() + "/" + EnterDay.getText() + "\r\n");
								textArea.append("Check-out Day: " + OutYear.getText() + "/" + OutMonth.getText() + "/" + OutDay.getText() + "\r\n");
								textArea.append("Hotel ID: " + HotelID.getText()+"\r\n");
								if(Single.isSelected()) {
									textArea.append("Single room: " + SingleRoom.getValue()+"\r\n");
								}
								if(Double.isSelected()) {
									textArea.append("Double room: " + DoubleRoom.getValue()+"\r\n");
								}
								if(Quad.isSelected()) {
									textArea.append("Quad room: " + QuadRoom.getValue()+"\r\n");
								}
							}
							else {
								textArea.setText("You don't agree."+"\r\n");
							}
						}
					});
					inf.addInternalFrameListener(new WindowHandler()); 
				}
			}
		});
		ToolBar.add(Reserve);
		
		JButton Modify = new JButton("\u4FEE\u6539\u8A02\u55AE");
		Modify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(flag == false) {
					flag = true;
					JInternalFrame inf = new JInternalFrame();
					inf=new JInternalFrame("�ק�/�����q��",true,true,true,true);
				    inf.setBounds(100, 100, 1049, 577);
				    inf.setVisible(true);
				    desktopPane.add(inf);
				    Container icp=inf.getContentPane();
				    icp.setLayout(null);
					
					JScrollPane scrollPane = new JScrollPane();
					scrollPane.setBounds(32, 33, 396, 479);
					icp.add(scrollPane);
					
					JTextArea textArea = new JTextArea();
					scrollPane.setViewportView(textArea);
					
					JLabel label = new JLabel("\u8A02\u4F4D\u4EE3\u865F");
					label.setFont(new Font("�s�ө���", Font.PLAIN, 22));
					label.setHorizontalAlignment(SwingConstants.CENTER);
					label.setBounds(442, 33, 210, 41);
					icp.add(label);
					
					JFormattedTextField ReservedID = new JFormattedTextField();
					ReservedID.setBounds(637, 35, 210, 41);
					icp.add(ReservedID);
					
					JButton Send = new JButton("\u78BA\u8A8D");
					
					Send.setBounds(871, 35, 129, 41);
					icp.add(Send);
					
					JLayeredPane Before = new JLayeredPane();
					Before.setBounds(442, 87, 279, 360);
					icp.add(Before);
					Before.setVisible(false);
					
					
					JLabel label_1 = new JLabel("\u539F\u672C\u8A02\u55AE");
					label_1.setHorizontalAlignment(SwingConstants.CENTER);
					label_1.setBounds(53, 13, 180, 39);
					Before.add(label_1);
					
					JLabel label_2 = new JLabel("\u65C5\u9928\u7DE8\u865F");
					label_2.setHorizontalAlignment(SwingConstants.CENTER);
					label_2.setBounds(0, 47, 128, 39);
					Before.add(label_2);
					
					JLabel HotelID = new JLabel("");
					HotelID.setHorizontalAlignment(SwingConstants.CENTER);
					HotelID.setBounds(151, 47, 128, 39);
					Before.add(HotelID);
					
					JLabel EnterDateOriginal = new JLabel("");
					EnterDateOriginal.setHorizontalAlignment(SwingConstants.CENTER);
					EnterDateOriginal.setBounds(151, 99, 128, 39);
					Before.add(EnterDateOriginal);
					
					JLabel label_4 = new JLabel("\u5165\u4F4F\u65E5\u671F");
					label_4.setHorizontalAlignment(SwingConstants.CENTER);
					label_4.setBounds(0, 99, 128, 39);
					Before.add(label_4);
					
					JLabel OutDateOriginal = new JLabel("");
					OutDateOriginal.setHorizontalAlignment(SwingConstants.CENTER);
					OutDateOriginal.setBounds(151, 151, 128, 39);
					Before.add(OutDateOriginal);
					
					JLabel label_5 = new JLabel("\u9000\u623F\u65E5\u671F");
					label_5.setHorizontalAlignment(SwingConstants.CENTER);
					label_5.setBounds(0, 151, 128, 39);
					Before.add(label_5);
					
					JLabel SingleOriginal = new JLabel("");
					SingleOriginal.setHorizontalAlignment(SwingConstants.CENTER);
					SingleOriginal.setBounds(151, 203, 128, 39);
					Before.add(SingleOriginal);
					
					JLabel label_6 = new JLabel("\u5165\u4F4F\u623F\u9593");
					label_6.setHorizontalAlignment(SwingConstants.CENTER);
					label_6.setBounds(0, 203, 128, 39);
					Before.add(label_6);
					
					JLabel DoubleOriginal = new JLabel("");
					DoubleOriginal.setHorizontalAlignment(SwingConstants.CENTER);
					DoubleOriginal.setBounds(151, 255, 128, 39);
					Before.add(DoubleOriginal);
					
					JLabel QuadOriginal = new JLabel("");
					QuadOriginal.setHorizontalAlignment(SwingConstants.CENTER);
					QuadOriginal.setBounds(151, 308, 128, 39);
					Before.add(QuadOriginal);
					
					JLayeredPane After = new JLayeredPane();
					After.setBounds(740, 87, 279, 441);
					icp.add(After);
					After.setVisible(false);
					
					JLabel label_3 = new JLabel("\u4FEE\u6539\u8A02\u55AE");
					label_3.setHorizontalAlignment(SwingConstants.CENTER);
					label_3.setBounds(53, 13, 180, 39);
					After.add(label_3);
					
					JLabel label_10 = new JLabel("\u5165\u4F4F\u65E5\u671F");
					label_10.setHorizontalAlignment(SwingConstants.CENTER);
					label_10.setBounds(0, 65, 128, 39);
					After.add(label_10);
					
					JLabel label_12 = new JLabel("\u9000\u623F\u65E5\u671F");
					label_12.setHorizontalAlignment(SwingConstants.CENTER);
					label_12.setBounds(0, 117, 128, 39);
					After.add(label_12);
					
					JLabel label_14 = new JLabel("\u5165\u4F4F\u623F\u9593");
					label_14.setHorizontalAlignment(SwingConstants.CENTER);
					label_14.setBounds(0, 163, 128, 39);
					After.add(label_14);
					
					JFormattedTextField EnterYear = new JFormattedTextField();
					EnterYear.setBounds(111, 65, 45, 40);
					After.add(EnterYear);
					EnterYear.setText("\u5E74");
					
					JLabel label_7 = new JLabel("/");
					label_7.setBounds(157, 65, 26, 43);
					After.add(label_7);
					
					JFormattedTextField EnterMonth = new JFormattedTextField();
					EnterMonth.setBounds(164, 65, 45, 40);
					After.add(EnterMonth);
					EnterMonth.setText("\u6708");
					
					JLabel label_8 = new JLabel("/");
					label_8.setBounds(211, 65, 26, 43);
					After.add(label_8);
					
					JFormattedTextField EnterDay = new JFormattedTextField();
					EnterDay.setBounds(220, 65, 45, 40);
					After.add(EnterDay);
					EnterDay.setText("\u65E5");
					
					JFormattedTextField OutYear = new JFormattedTextField();
					OutYear.setText("\u5E74");
					OutYear.setBounds(111, 117, 45, 40);
					After.add(OutYear);
					
					JLabel label_9 = new JLabel("/");
					label_9.setBounds(157, 117, 26, 43);
					After.add(label_9);
					
					JFormattedTextField OutMonth = new JFormattedTextField();
					OutMonth.setText("\u6708");
					OutMonth.setBounds(164, 117, 45, 40);
					After.add(OutMonth);
					
					JLabel label_11 = new JLabel("/");
					label_11.setBounds(211, 117, 26, 43);
					After.add(label_11);
					
					JFormattedTextField OutDay = new JFormattedTextField();
					OutDay.setText("\u65E5");
					OutDay.setBounds(220, 117, 45, 40);
					After.add(OutDay);
					
					JCheckBox Single = new JCheckBox("\u55AE\u4EBA");
					Single.setBounds(113, 169, 115, 27);
					After.add(Single);
					
					JSpinner SingleRoom = new JSpinner();
					SingleRoom.setBounds(111, 205, 120, 42);
					After.add(SingleRoom);
					
					JCheckBox Double = new JCheckBox("\u96D9\u4EBA");
					Double.setBounds(113, 260, 115, 27);
					After.add(Double);
					
					JSpinner DoubleRoom = new JSpinner();
					DoubleRoom.setBounds(111, 296, 120, 42);
					After.add(DoubleRoom);
					
					JCheckBox Quad = new JCheckBox("\u56DB\u4EBA");
					Quad.setBounds(113, 347, 115, 27);
					After.add(Quad);
					
					JSpinner QuadRoom = new JSpinner();
					QuadRoom.setBounds(111, 383, 120, 42);
					After.add(QuadRoom);
					
					JButton btnCancel = new JButton("\u5168\u90E8\u53D6\u6D88");
					btnCancel.setBounds(442, 460, 144, 51);
					icp.add(btnCancel);
					btnCancel.setVisible(false);
					
					JButton SendModify = new JButton("\u78BA\u8A8D\u9001\u51FA");
					
					SendModify.setBounds(589, 460, 144, 51);
					icp.add(SendModify);
					SendModify.setVisible(false);
					
					Send.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							Before.setVisible(true);
							After.setVisible(true);
							btnCancel.setVisible(true);
							SendModify.setVisible(true);
						}
					});
					
					SendModify.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							textArea.append("Check-in Day: " + EnterYear.getText() + "/" + EnterMonth.getText() + "/" + EnterDay.getText() + "\r\n");
							textArea.append("Check-out Day: " + OutYear.getText() + "/" + OutMonth.getText() + "/" + OutDay.getText() + "\r\n");
							textArea.append("Hotel ID: " + HotelID.getText()+"\r\n");
							if(Single.isSelected()) {
								textArea.append("Single room: " + SingleRoom.getValue()+"\r\n");
							}
							if(Double.isSelected()) {
								textArea.append("Double room: " + DoubleRoom.getValue()+"\r\n");
							}
							if(Quad.isSelected()) {
								textArea.append("Quad room: " + QuadRoom.getValue()+"\r\n");
							}
						}
					});
					inf.addInternalFrameListener(new WindowHandler()); 
				}
			}
		});
		ToolBar.add(Modify);
		
		JButton Query = new JButton("\u67E5\u8A62\u8A02\u55AE");
		Query.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(flag == false) {
					flag = true;
					JInternalFrame inf = new JInternalFrame();
					inf=new JInternalFrame("�d�߭q��",true,true,true,true);
				    inf.setBounds(100, 100, 563, 613);
				    inf.setVisible(true);
				    desktopPane.add(inf);
				    Container icp=inf.getContentPane();
				    icp.setLayout(null);
					
					JScrollPane scrollPane = new JScrollPane();
					scrollPane.setBounds(43, 109, 442, 379);
					icp.add(scrollPane);
					
					JTextArea textArea = new JTextArea();
					scrollPane.setViewportView(textArea);
					scrollPane.setVisible(false);
					
					JLabel label = new JLabel("\u8A02\u4F4D\u4EE3\u865F");
					label.setFont(new Font("�s�ө���", Font.PLAIN, 22));
					label.setHorizontalAlignment(SwingConstants.CENTER);
					label.setBounds(30, 32, 194, 85);
					icp.add(label);
					
					JFormattedTextField ReservedID = new JFormattedTextField();
					ReservedID.setBounds(199, 57, 255, 39);
					icp.add(ReservedID);
					
					JButton Send = new JButton("\u78BA\u8A8D");
					Send.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							scrollPane.setVisible(true);
							textArea.setText(ReservedID.getText());
						}
					});
					Send.setBounds(43, 501, 450, 52);
					icp.add(Send);
					inf.addInternalFrameListener(new WindowHandler()); 
				}
			}
		});
		ToolBar.add(Query);
		
		JButton LoginSend = new JButton("OK");
		LoginSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				account = Account.getText();
				password = Password.getText();
				Login.setVisible(false);
				ToolBar.setVisible(true);
			}
		});
		LoginSend.setBounds(168, 111, 151, 36);
		Login.add(LoginSend);
		
		JButton LoginCancel = new JButton("Cancel");
		LoginCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		LoginCancel.setBounds(333, 111, 151, 36);
		Login.add(LoginCancel);
		
		Password = new JPasswordField();
		Password.setBounds(333, 62, 151, 36);
		Login.add(Password);
		
		
	}

	class WindowHandler extends InternalFrameAdapter {
		public void internalFrameClosing(WindowEvent e) {
		    flag = false;  
		}
		public void internalFrameClosed(InternalFrameEvent e) {
			flag = false;
	    }
	}
}