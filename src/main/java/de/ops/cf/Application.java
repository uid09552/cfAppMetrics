package de.ops.cf;

import org.cloudfoundry.client.lib.*;
import org.apache.log4j.Logger;

public class Application {

	private static final Logger logger = Logger.getLogger(Application.class);
	
	public static void main(String[] args) {
		String User=System.getenv("CFUser");
		String Password=System.getenv("CFPassword");
		logger.info(String.format("Read System Variables: User: %s, password: xxx", "s"));
		
	}

}
