package com.java.system.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JScrollPane;

import com.java.system.logiccode.Alertprotocol;
import com.java.system.logiccode.ThreadboxII;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Guardiangui extends JFrame {
	private JPanel contentPane;
	public static JTextArea alertarea;

	public Guardiangui() {
		setTitle("ESS\u76D1\u63A7\u7AEF");
		setIconImage(Toolkit.getDefaultToolkit().getImage("SOURCES/icon.jpg"));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel title = new JLabel("ESS\u667A\u80FD\u8BBE\u5907\u76D1\u63A7\u7AEF");
		title.setForeground(Color.WHITE);
		title.setFont(new Font("ºÚÌå", Font.BOLD, 20));
		title.setBounds(53, 10, 193, 35);
		contentPane.add(title);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(35, 56, 214, 336);
		contentPane.add(scrollPane);
		alertarea = new JTextArea();
		alertarea.setFont(new Font("ºÚÌå", Font.PLAIN, 18));
		alertarea.setLineWrap(true);
		alertarea.setEditable(false);
		scrollPane.setViewportView(alertarea);
		String path = "SOURCES/bg.jpg";  
		ImageIcon background = new ImageIcon(path);  
		JLabel backgroud = new JLabel(background);  
		backgroud.setBounds(0, 0, this.getWidth(), this.getHeight());  
		JPanel imagePanel = (JPanel) this.getContentPane();  
		imagePanel.setOpaque(false);  
		this.getLayeredPane().add(backgroud, new Integer(Integer.MIN_VALUE));  
		setVisible(true);  
		JButton terminate = new JButton("\u7EC8\u6B62\u76D1\u63A7");
		terminate.setFont(new Font("ºÚÌå", Font.BOLD, 18));
		terminate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GuardianSetting.thr.interrupt();
				ThreadboxII.receiveralert.interrupt();
				Alertprotocol.rugulator.stop();
				System.exit(0);
			}
		});
		terminate.setBounds(72, 417, 144, 35);
		contentPane.add(terminate);
	}
}