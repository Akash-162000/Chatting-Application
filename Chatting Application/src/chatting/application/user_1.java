package chatting.application;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
import java.io.*;

public class user_1 extends JFrame implements ActionListener{
	
	JPanel p1 = new JPanel();
	JTextField t1= new JTextField();
	JButton b1 = new JButton("Send");
	static JTextArea ta1 = new JTextArea();
	
	static ServerSocket server_socket;
	static Socket socket;
	static DataInputStream d_in;
	static DataOutputStream d_out;
	
	Boolean typing = false;
	
	user_1(){
		
		
		
		p1.setLayout(null);
		p1.setBackground(Color.decode("#2a162e"));
		p1.setBounds(0,0,400,65);
		add(p1);
		
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("chatting/application/icons/3.png"));
		Image i2 = i1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		JLabel l1 = new JLabel(i3);
		l1.setBounds(5,21,25,25);
		p1.add(l1);
		
		l1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});

		 
		
		ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("chatting/application/icons/1.png"));
		Image i5 = i4.getImage().getScaledInstance(45, 45, Image.SCALE_DEFAULT);
		ImageIcon i6 = new ImageIcon(i5);
		JLabel l2 = new JLabel(i6);
		l2.setBounds(40,10,45,45);
		p1.add(l2);
		
		

		ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("chatting/application/icons/video.png"));
		Image i8 = i7.getImage().getScaledInstance(-1, 25, Image.SCALE_DEFAULT);
		ImageIcon i9 = new ImageIcon(i8);
		JLabel l5 = new JLabel(i9);
		l5.setBounds(270,21,25,25);
		p1.add(l5);
		
		ImageIcon i10 = new ImageIcon(ClassLoader.getSystemResource("chatting/application/icons/phone.png"));
		Image i11 = i10.getImage().getScaledInstance(-1, 25, Image.SCALE_DEFAULT);
		ImageIcon i12 = new ImageIcon(i11);
		JLabel l6 = new JLabel(i12);
		l6.setBounds(315,21,25,25);
		p1.add(l6);
		
		
		ImageIcon i13 = new ImageIcon(ClassLoader.getSystemResource("chatting/application/icons/3icon.png"));
		Image i14 = i13.getImage().getScaledInstance(-1, 20, Image.SCALE_DEFAULT);
		ImageIcon i15 = new ImageIcon(i14);
		JLabel l7 = new JLabel(i15);
		l7.setBounds(360,21,15,25);
		p1.add(l7);
		
		
		JLabel l3 = new JLabel("USER-1");
		l3.setFont(new Font("SAN_SERIF", Font.BOLD, 14));
		l3.setForeground(Color.WHITE);
		l3.setBounds(100, 10, 100 , 25);
		p1.add(l3);
		
		JLabel l4 = new JLabel("Active Now");
		l4.setFont(new Font("SAN_SERIF", Font.PLAIN, 12));
		l4.setForeground(Color.WHITE);
		l4.setBounds(100, 35, 100 , 18);
		p1.add(l4);
		
		Timer timer = new Timer(1,new ActionListener() {
			
			public void actionPerformed(ActionEvent ae)
			{
				if(!typing)
				{
					l4.setText("Active Now");
					
				}
			}
			
		});
		
		timer.setInitialDelay(1500);
		
		
		
		t1.setBounds(10,570,300,35);
		t1.setFont(new Font("SAN_SERIF", Font.PLAIN,16));
		add(t1);
		
		t1.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent ke){
				
				l4.setText("typing...");
				
				timer.stop();
				
				typing = true;
			}
			
			public void keyReleased(KeyEvent ke){
				typing = false;
				
				if(!timer.isRunning())
				{
					timer.restart();
				}
			}
			
		});
		
		b1.setBounds(315,570,75,35);
		b1.setBackground(Color.decode("#2a162e"));
		b1.setForeground(Color.WHITE);
		b1.setFont(new Font("SAN_SERIF", Font.BOLD,14));
		b1.addActionListener(this);
		add(b1);
		
		ta1.setBounds(5,70,390,495);
		ta1.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
		ta1.setEditable(false);
		ta1.setLineWrap(true);
		ta1.setWrapStyleWord(true);
		ta1.setBackground(new Color(200,200,200));
		add(ta1);
		
		
		setLayout(null);
		
		setSize(400,620);
		setLocation(300,100);
		setUndecorated(true);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ae) {
		
		
		
		try {
		String temp =  t1.getText();
		
		
		ta1.setText(ta1.getText()+"\n"+"\t\t"+temp);
		
		d_out.writeUTF(temp);
		t1.setText("");
		
		}catch(Exception err) {
			t1.setText("");
		}
	}
	
	public static void main(String args[])
	{
		new user_1();
		
		String input = "";
		
		try {
		server_socket = new ServerSocket(6000);
		
		while(true)
		{
		socket = server_socket.accept();
		d_in = new DataInputStream(socket.getInputStream());
		d_out = new DataOutputStream(socket.getOutputStream());
		
		
		
		while(true)
		{
		input = d_in.readUTF();
		ta1.setText(ta1.getText()+"\n "+input);
		}
		}
		}
		catch(Exception e) {
			
		}
	}

}
