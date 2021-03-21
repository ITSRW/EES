package com.java.system.gui;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ExecutePage extends JFrame {
	private JPanel contentPane;
	public static ExecutePage frame ;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new ExecutePage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ExecutePage() {
		setTitle("ESS 2.0 \u542F\u52A8\u5668");
		setIconImage(Toolkit.getDefaultToolkit().getImage("SOURCES/icon.jpg"));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 250, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel titleI = new JLabel("\u6B22\u8FCE\u4F7F\u7528ESS\u7F51\u7EDC\u8BBE\u5907");
		titleI.setForeground(Color.BLUE);
		titleI.setFont(new Font("黑体", Font.BOLD, 18));
		titleI.setBounds(33, 10, 191, 37);
		contentPane.add(titleI);
		JLabel titleII = new JLabel("\u667A\u80FD\u5B89\u9632\u7CFB\u7EDF");
		titleII.setForeground(Color.BLUE);
		titleII.setFont(new Font("黑体", Font.BOLD, 18));
		titleII.setBounds(60, 57, 128, 37);
		contentPane.add(titleII);
		String path = "SOURCES/bg.jpg";  
		ImageIcon background = new ImageIcon(path);  
		JLabel backgroud = new JLabel(background);  
		backgroud.setBounds(0, 0, this.getWidth(), this.getHeight());  
		JPanel imagePanel = (JPanel) this.getContentPane();  
		imagePanel.setOpaque(false);  
		this.getLayeredPane().add(backgroud, new Integer(Integer.MIN_VALUE));  
		setVisible(true);  
		
		JButton exit = new JButton("\u9000\u51FA");
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		JButton actguardian = new JButton("\u542F\u52A8\u76D1\u89C6\u7AEF");
		actguardian.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GuardianSetting newgurdian=new GuardianSetting();
				newgurdian.show();
				dispose();
			}
		});

		JButton actclient = new JButton("\u542F\u52A8\u8BBE\u5907\u7AEF");
		actclient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Clientlock newclient =new Clientlock();
				newclient.show();
				dispose();
			}
		});
		
		actclient.setFont(new Font("黑体", Font.BOLD, 13));
		actclient.setBounds(65, 130, 105, 30);
		contentPane.add(actclient);
		actguardian.setFont(new Font("黑体", Font.BOLD, 13));
		actguardian.setBounds(65, 180, 105, 30);
		contentPane.add(actguardian);
		exit.setFont(new Font("黑体", Font.BOLD, 13));
		exit.setBounds(65, 230, 105, 30);
		contentPane.add(exit);
	}
}
