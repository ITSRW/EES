package com.java.system.logiccode;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import javax.swing.JOptionPane;
import com.java.system.gui.GuardianSetting;

public class MSBOX implements Runnable{
	byte inbuf[];
	int BUFSIZE = 1024;
	DatagramSocket receiveSocket;
	DatagramPacket receivePacket;
	public void run() {
		execu_exe monitor =new execu_exe();
		monitor.openExe();//ģ������ͷ�ɼ��ź�
		/*
		 * �����������������ͷ���ݽ��մ���ӿ�
		 */
	}
}

class execu_exe {
	void openExe() {  
		final Runtime runtime = Runtime.getRuntime();  
		Process process = null;  
		try {  
			process = runtime.exec("SOURCES/ECap.exe");  
		} catch (final Exception e) {  
			System.out.println("Error exec!");  
		}  
	}  
}