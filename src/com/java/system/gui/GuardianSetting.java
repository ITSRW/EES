package com.java.system.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;

import com.java.system.logiccode.ThreadboxII;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.awt.Color;
import java.awt.Toolkit;

public class GuardianSetting extends JFrame {
	public static ThreadboxII receiver;
	public static Thread thr;
	public static int receivePort;
	public static int videoPort;
	
	public static JPanel contentPane;
	public static JTextField iptext;
	public static JTextField porttext;
	public static JTextField videoport;
	
	public static GuardianSetting frame;
	public static Guardiangui gui;
	
	public GuardianSetting() {
		setTitle("ESS\u76D1\u63A7\u7AEF\u521D\u59CB\u5316");
		setIconImage(Toolkit.getDefaultToolkit().getImage("SOURCES/icon.jpg"));
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 300, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel title = new JLabel("ESS\u667A\u80FD\u8BBE\u5907\u76D1\u63A7\u7AEF");
		title.setForeground(Color.LIGHT_GRAY);
		title.setFont(new Font("黑体", Font.BOLD, 20));
		title.setBounds(54, 10, 186, 35);
		contentPane.add(title);
		JLabel set = new JLabel("\u53C2\u6570\u8BBE\u5B9A");
		set.setForeground(Color.RED);
		set.setFont(new Font("黑体", Font.BOLD, 16));
		set.setBounds(106, 80, 75, 19);
		contentPane.add(set);
		JLabel ip = new JLabel("\"\u8109\u640F\"\u4FE1\u53F7IP");
		ip.setForeground(Color.BLUE);
		ip.setFont(new Font("黑体", Font.BOLD, 16));
		ip.setBounds(10, 140, 108, 19);
		contentPane.add(ip);
		JLabel port = new JLabel("\u901A\u4FE1\u7AEF\u53E3\u8BBE\u5B9A");
		port.setForeground(Color.BLUE);
		port.setFont(new Font("黑体", Font.BOLD, 16));
		port.setBounds(10, 192, 108, 19);
		contentPane.add(port);
		
		JLabel label = new JLabel("\u89C6\u9891\u76D1\u63A7\u6570\u636E");
		label.setForeground(Color.BLUE);
		label.setFont(new Font("黑体", Font.BOLD, 16));
		label.setBounds(10, 240, 108, 19);
		contentPane.add(label);
		iptext = new JTextField();
		iptext.setEditable(false);
		iptext.setBounds(141, 137, 128, 28);
		contentPane.add(iptext);
		iptext.setColumns(10);
		iptext.setText(getip());
		porttext = new JTextField();
		porttext.setText("1");
		porttext.setBounds(141, 189, 128, 28);
		contentPane.add(porttext);
		porttext.setColumns(10);
		
        String path = "SOURCES/bg.jpg";  
        ImageIcon background = new ImageIcon(path);  
        JLabel backgroud = new JLabel(background);  
        backgroud.setBounds(0, 0, this.getWidth(), this.getHeight());  
        JPanel imagePanel = (JPanel) this.getContentPane();  
        imagePanel.setOpaque(false);  
        this.getLayeredPane().add(backgroud, new Integer(Integer.MIN_VALUE));  
        setVisible(true);  
		
		JButton active = new JButton("\u5F00\u59CB\u76D1\u63A7");
		active.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(porttext.getText().equals("")||videoport.getText().equals("")){
					JOptionPane.showMessageDialog(null, "请输入正确的端口号!", "错误！", JOptionPane.ERROR_MESSAGE);
					return;
				}
				receivePort=Integer.parseInt(porttext.getText());
				videoPort=Integer.parseInt(videoport.getText());
				gui=new Guardiangui();
				gui.show();
				receiver=new ThreadboxII();
				thr=new Thread(receiver);
				thr.start();
				dispose();
			}
		});
		
		JButton exit = new JButton("\u9000\u51FA");
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExecutePage.frame.show();
				dispose();
			}
		});
		
		videoport = new JTextField();
		videoport.setText("2");
		videoport.setColumns(10);
		videoport.setBounds(141, 246, 128, 28);
		contentPane.add(videoport);
		active.setFont(new Font("黑体", Font.BOLD, 16));
		active.setBounds(86, 382, 115, 35);
		contentPane.add(active);
		exit.setFont(new Font("黑体", Font.BOLD, 16));
		exit.setBounds(86, 427, 115, 35);
		contentPane.add(exit);
		
		JLabel label_1 = new JLabel("\u7AEF\u53E3\u8BBE\u5B9A");
		label_1.setForeground(Color.BLUE);
		label_1.setFont(new Font("黑体", Font.BOLD, 16));
		label_1.setBounds(30, 262, 75, 19);
		contentPane.add(label_1);
	}
	
	public static String getip(){
		String address = null;  
		try {  
			address = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {  
		}  
		return address;
	}
}