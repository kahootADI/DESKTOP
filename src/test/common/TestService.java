package test.common;

import java.rmi.Remote;

public interface TestService extends Remote{

	  public String getResponse(String data);
	}
