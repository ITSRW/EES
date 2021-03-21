package com.java.system.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;

import com.java.system.logiccode.ThreadboxI;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Time;
import javax.swing.JPasswordField;
import java.awt.Color;

public class Clientunlock extends JFrame {
	Thread die;
	Thread thr;
	Timer tme;
	Timer dietme;
	int counter=0;
	
	private JPanel contentPane;

	public static JButton quit;
	public static JButton submit;
	private JPasswordField code;
	
	public Clientunlock() {
		setTitle("\u5B89\u5168\u89E3\u9664\u76D1\u6D4B");
		setIconImage(Toolkit.getDefaultToolkit().getImage("SOURCES/icon.jpg"));
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 323, 157);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel unlock = new JLabel("\u89E3\u9664\u76D1\u63A7");
		unlock.setForeground(Color.WHITE);
		unlock.setFont(new Font("黑体", Font.BOLD, 18));
		unlock.setBounds(118, 10, 83, 28);
		contentPane.add(unlock);
		JLabel input = new JLabel("\u8F93\u5165\u6307\u4EE4");
		input.setForeground(Color.WHITE);
		input.setFont(new Font("黑体", Font.BOLD, 18));
		input.setBounds(10, 44, 83, 28);
		contentPane.add(input);

		String path = "SOURCES/BGIII.jpg";  
		ImageIcon background = new ImageIcon(path);  
		JLabel backgroud = new JLabel(background);  
		backgroud.setBounds(0, 0, this.getWidth(), this.getHeight());  
		JPanel imagePanel = (JPanel) this.getContentPane();  
		imagePanel.setOpaque(false);  
		this.getLayeredPane().add(backgroud, new Integer(Integer.MIN_VALUE));  
		setVisible(true);  

		quit = new JButton("\u53D6\u6D88");
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tme==null){
					Clientgui.terminate.setEnabled(true);
				}
				dispose();
			}
		});
		submit = new JButton("\u786E\u8BA4");
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(code.getText().equals(Clientlock.lockcode)){
					ThreadboxI.signal=-1;
					submit.setEnabled(false);
					quit.setEnabled(false);
					dietimer tmer=new dietimer();
					die=new Thread (tmer);
					die.start();
					JOptionPane.showMessageDialog(null, "请稍候！", "正在解除...", JOptionPane.NO_OPTION);
				}
				else{
					counter++;
					if((3-counter)==0){
						submit.setEnabled(false);
						Clientgui.terminate.setEnabled(false);
						timer tmer=new timer();
						thr=new Thread (tmer);
						thr.start();
					}
					JOptionPane.showMessageDialog(null, "密码错误！无法解除！您还可以输入\r\n"+(3-counter)+"次！", "错误！", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		});
		submit.setBounds(52, 82, 93, 23);
		contentPane.add(submit);
		quit.setBounds(170, 82, 93, 23);
		contentPane.add(quit);
		
		code = new JPasswordField();
		code.setBounds(99, 44, 164, 27);
		contentPane.add(code);
	}
	class timer  implements Runnable {
		int t=1000;
		public void run() {
			counttime counter=new counttime();
			tme =new Timer(t,counter);
			tme.start();
		} 
	} 
	class counttime implements ActionListener{
		int timer=0;
		public void actionPerformed(ActionEvent e) {
			timer++;
			if(timer>600){
				Clientgui.terminate.setEnabled(true);
				tme.stop();
				thr.interrupt();
				tme=null;
				thr=null;
			}
		}
	}
	class dietimer  implements Runnable {
		int t=1000;
		public void run() {
			countdietime counter=new countdietime();
			dietme =new Timer(t,counter);
			dietme.start();
		} 
	} 
	class countdietime implements ActionListener{
		int timer=0;
		public void actionPerformed(ActionEvent e) {
			timer++;
			if(timer>5){
				Clientgui.remainder.setBackground(SystemColor.gray);
				ThreadboxI.tme.stop();
				Clientlock.thr.interrupt();
				dietme.stop();
				die.interrupt();
				dietme=null;
				die=null;
				JOptionPane.showMessageDialog(null, "已解除监控！", "完成！", JOptionPane.INFORMATION_MESSAGE);
				quit.setEnabled(true);
				Clientgui.terminate.setEnabled(false);
			}
		}
	}
}