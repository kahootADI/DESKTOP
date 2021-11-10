
//TestService.java
package test.common;

//TestServer.java
import java.io.IOException;
import java.net.Socket;

import test.common.TestService;

import lipermi.exception.LipeRMIException;
import lipermi.handler.CallHandler;
import lipermi.net.IServerListener;

//TestServer.java
import java.io.IOException;
import java.net.Socket;

import test.common.TestService;

import lipermi.exception.LipeRMIException;
import lipermi.handler.CallHandler;
import lipermi.net.IServerListener;
import lipermi.net.Server;

public class ServerApp implements TestService {
	public static void main(String[] args) {
		ServerApp sa = new ServerApp();
	}

  public ServerApp() {
      try {
          CallHandler callHandler = new CallHandler();
          callHandler.registerGlobal(TestService.class, this);
          Server server = new Server();
          server.bind(7777, callHandler);
          server.addServerListener(new IServerListener() {
              
              @Override
              public void clientDisconnected(Socket socket) {
                  System.out.println("Client Disconnected: " + socket.getInetAddress());
              }
              
              @Override
              public void clientConnected(Socket socket) {
                  System.out.println("Client Connected: " + socket.getInetAddress());
              }
          });
          System.out.println("Server Listening");
      } catch (LipeRMIException | IOException e) {
          e.printStackTrace();
      }
  }
  
  @Override
  public String getResponse(String data) {
      System.out.println("getResponse called");
      return "Your data: " + data;
  }

}
