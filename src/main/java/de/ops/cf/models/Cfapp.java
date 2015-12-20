package de.ops.cf.models;

import org.springframework.data.annotation.Id;

import com.mongodb.BasicDBObject;

import java.util.Date;

public class Cfapp {
		//will be used for Mongo _id
		@Id 
		private String id;
		private String appName;
		private double cpu;
		private Date timestamp;
		private float ram;
		private int instance;
			
		public int getInstance() {
			return instance;
		}


		public void setInstance(int instance) {
			this.instance = instance;
		}


		public Cfapp(String appName, double cpu, Date timestamp,int instance,float ram) {
						
			this.appName = appName;
			this.cpu = cpu;
			this.timestamp = timestamp;
			this.instance=instance;
			this.ram=ram;
		}
		
		
		public String getCfOrgName() {
			return cfOrgName;
		}

		public void setCfOrgName(String cfOrgName) {
			this.cfOrgName = cfOrgName;
		}

		public String getCfSpaceName() {
			return cfSpaceName;
		}

		public void setCfSpaceName(String cfSpaceName) {
			this.cfSpaceName = cfSpaceName;
		}

		private String cfOrgName;
		private String cfSpaceName;
		
		
		public float getRam() {
			return ram;
		}

		public void setRam(float ram) {
			this.ram = ram;
		}
						
		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getAppName() {
			return appName;
		}

		public void setAppName(String appName) {
			this.appName = appName;
		}

		public double getCpu() {
			return cpu;
		}

		public void setCpu(double cpu) {
			this.cpu = cpu;
		}

		public Date getTimestamp() {
			return timestamp;
		}

		public void setTimestamp(Date timestamp) {
			this.timestamp = timestamp;
		}

		@Override
		public String toString()
		{
			return id+"::"+appName+"::"+String.valueOf(cpu)+String.valueOf(ram);
		}
		
		public BasicDBObject getMongoObject()
		{
			BasicDBObject obj = new BasicDBObject();
			
			obj.put("appName", getAppName());
			obj.put("instance", getInstance());
			obj.put("cpu", getCpu());
			obj.put("Ram", getRam());
			obj.put("timestamp", getTimestamp());
			
			
			return obj;
		}
}
