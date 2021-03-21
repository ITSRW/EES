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
		monitor.openExe();//模拟摄像头采集信号
		/*
		 * 物联网部门设计摄像头数据接收代码接口
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