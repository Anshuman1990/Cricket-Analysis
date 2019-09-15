package com.database;

import java.net.Inet4Address;
import java.net.UnknownHostException;

public class config
{
   public static String ip = "127.0.0.1";
   public static String DB_name = "cricket_analysis";
   static
   {
	   try
	{
		ip = Inet4Address.getLocalHost().getHostAddress();
	}
	catch (UnknownHostException e)
	{
		e.printStackTrace();
	}
   }
}
