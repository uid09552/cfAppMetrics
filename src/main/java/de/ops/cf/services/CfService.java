package de.ops.cf.services;

import org.cloudfoundry.client.lib.*;
import org.cloudfoundry.client.lib.domain.ApplicationStats;
import org.cloudfoundry.client.lib.domain.InstanceStats;
import org.cloudfoundry.client.lib.domain.InstanceStats.Usage;

import de.ops.cf.MongoUtil;
import de.ops.cf.models.Cfapp;

import org.apache.log4j.Logger;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.TimerTask;


public class CfService extends TimerTask {
	
   private static final Logger logger = Logger.getLogger(CfService.class);
	     
   private CloudCredentials cloudcredetials;
   private CloudFoundryClient client;
   private List<String> monitoringApps;
   
   
   public CfService(String user, String password, String target,List<String> monitoringApps) throws Exception
   {
	  	   
	   logger.debug("initialize CF Service");
	   
	   cloudcredetials=new CloudCredentials(user,password);
	   client=new CloudFoundryClient(cloudcredetials,getTargetURL(target));
	   this.monitoringApps=monitoringApps;
	   
	   try {
		   client.login();
	   } catch(Exception ex)
	   {
		   logger.error(ex.getMessage());
		   throw new Exception("Could not connect to client: target "+target);
	   }  
	   
   }
   
   private URL getTargetURL(String target) {
       try {
           return URI.create(target).toURL();
       } catch (MalformedURLException e) {
           throw new RuntimeException("The target URL is not valid: " + e.getMessage());
       }
   }
        //http://www.tutorialspoint.com/spring/spring_jdbc_example.htm (DAO Example)
   
   public void run()
   {
	   	         
	   java.util.ListIterator<String> itrApps=monitoringApps.listIterator();
	   MongoUtil mongoService;
	   try {
	   mongoService = new MongoUtil();
	  
	   }
	   catch(Exception ex)
	   {
		   logger.error(ex.getMessage());
		   return;
	   }
	   
	   while(itrApps.hasNext())
	   {
		   String appName=(String) itrApps.next();
		   ApplicationStats appStats= client.getApplicationStats(appName);
		   List <InstanceStats> appInstanceStats= appStats.getRecords();
		   
		   java.util.ListIterator<InstanceStats> itrInstanceStats=appInstanceStats.listIterator();
		   int instance=0;
		   while (itrInstanceStats.hasNext()) {
				Usage appInstanceUsage=itrInstanceStats.next().getUsage();
				Cfapp app=new Cfapp(appName, appInstanceUsage.getCpu(), appInstanceUsage.getTime(), instance,appInstanceUsage.getMem());
				mongoService.insert(app.getMongoObject(), "cfappdata");
				instance++;
			
		}
	   }
	   	   
	   
   }

}
