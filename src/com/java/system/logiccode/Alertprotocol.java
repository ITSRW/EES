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

	public void run(){//��ȫЭ���ִ���̶߳�Ӧ����
		//�����̣߳�����������ʱָ��
		SecurityPoint caculating=new SecurityPoint();
		rugulator=new Timer(1000,caculating);
		rugulator.start();
	}

	public void dataAction(String headkey,String ipkey){//����ͷ���������
		if(headkey.equals("100")){
			//�жϽ������ݲ���ȫ�˳�
			GuardianSetting.thr.interrupt();
			System.exit(0);
		}
		if(headkey.equals("001")){
			//��ʼ���ܳ����ź�ͷ�����ݡ�
			Guardiangui.alertarea.setText(packege[0]+"|"+packege[1]+"|"+packege[2]+"|"+packege[3]+"|"+packege[4]+"|"+packege[5]+"|"+packege[6]);
			if(ipkey.equals("127.0.0.1")){
				alert();
			}
		}
	}

	public static void alert() {//���淽��
		if(SecurityPoint==0){
			jumptooff jump=new jumptooff();
			Thread off =new Thread(jump);
			off.start();
		}
		SecurityPoint=1;
		Guardiangui.alertarea.setText("���棡��⵽�������̻�������ְ�ȫЭ�飡");
		media startbelling=new media();

		//�������
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

	public static String getip(){//��ȡ��ǰ����IP
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

	public void actionPerformed(ActionEvent e) {//ʱ�䶯����������⾯��Э��
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

	public static Date StrToDate(String str) {//�������ݱ����ڻ�ȡ����
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

class jumptooff implements Runnable{//��⵽��ȫЭ�����ֶԻ������߳�
	public void run() {
		JOptionPane.showMessageDialog(null, "��⵽��ȫЭ�����֣��뾡�����豸��", "���棡", JOptionPane.ERROR_MESSAGE);
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
			//�޶���
		} catch (JavaLayerException e) {
			//�޶���
		}
		Alertprotocol.belling.interrupt();
		Alertprotocol.belling=null;
	}
}