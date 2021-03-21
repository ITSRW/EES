package com.java.system.logiccode;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import org.hyperic.sigar.NetFlags;
import org.hyperic.sigar.NetInterfaceConfig;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import com.java.system.gui.Clientgui;
import com.java.system.gui.Clientlock;
import com.java.system.gui.GuardianSetting;

public class ThreadboxII implements Runnable {
	Alertprotocol alert;
	public static Thread receiveralert;
	String [] msgstr;
	String Headkey="";
	String IPkey="";

	byte inbuf[];
	int BUFSIZE = 1024;
	DatagramSocket receiveSocket;
	DatagramPacket receivePacket;

	public void run() {
		msgstr=new String [7];
		alert=new Alertprotocol();
		inbuf = new byte[BUFSIZE];

		try {
			receiveSocket = new DatagramSocket(GuardianSetting.receivePort);
		} catch (SocketException e1) {
			JOptionPane.showMessageDialog(null, "�򿪶˿�ʧ�ܣ�", "����", JOptionPane.ERROR_MESSAGE);
			GuardianSetting.frame.show();
			GuardianSetting.gui.dispose();
			return;
		}		
		receivePacket = new DatagramPacket(inbuf, BUFSIZE);

		receiveralert=new Thread(alert);  
		receiveralert.start();	//�̼߳������

		while (!GuardianSetting.thr.isInterrupted()) {//�������ܡ��������ź�
			try {
				receiveSocket.receive(receivePacket);
				msgstr=(new String(receivePacket.getData(), 0, receivePacket.getLength())).split("/");
				Alertprotocol.packege=msgstr;
				if(msgstr[0]!=null){//��ȡͷ����IP��
					Headkey=msgstr[0];
					IPkey=msgstr[3];
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "�򿪶˿�ʧ�ܣ�", "����", JOptionPane.ERROR_MESSAGE);
				return;
			}
			alert.dataAction(Headkey,IPkey);//��ȫЭ�鴦��ͷ����IP��
		}
	} 
} 