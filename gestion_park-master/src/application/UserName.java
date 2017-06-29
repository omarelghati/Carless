package application;

import org.apache.commons.collections4.map.StaticBucketMap;

import controllers.AccueilController;

public class UserName {
	private static String user;

	public static String getUser(String name){
		if(user==null){
			user = new String(name);
			return user;
		}else return user;

	}


}
