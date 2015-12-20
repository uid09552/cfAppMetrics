package de.ops.cf;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;

import com.mongodb.DB;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;


import org.apache.log4j.Logger;


public class MongoUtil {
	
	private MongoClient mongo;
	private static final Logger logger = Logger.getLogger(MongoUtil.class);
	private DB mongoDB;
	
	public MongoUtil() throws UnknownHostException{
		String uri=System.getenv("MongoUri");
	
		
		MongoClientURI mongoUri=new MongoClientURI(uri);
		mongo=new MongoClient(mongoUri);
		mongoDB=mongo.getDB(mongoUri.getDatabase());
		logger.debug("Mongo Client created:"+mongo.getAddress());
		
	}
	
	public boolean insert(BasicDBObject data,String collectionName)
	{
		try
		{
			mongoDB.getCollection(collectionName).insert(data);
		}
		catch(Exception ex)
		{
			logger.error(ex.getMessage());
			return false;
		}
		
		return true;
	}
	
	 
	
}
