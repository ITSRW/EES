package com.java.system.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;

import com.java.system.logiccode.ThreadboxI;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.awt.Color;
import javax.swing.JPasswordField;

public class Clientlock extends JFrame {
	public JPanel contentPane;
	public JTextField setiptextField;
	public JTextField commuporttextField;

	public static InetAddress IPAddress;
	public static int port;
	public static String lockcode="";

	public static Thread thr;
	public static ThreadboxI sender;
	private JPasswordField codetextField;
	private JPasswordField confirmtextField;

	public Clientlock() {
		setTitle("ESS\u8BBE\u5907\u7AEF\u521D\u59CB\u5316");
		setIconImage(Toolkit.getDefaultToolkit().getImage("SOURCES/icon.jpg"));
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 308);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel inputcode = new JLabel("\u521D\u59CB\u5316\u8F93\u5165");
		inputcode.setForeground(Color.WHITE);
		inputcode.setFont(new Font("黑体", Font.PLAIN, 20));
		inputcode.setBounds(10, 61, 100, 30);
		contentPane.add(inputcode);
		JLabel Setting = new JLabel("\u8BF7\u8BBE\u7F6E\u672C\u6B21\u76D1\u63A7\u6307\u4EE4");
		Setting.setForeground(Color.WHITE);
		Setting.setFont(new Font("黑体", Font.PLAIN, 25));
		Setting.setBounds(118, 10, 237, 30);
		contentPane.add(Setting);
		JLabel setip = new JLabel("\u914D\u7F6E\u76D1\u89C6\u7AEFIP");
		setip.setForeground(Color.WHITE);
		setip.setFont(new Font("黑体", Font.PLAIN, 18));
		setip.setBounds(10, 141, 123, 30);
		contentPane.add(setip);
		JLabel comfirminput = new JLabel("\u786E\u8BA4\u8F93\u5165");
		comfirminput.setForeground(Color.WHITE);
		comfirminput.setFont(new Font("黑体", Font.PLAIN, 20));
		comfirminput.setBounds(10, 101, 83, 30);
		contentPane.add(comfirminput);
		JLabel commuport = new JLabel("\u901A\u4FE1\u7AEF\u53E3");
		commuport.setForeground(Color.WHITE);
		commuport.setFont(new Font("黑体", Font.PLAIN, 18));
		commuport.setBounds(20, 181, 83, 30);
		contentPane.add(commuport);
		setiptextField = new JTextField();
		setiptextField.setText("192.168.1.101");
		setiptextField.setBounds(131, 143, 237, 30);
		contentPane.add(setiptextField);
		setiptextField.setColumns(10);
		commuporttextField = new JTextField();
		commuporttextField.setColumns(10);
		commuporttextField.setBounds(131, 183, 237, 30);
		contentPane.add(commuporttextField);

		String path = "SOURCES/BGI.jpg";  
		ImageIcon background = new ImageIcon(path);  
		JLabel backgroud = new JLabel(background);  
		backgroud.setBounds(0, 0, this.getWidth(), this.getHeight());  
		JPanel imagePanel = (JPanel) this.getContentPane();  
		imagePanel.setOpaque(false);  
		this.getLayeredPane().add(backgroud, new Integer(Integer.MIN_VALUE));  
		setVisible(true);  
		
		JButton submit = new JButton("\u5F00\u59CB\u76D1\u6D4B");
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(codetextField.getText().equals("")||confirmtextField.getText().equals("")||setiptextField.getText().equals("")||commuporttextField.getText().equals("")){
					JOptionPane.showMessageDialog(null, "请输入正确的参数！", "错误！", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if(codetextField.getText().equals(confirmtextField.getText())){
					lockcode=codetextField.getText();//两次输入一致，密码确定
				}
				else{
					JOptionPane.showMessageDialog(null, "两次密码输入不一致！", "错误！", JOptionPane.ERROR_MESSAGE);
					return;
				}
				try {
					port=Integer.parseInt(commuporttextField.getText());//端口号参数
					IPAddress=InetAddress.getByName(setiptextField.getText());//IP地址参数
				} catch (UnknownHostException e1) {
					JOptionPane.showMessageDialog(null, "无法连接到指定地址!", "错误！", JOptionPane.ERROR_MESSAGE);
					return;
				}
				Clientgui client=new Clientgui();
				client.show();
				sender=new ThreadboxI();
				thr=new Thread(sender);
				thr.start();
				Clientgui.remainder.setBackground(SystemColor.blue);
				dispose();
			}
		});
		JButton quit = new JButton("\u9000\u51FA");
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExecutePage.frame.show();
				dispose();
			}
		});
		submit.setBounds(131, 223, 93, 36);
		contentPane.add(submit);
		quit.setBounds(275, 223, 93, 36);
		contentPane.add(quit);
		
		codetextField = new JPasswordField();
		codetextField.setBounds(131, 61, 237, 30);
		contentPane.add(codetextField);
		
		confirmtextField = new JPasswordField();
		confirmtextField.setBounds(131, 101, 237, 30);
		contentPane.add(confirmtextField);
	}
}