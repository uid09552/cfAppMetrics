package de.ops.cf;

import de.ops.cf.services.CfService;

import java.util.Arrays;
import java.util.List;
import java.util.Timer;

import org.apache.log4j.Logger;





public class Application {

	private static final Logger logger = Logger.getLogger(Application.class);
	
	public static void main(String[] args) {
		
		String User=System.getenv("CFUser");
		String Password=System.getenv("CFPassword");
		String target=System.getenv("CFTarget");
		String monitoringApps=System.getenv("monitoringApps");
		
		if (User!=null && Password!=null && target!=null && monitoringApps!=null)
		{
			logger.info(String.format("Read System Variables: User: %s, password: xxx; target %s", User,target));
		}
		else
		{
			logger.error("No Env Variables found: CFUser, CFPassword, CFTarget needed");
			return;
		}
		
		try {
			
			List <String> appItems=Arrays.asList(monitoringApps.split(","));
			
			CfService cfservice=new CfService(User, Password, target,appItems);
						
			Timer timer = new Timer();
			
			timer.scheduleAtFixedRate(cfservice, 0, 5000);
			
		
		}catch(Exception ex)
		{
			logger.error(ex.getMessage());
			return;
		}

	}
	
	

}
