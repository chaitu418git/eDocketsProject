package eDockets.cucumber.owner;
import org.aeonbits.owner.ConfigFactory;

import eDockets.cucumber.utils.FrameworkConfig;



public class OwnerClass {
	public static FrameworkConfig config;
	
    public static FrameworkConfig configLoader()
   {
	 config = ConfigFactory.create(FrameworkConfig.class);
	 return config;
   }

/*
 * public static String getUrl() { return config.baseUrl(); }
 */
}
