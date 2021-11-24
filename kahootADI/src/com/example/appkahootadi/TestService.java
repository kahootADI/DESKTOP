package com.example.appkahootadi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TestService{
	  public String getNickname(String nickname);
	  public boolean kahootStarted();
	  public boolean checkNicknames(String actualNickname);
	}
