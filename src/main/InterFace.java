package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JLayeredPane;
import javax.swing.JToolBar;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JSpinner;
import javax.swing.JCheckBox;
import javax.swing.JInternalFrame;

public class InterFace extends JFrame {
	private String account;
	private String password;
	
	private JPanel contentPane;
	private JTextField AccountTxt;
	private JTextField PasswordTxt;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterFace frame = new InterFace();
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
	public InterFace() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1057, 523);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(391, 34, 289, 98);
		contentPane.add(toolBar);
		toolBar.setVisible(false);
		
		JButton Search = new JButton("\u67E5\u8A62\u65C5\u9928");
		
		toolBar.add(Search);
		
		JButton Change = new JButton("\u4FEE\u6539\u8A02\u55AE");
		toolBar.add(Change);
		
		JButton Finding = new JButton("\u67E5\u8A62\u8A02\u55AE");
		toolBar.add(Finding);
		
		JButton Reservation = new JButton("\u4F4F\u5BBF\u9810\u5B9A");
		
		toolBar.add(Reservation);
		
		JLayeredPane Login = new JLayeredPane();
		Login.setBounds(196, -1, 649, 151);
		contentPane.add(Login);
		
		JLabel AccountLabel = new JLabel("\u5E33\u865F");
		AccountLabel.setBounds(168, 13, 151, 36);
		Login.add(AccountLabel);
		AccountLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		AccountTxt = new JTextField();
		AccountTxt.setBounds(333, 13, 151, 36);
		Login.add(AccountTxt);
		AccountTxt.setColumns(10);
		
		PasswordTxt = new JTextField();
		PasswordTxt.setBounds(333, 62, 151, 36);
		Login.add(PasswordTxt);
		PasswordTxt.setColumns(10);
		
		JLabel PasswordLabel = new JLabel("\u5BC6\u78BC");
		PasswordLabel.setBounds(168, 62, 151, 36);
		Login.add(PasswordLabel);
		PasswordLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton btnOk = new JButton("OK");
		btnOk.setBounds(168, 111, 151, 36);
		Login.add(btnOk);
		
		JButton btnCnacel = new JButton("Cancel");
		btnCnacel.setBounds(333, 111, 151, 36);
		Login.add(btnCnacel);
		
		JLayeredPane Reserve = new JLayeredPane();
		Reserve.setBounds(14, 149, 1011, 314);
		contentPane.add(Reserve);
		Reserve.setLayout(null);
		Reserve.setVisible(false);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(14, 13, 366, 288);
		Reserve.add(scrollPane_1);
		
		JTextArea textArea_1 = new JTextArea();
		scrollPane_1.setViewportView(textArea_1);
		
		JLabel Hotelabel = new JLabel("\u65C5\u9928\u7DE8\u865F");
		Hotelabel.setFont(new Font("新細明體", Font.PLAIN, 22));
		Hotelabel.setHorizontalAlignment(SwingConstants.CENTER);
		Hotelabel.setBounds(395, 16, 149, 43);
		Reserve.add(Hotelabel);
		
		JFormattedTextField HotelID = new JFormattedTextField();
		HotelID.setBounds(558, 13, 149, 43);
		Reserve.add(HotelID);
		
		JLabel label = new JLabel("\u5165\u4F4F\u65E5\u671F");
		label.setFont(new Font("新細明體", Font.PLAIN, 22));
		label.setBounds(418, 72, 118, 64);
		Reserve.add(label);
		
		JFormattedTextField EnterYearRes = new JFormattedTextField();
		EnterYearRes.setText("\u5E74");
		EnterYearRes.setBounds(534, 87, 45, 40);
		Reserve.add(EnterYearRes);
		
		JLabel label_5 = new JLabel("/");
		label_5.setBounds(593, 84, 26, 43);
		Reserve.add(label_5);
		
		JFormattedTextField EnterMonthRes = new JFormattedTextField();
		EnterMonthRes.setText("\u6708");
		EnterMonthRes.setBounds(609, 87, 45, 40);
		Reserve.add(EnterMonthRes);
		
		JLabel label_6 = new JLabel("/");
		label_6.setBounds(666, 84, 26, 43);
		Reserve.add(label_6);
		
		JFormattedTextField EnterDayRes = new JFormattedTextField();
		EnterDayRes.setText("\u65E5");
		EnterDayRes.setBounds(679, 87, 45, 40);
		Reserve.add(EnterDayRes);
		
		JLabel label_7 = new JLabel("\u9000\u623F\u65E5\u671F");
		label_7.setFont(new Font("新細明體", Font.PLAIN, 22));
		label_7.setBounds(418, 135, 108, 45);
		Reserve.add(label_7);
		
		JFormattedTextField OutYearRes = new JFormattedTextField();
		OutYearRes.setText("\u5E74");
		OutYearRes.setBounds(534, 140, 45, 40);
		Reserve.add(OutYearRes);
		
		JLabel label_8 = new JLabel("/");
		label_8.setBounds(593, 137, 26, 43);
		Reserve.add(label_8);
		
		JFormattedTextField OutMonthRes = new JFormattedTextField();
		OutMonthRes.setText("\u6708");
		OutMonthRes.setBounds(609, 140, 45, 40);
		Reserve.add(OutMonthRes);
		
		JLabel label_9 = new JLabel("/");
		label_9.setBounds(666, 137, 26, 43);
		Reserve.add(label_9);
		
		JFormattedTextField OutDayRes = new JFormattedTextField();
		OutDayRes.setText("\u65E5");
		OutDayRes.setBounds(679, 140, 45, 40);
		Reserve.add(OutDayRes);
		
		JCheckBox checkBox = new JCheckBox("\u55AE\u4EBA");
		checkBox.setBounds(879, 13, 115, 27);
		Reserve.add(checkBox);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(877, 49, 120, 42);
		Reserve.add(spinner);
		
		JCheckBox checkBox_1 = new JCheckBox("\u96D9\u4EBA");
		checkBox_1.setBounds(879, 104, 115, 27);
		Reserve.add(checkBox_1);
		
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setBounds(877, 140, 120, 42);
		Reserve.add(spinner_1);
		
		JCheckBox checkBox_2 = new JCheckBox("\u56DB\u4EBA");
		checkBox_2.setBounds(879, 191, 115, 27);
		Reserve.add(checkBox_2);
		
		JSpinner spinner_2 = new JSpinner();
		spinner_2.setBounds(877, 227, 120, 42);
		Reserve.add(spinner_2);
		
		JLabel label_10 = new JLabel("\u5165\u4F4F\u623F\u9593");
		label_10.setHorizontalAlignment(SwingConstants.CENTER);
		label_10.setFont(new Font("新細明體", Font.PLAIN, 22));
		label_10.setBounds(721, 16, 149, 43);
		Reserve.add(label_10);
		
		JLayeredPane SearchLayer = new JLayeredPane();
		SearchLayer.setBounds(14, 130, 1011, 300);
		contentPane.add(SearchLayer);
		SearchLayer.setLayout(null);
		SearchLayer.setVisible(false);
		
		JLabel EnterLabel = new JLabel("\u5165\u4F4F\u65E5\u671F");
		EnterLabel.setFont(new Font("新細明體", Font.PLAIN, 22));
		EnterLabel.setBounds(328, 13, 118, 64);
		SearchLayer.add(EnterLabel);
		
		JFormattedTextField EnterYear = new JFormattedTextField();
		EnterYear.setText("\u5E74");
		EnterYear.setBounds(444, 28, 45, 40);
		SearchLayer.add(EnterYear);
		
		JLabel label_1 = new JLabel("/");
		label_1.setBounds(503, 25, 26, 43);
		SearchLayer.add(label_1);
		
		JFormattedTextField EnterMonth = new JFormattedTextField();
		EnterMonth.setText("\u6708");
		EnterMonth.setBounds(519, 28, 45, 40);
		SearchLayer.add(EnterMonth);
		
		JLabel label_2 = new JLabel("/");
		label_2.setBounds(576, 25, 26, 43);
		SearchLayer.add(label_2);
		
		JFormattedTextField EnterDay = new JFormattedTextField();
		EnterDay.setText("\u65E5");
		EnterDay.setBounds(589, 28, 45, 40);
		SearchLayer.add(EnterDay);
		
		JFormattedTextField OutDay = new JFormattedTextField();
		OutDay.setText("\u65E5");
		OutDay.setBounds(589, 81, 45, 40);
		SearchLayer.add(OutDay);
		
		JLabel label_3 = new JLabel("/");
		label_3.setBounds(576, 78, 26, 43);
		SearchLayer.add(label_3);
		
		JFormattedTextField OutMonth = new JFormattedTextField();
		OutMonth.setText("\u6708");
		OutMonth.setBounds(519, 81, 45, 40);
		SearchLayer.add(OutMonth);
		
		JLabel label_4 = new JLabel("/");
		label_4.setBounds(503, 78, 26, 43);
		SearchLayer.add(label_4);
		
		JFormattedTextField OutYear = new JFormattedTextField();
		OutYear.setText("\u5E74");
		OutYear.setBounds(444, 81, 45, 40);
		SearchLayer.add(OutYear);
		
		JLabel OutLabel = new JLabel("\u9000\u623F\u65E5\u671F");
		OutLabel.setFont(new Font("新細明體", Font.PLAIN, 22));
		OutLabel.setBounds(328, 76, 108, 45);
		SearchLayer.add(OutLabel);
		
		JLabel PeopleLabel = new JLabel("\u5165\u4F4F\u4EBA\u6578");
		PeopleLabel.setFont(new Font("新細明體", Font.PLAIN, 22));
		PeopleLabel.setBounds(328, 128, 108, 45);
		SearchLayer.add(PeopleLabel);
		
		JSpinner People = new JSpinner();
		People.setBounds(444, 128, 190, 42);
		SearchLayer.add(People);
		
		JLabel StarLabel = new JLabel("\u661F\u7D1A");
		StarLabel.setFont(new Font("新細明體", Font.PLAIN, 22));
		StarLabel.setBounds(328, 186, 57, 45);
		SearchLayer.add(StarLabel);
		
		JSpinner Star = new JSpinner();
		Star.setBounds(444, 183, 190, 42);
		SearchLayer.add(Star);
		
		JLabel RoomLabel = new JLabel("\u5BA2\u623F\u6578\u91CF");
		RoomLabel.setFont(new Font("新細明體", Font.PLAIN, 22));
		RoomLabel.setBounds(650, 13, 108, 45);
		SearchLayer.add(RoomLabel);
		
		JCheckBox Single = new JCheckBox("\u55AE\u4EBA");
		Single.setBounds(768, 13, 115, 27);
		SearchLayer.add(Single);
		
		JSpinner SingleNimber = new JSpinner();
		SingleNimber.setBounds(766, 49, 120, 42);
		SearchLayer.add(SingleNimber);
		
		JCheckBox Double = new JCheckBox("\u96D9\u4EBA");
		Double.setBounds(768, 104, 115, 27);
		SearchLayer.add(Double);
		
		JSpinner DoubleNumber = new JSpinner();
		DoubleNumber.setBounds(766, 140, 120, 42);
		SearchLayer.add(DoubleNumber);
		
		JCheckBox Quad = new JCheckBox("\u56DB\u4EBA");
		Quad.setBounds(768, 191, 115, 27);
		SearchLayer.add(Quad);
		
		JSpinner QuadNumber = new JSpinner();
		QuadNumber.setBounds(766, 227, 120, 42);
		SearchLayer.add(QuadNumber);
		
		JCheckBox Agree = new JCheckBox("\u540C\u610F\u4F7F\u7528\u8005\u689D\u6B3E");
		Agree.setBounds(328, 240, 190, 45);
		SearchLayer.add(Agree);
		
		JButton Send = new JButton("\u78BA\u8A8D");
		
		Send.setBounds(538, 238, 190, 49);
		SearchLayer.add(Send);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 13, 300, 274);
		SearchLayer.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JCheckBox AgreeRes = new JCheckBox("\u540C\u610F\u4F7F\u7528\u8005\u689D\u6B3E");
		AgreeRes.setBounds(428, 222, 190, 45);
		Reserve.add(AgreeRes);
		
		JButton SendRes = new JButton("\u78BA\u8A8D");
		SendRes.setBounds(638, 220, 190, 49);
		Reserve.add(SendRes);
		
		
		
		Send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (Agree.isSelected()) {
					
					textArea.append("Account: " + account + "\r\n");
					textArea.append("Password: " + password + "\r\n");
					textArea.append("Check-in Day: " + EnterYear.getText() + "/" + EnterMonth.getText() + "/" + EnterDay.getText() + "\r\n");
					textArea.append("Check-out Day: " + OutYear.getText() + "/" + OutMonth.getText() + "/" + OutDay.getText() + "\r\n");
					textArea.append("Nums of persons: " + People.getValue()+"\r\n");
					textArea.append("Required star: " + Star.getValue()+"\r\n");
					if(Single.isSelected()) {
						textArea.append("Single room: " + SingleNimber.getValue()+"\r\n");
					}
					if(Double.isSelected()) {
						textArea.append("Double room: " + DoubleNumber.getValue()+"\r\n");
					}
					if(Quad.isSelected()) {
						textArea.append("Quad room: " + QuadNumber.getValue()+"\r\n");
					}
					
					HotelSearcher Searcher = new HotelSearcher();
					Hotel[] Hotels = Searcher.search(Integer.valueOf(Star.getValue().toString()), Integer.valueOf(SingleNimber.getValue().toString()), Integer.valueOf(DoubleNumber.getValue().toString()), Integer.valueOf(QuadNumber.getValue().toString()));
					textArea.append(Star.getValue().toString());
				}
				else {
					textArea.setText("You don't agree."+"\r\n");
				}
			}
		});
		btnCnacel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				account = AccountTxt.getText();
				password = PasswordTxt.getText();
				Login.setVisible(false);
				toolBar.setVisible(true);
			}
			
		});
		Search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SearchLayer.setVisible(true);
			}
		});
		Reservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SearchLayer.setVisible(false);
				Reserve.setVisible(true);
			}
		});
	}
}
