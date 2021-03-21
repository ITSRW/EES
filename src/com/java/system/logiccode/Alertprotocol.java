package com.java.system.logiccode;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import javazoom.jl.decoder.JavaLayerException;

import com.java.system.gui.Clientgui;
import com.java.system.gui.GuardianSetting;
import com.java.system.gui.Guardiangui;

public class Alertprotocol implements Runnable{
	public static Timer rugulator;
	public static Thread belling;
	public static Thread monitor ;
	public static String packege[]=new String[7];
	public static int SecurityPoint=0;

	public void run(){//安全协议可执行线程对应方法
		//监视线程，持续监测监察计时指针
		SecurityPoint caculating=new SecurityPoint();
		rugulator=new Timer(1000,caculating);
		rugulator.start();
	}

	public void dataAction(String headkey,String ipkey){//数据头码分流操作
		if(headkey.equals("100")){
			//中断接收数据并安全退出
			GuardianSetting.thr.interrupt();
			System.exit(0);
		}
		if(headkey.equals("001")){
			//开始接受持续信号头码数据。
			Guardiangui.alertarea.setText(packege[0]+"|"+packege[1]+"|"+packege[2]+"|"+packege[3]+"|"+packege[4]+"|"+packege[5]+"|"+packege[6]);
			if(ipkey.equals("127.0.0.1")){
				alert();
			}
		}
	}

	public static void alert() {//警告方法
		if(SecurityPoint==0){
			jumptooff jump=new jumptooff();
			Thread off =new Thread(jump);
			off.start();
		}
		SecurityPoint=1;
		Guardiangui.alertarea.setText("警告！检测到不明进程或操作入侵安全协议！");
		media startbelling=new media();

		//开启监控
		if(monitor==null){
			MSBOX ms=new MSBOX();
			monitor =new Thread(ms);
			monitor.start();
		}

		if(belling==null){
			belling=new Thread(startbelling);
			belling.start();
		}

	}

	public static String getip(){//获取当前本机IP
		String address = null;  
		try {  
			address = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {  
		}  
		return address;
	}
}

class SecurityPoint implements ActionListener{
	long outtime=0;
	Date now;
	Date lastdatatime;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public void actionPerformed(ActionEvent e) {//时间动作函数·监测警告协议
		if(Alertprotocol.packege[1]==null){
			return;
		}
		else{
			now=new Date();
			lastdatatime=StrToDate(Alertprotocol.packege[1]);
			outtime=lastdatatime.getTime()-now.getTime();
			if(outtime<=-5000){
				Alertprotocol.alert();
			}
		}
	}

	public static Date StrToDate(String str) {//最晚数据报日期获取方法
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = format.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
}

class jumptooff implements Runnable{//检测到安全协议入侵对话框处理线程
	public void run() {
		JOptionPane.showMessageDialog(null, "检测到安全协议入侵！请尽快检查设备！", "警告！", JOptionPane.ERROR_MESSAGE);
		System.exit(0);
	}
}

class media implements Runnable{
	public void run() {
		File file=new File("SOURCES/WARNING.mp3");
		MediaAlert m=new MediaAlert(file);
		try {
			m.play();
		} catch (FileNotFoundException e) {
			//无动作
		} catch (JavaLayerException e) {
			//无动作
		}
		Alertprotocol.belling.interrupt();
		Alertprotocol.belling=null;
	}
}