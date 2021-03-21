package com.java.system.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.TextArea;

import javax.swing.JTextArea;

import java.awt.SystemColor;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Clientgui extends JFrame {
	public static JButton terminate;
	public static JPanel contentPane;
	public static JTable dialogtable;
	public static JTextArea remainder;
	public static DefaultTableModel model;
	public Object[]Head={"通讯码","时间","目标IP","本机IP","端口","本机名称","物理地址"};
	public static Object[][]table=new Object[0][7];
	public static Object[]RowModel=new Object[7];

	public Clientgui() {
		setTitle("ESS\u8BBE\u5907\u7AEF");
		setIconImage(Toolkit.getDefaultToolkit().getImage("SOURCES/icon.jpg"));
		setResizable(false);
		model=new DefaultTableModel(table,Head);//创建table模版对象
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel states = new JLabel("\u5F53\u524D\u72B6\u6001");
		states.setForeground(Color.WHITE);
		states.setFont(new Font("黑体", Font.BOLD, 18));
		states.setBounds(48, 81, 87, 26);
		contentPane.add(states);
		JLabel title = new JLabel("ESS\u8BBE\u5907\u5B89\u5168\u8054\u7F51\u76D1\u63A7\u7CFB\u7EDF");
		title.setForeground(Color.WHITE);
		title.setFont(new Font("黑体", Font.BOLD, 24));
		title.setBounds(343, 10, 305, 36);
		contentPane.add(title);
		JLabel dilog = new JLabel("\"\u8109\u640F\"\u65E5\u5FD7");
		dilog.setForeground(Color.WHITE);
		dilog.setFont(new Font("黑体", Font.BOLD, 18));
		dilog.setBounds(410, 138, 106, 26);
		contentPane.add(dilog);
		JScrollPane dialogscrollPane = new JScrollPane();
		dialogscrollPane.setBounds(10, 174, 864, 225);
		contentPane.add(dialogscrollPane);
		dialogtable=new JTable(model);
		dialogscrollPane.setViewportView(dialogtable);
		remainder = new JTextArea();
		remainder.setForeground(Color.RED);
		remainder.setBackground(Color.LIGHT_GRAY);
		remainder.setBounds(192, 81, 595, 26);
		contentPane.add(remainder);

		String path = "SOURCES/BGII.jpg";  
		ImageIcon background = new ImageIcon(path);  
		JLabel backgroud = new JLabel(background);  
		backgroud.setBounds(0, 0, this.getWidth(), this.getHeight());  
		JPanel imagePanel = (JPanel) this.getContentPane();  
		imagePanel.setOpaque(false);  
		this.getLayeredPane().add(backgroud, new Integer(Integer.MIN_VALUE));  
		setVisible(true);  

		terminate = new JButton("\u7EC8\u6B62\u76D1\u63A7");
		terminate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Clientunlock unlock=new Clientunlock();
				unlock.show();
				terminate.setEnabled(false);
			}
		});
		terminate.setBounds(423, 409, 93, 23);
		contentPane.add(terminate);
	}
}