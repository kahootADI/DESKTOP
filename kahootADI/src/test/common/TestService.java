package test.common;

import java.net.InetAddress;
import java.rmi.Remote;

public interface TestService extends Remote{

	  public String getResponse();
	}
