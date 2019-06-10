package test_GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class test implements ActionListener {

	int counter = 0;
	JFrame f;
	JDesktopPane pane;
	boolean flag = false;
	private JButton btnFlag;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new test();
	}
	
	
	/**
	 * Create the frame.
	 */
	public test() {
		JFrame.setDefaultLookAndFeelDecorated(true);
	    f = new JFrame("test");
	    f.setSize(400, 300);
	    f.setLocationRelativeTo(null);
	    Container container = f.getContentPane();
	    container.setLayout(new BorderLayout());  
	    f.setVisible(true);
	    
	    JButton newInf = new JButton("new");
	    newInf.addActionListener(this);
	    container.add(newInf, BorderLayout.NORTH);
	    pane = new JDesktopPane();
	    container.add(pane);
	    JTextPane textPane = new JTextPane();
	    textPane.setBounds(189, 188, 154, 25);
	    pane.add(textPane);
	    btnFlag = new JButton("flag");
	    btnFlag.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		textPane.setText(String.valueOf(flag));
	    	}
	    });
	    btnFlag.setBounds(27, 186, 99, 27);
	    pane.add(btnFlag);
	    
	    
	    
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "new" && flag == false) {
			JInternalFrame inf = new JInternalFrame();
			inf=new JInternalFrame("內部框架 " + (++counter),true,true,true,true);
		    inf.setSize(200,100);
		    inf.setVisible(true);
		    pane.add(inf);
		    Container icp=inf.getContentPane();
		    final JTextArea ta=new JTextArea("Hello World! #" + counter);
		    JButton btn=new JButton("清除內容");
		    btn.addActionListener(new ActionListener(){
		    public void actionPerformed(ActionEvent e) {ta.setText("");}
		    });
		    icp.add(ta,"Center"); 
		    icp.add(btn,"South");
		    flag = true;
		    inf.addInternalFrameListener(new WindowHandler()); 
		    //inf.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		}
		else {
			System.out.println(e.getActionCommand());
			System.out.println(String.valueOf(flag));
		}
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
