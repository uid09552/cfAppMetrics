package de.ops.cf.models;

import org.springframework.data.annotation.Id;
import java.util.Date;


public class Cfapp {
		//will be used for Mongo _id
		@Id 
		private String id;
		private String appName;
		private float cpu;
		private Date timestamp;
		private float ram;
		
		
		public float getRam() {
			return ram;
		}

		public void setRam(float ram) {
			this.ram = ram;
		}

		public Cfapp(){
			
		}
		
		public Cfapp(String id, String appName, float cpu, Date timestamp) {
			super();
			this.id = id;
			this.appName = appName;
			this.cpu = cpu;
			this.timestamp = timestamp;
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

		public float getCpu() {
			return cpu;
		}

		public void setCpu(float cpu) {
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
		
}
