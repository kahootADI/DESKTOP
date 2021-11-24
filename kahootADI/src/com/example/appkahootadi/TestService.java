package com.example.appkahootadi;

import java.net.InetAddress;
import java.rmi.Remote;

public interface TestService{
	  public String getNickname(String nickname);
	  public boolean kahootStarted();
	  public boolean checkNicknames(String actualNickname);
	  public int getSizeList();
	}

