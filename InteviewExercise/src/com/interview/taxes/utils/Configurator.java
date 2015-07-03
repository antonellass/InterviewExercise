package com.interview.taxes.utils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;


	public class Configurator {
		private static Logger logger = Logger.getLogger(Configurator.class);
		private static final String PROPERTY_PATH="/settings.properties";
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
				return new Configurator();
		}
		
		 public  String getPatternItem(){
			 return props.getProperty("pattern_Item");
		 }
		 
		 public BigDecimal getTaxAll(){
			 return new BigDecimal(props.getProperty("tax_all"));
		 }

		 public BigDecimal getTaxExempt(){
			 return new BigDecimal(props.getProperty("tax_exempt"));
		 }
		 public BigDecimal getTaxImported(){
			 return new BigDecimal(props.getProperty("tax_imported"));
		 }
		 public List<String> getExemptItemList(){
			 
			 String strList=  props.getProperty("exempt_items");
			 List<String> myList= Arrays.asList(strList.split(","));
			 return myList;
		 }

}
