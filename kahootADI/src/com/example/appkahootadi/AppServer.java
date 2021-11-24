package com.example.appkahootadi;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.Iterator;

import javax.swing.ListModel;

import com.example.appkahootadi.TestService;

import kahootADI.waitingRoom;

//TestServer.java
import java.io.IOException;
import java.net.Socket;

import lipermi.exception.LipeRMIException;
import lipermi.handler.CallHandler;
import lipermi.net.IServerListener;
import lipermi.net.Server;

public class AppServer implements TestService{
  private String ip;
  private waitingRoom wr;
  public static void main(String[] args) {
	AppServer as = new AppServer();
}
  public AppServer() {
      try {
    	  Enumeration<NetworkInterface> n = NetworkInterface.getNetworkInterfaces();
          for (; n.hasMoreElements();)
          {
                  NetworkInterface e = n.nextElement();
                  Enumeration<InetAddress> a = e.getInetAddresses();
                  for (; a.hasMoreElements();)
                  {
                          InetAddress addr = a.nextElement();
                          if(addr.toString().contains("192")) {
                        	  ip = addr.getHostAddress().toString();
                          }
                  }
          }
          System.out.println(ip);
          CallHandler callHandler = new CallHandler();
          callHandler.registerGlobal(TestService.class, this);
          Server server = new Server();
          server.bind(7777, callHandler);
          wr = new waitingRoom();
          wr.setVisible(true);
//          wr.getLblIPLabel().setText("IP:"+ip);
          wr.getLblIPLabel().setText("IP: " + ip);
          server.addServerListener(new IServerListener() {  
              @Override
              public void clientDisconnected(Socket socket) {
                  System.out.println("Client Disconnected: ");
              }
              
              @Override
              public void clientConnected(Socket socket) {
                  //addPlayerToList(wr,sendNickname(ip));

              }
          });
      } catch (LipeRMIException | IOException e) {
          e.printStackTrace();
      }
  }


@Override
  public String getNickname(String nickname) {
	  addPlayerToList(wr,nickname);
	  return nickname;
  }

	public void addPlayerToList(waitingRoom wr,String player) {
		wr.getListModel().addElement(player);
		
	}
	@Override
	public boolean kahootStarted() {
		return wr.isPlaying();
	}
	@Override
	public boolean checkNicknames(String actualNickname) {
		boolean exists = false;
		ListModel model = wr.getList().getModel();
		for (int i = 0; i < model.getSize(); i++) {
			System.out.println(actualNickname+" "+wr.getListModel().get(i));
			if(actualNickname.equals(wr.getListModel().get(i))){
				exists = true;
			}
		}
		return exists;
	}
}
