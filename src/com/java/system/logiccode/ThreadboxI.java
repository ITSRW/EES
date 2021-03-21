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
import org.hyperic.sigar.NetInterfaceConfig;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import com.java.system.gui.Clientgui;
import com.java.system.gui.Clientlock;

public class ThreadboxI implements Runnable {
	public static int signal;
	public static Timer tme;
	int t=1000;
	public void run() {//“脉搏”信号发送线程
		senderbox sender=new senderbox();
		tme =new Timer(t,sender);
		tme.start();
	} 
} 

class senderbox implements ActionListener{
	
	static Sigar sigar = new Sigar(); //系统信息获取对象待命
	DatagramSocket sendSocket;
	DatagramPacket sendPacket;
	byte outbuf[];
	int BUFSIZE = 1024;

	String datastring="";

	String unactive="001";
	String active="010";
	String terminate="100";
	
	String date;
	String localip;
	String localhost;
	String MAC;

	public void actionPerformed(ActionEvent e) {//辅助时间监听事件
		sendpulse(Clientlock.IPAddress,Clientlock.port);
	}

	public void sendpulse(InetAddress ip,int port){//“脉搏”信号发送方法
		//获取四个本机信息
		date=getDateTime();
		localhost=getPlatformName();
		localip=getip();
		try {
			MAC=getlocalMac();
		} catch (SigarException e) {
			JOptionPane.showMessageDialog(null, "获取Mac异常！", "错误！", JOptionPane.ERROR_MESSAGE);
			return;
		}

		try{
			//增加表格记录
			datastring="";
			Clientgui.model.addRow(Clientgui.RowModel);
			if(ThreadboxI.signal==0){
				Clientgui.dialogtable.setValueAt("001", Clientgui.dialogtable.getRowCount()-1, 0);
				datastring+="001/";
			}
			else if(ThreadboxI.signal==1){
				Clientgui.dialogtable.setValueAt("010", Clientgui.dialogtable.getRowCount()-1, 0);
				datastring+="010/";
			}
			else if(ThreadboxI.signal==-1){
				Clientgui.dialogtable.setValueAt("100", Clientgui.dialogtable.getRowCount()-1, 0);
				datastring+="100/";
			}

			Clientgui.dialogtable.setValueAt(date, Clientgui.dialogtable.getRowCount()-1, 1);
			Clientgui.dialogtable.setValueAt(ip, Clientgui.dialogtable.getRowCount()-1, 2);
			Clientgui.dialogtable.setValueAt(localip, Clientgui.dialogtable.getRowCount()-1, 3);
			Clientgui.dialogtable.setValueAt(port, Clientgui.dialogtable.getRowCount()-1, 4);
			Clientgui.dialogtable.setValueAt(localhost, Clientgui.dialogtable.getRowCount()-1, 5);
			Clientgui.dialogtable.setValueAt(MAC, Clientgui.dialogtable.getRowCount()-1, 6);

			datastring+=date+ip+"/"+localip+"/"+port+"/"+localhost+"/"+MAC;
			outbuf = datastring.getBytes();//组装表格数据
			sendSocket=new DatagramSocket();
			// 组装要发送的的数据包
			sendPacket = new DatagramPacket(outbuf, outbuf.length, ip, port);
			sendSocket.send(sendPacket);// 发送数据

		} catch (SocketException e1) {
			JOptionPane.showMessageDialog(null, "无法打开指定端口！", "错误！", JOptionPane.ERROR_MESSAGE);
			return;
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "发送数据数据失败！", "错误！", JOptionPane.ERROR_MESSAGE);
			return;
		} catch (NumberFormatException e1) {
			JOptionPane.showMessageDialog(null, "设置端口数据异常！", "错误！", JOptionPane.ERROR_MESSAGE);
			return;
		}
	}

	public static String getlocalMac() throws SigarException{
		// 取到当前机器的MAC地址  
		String[] ifaces = sigar.getNetInterfaceList();  
		String hwaddr = null;  
		NetInterfaceConfig cfg = sigar.getNetInterfaceConfig();  
		return cfg.getHwaddr();
	}

	public static String getip(){
		String address = null;  
		try {  
			address = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {  
			System.out.println("取值异常！");  
		}  
		return address;
	}

	public static String getPlatformName() {  
		String hostname = "";  
		try {  
			hostname = InetAddress.getLocalHost().getHostName();  
		} catch (Exception exc) {  
			Sigar sigar = new Sigar();  
			try {  
				hostname = sigar.getNetInfo().getHostName();  
			} catch (SigarException e) {  
				hostname = "localhost.unknown";  
			} finally {  
				sigar.close();  
			}  
		}  
		return hostname;  
	}  

	public static String getDateTime() {
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = format.format(date);
		return time;
	}
}