package com.interview.taxes.utils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;


	public class Configurator {
		private static Logger logger = Logger.getLogger(Configurator.class);
		private static final String PROPERTY_PATH="/settings.properties";
		private static Configurator configurator=null;
		private static Properties props = new Properties();
		
		private Configurator () {
			InputStream is = null;
			BufferedInputStream bif = null;
	        try {
	        	is = this.getClass().getResourceAsStream(PROPERTY_PATH);
	        	bif = new BufferedInputStream(is);
	        	props.load(bif);
	        } catch (Exception e) {
	            logger.error("Unable to load configuration: "+e.getMessage(),e);
	        }finally{
	        	try {
	        		if(bif != null)				
						bif.close();				
		        	if(is != null)
		        		is.close();
	        	} catch (IOException e) {
					e.printStackTrace();
				}
	        }
	    }
		
		
		public static Configurator getInstance(){
			
				return configurator=new Configurator();
		}
		
		 public String getPatternItem(){
			 return props.getProperty("pattern_Item");
		 }
		 
		 public String getTaxAll(){
			 return props.getProperty("tax_all");
		 }

		 public String getTaxExempt(){
			 return props.getProperty("tax_exempt");
		 }
		 public String getTaxImported(){
			 return props.getProperty("tax_imported");
		 }

}
