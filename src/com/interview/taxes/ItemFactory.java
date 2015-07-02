package com.interview.taxes;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.LogMF;
import org.apache.log4j.Logger;

public enum ItemFactory {
	/**
	 * Istanza Singleton del Factory
	 */
	INSTANCE;
	/**
	 * Logger
	 */
	private Logger logger = Logger.getLogger(ItemFactory.class);
	/**
	 * Parola chiave per gli item importati
	 */
	private static final String IMPORTED_KEYWORD = "imported ";
	/**
	 * Lista degli item esenti da tasse e inzializzata prima dell'instanziazione della classe
	 */
	private static List<String> EXEMPT_ITEMS_TOKENS = new ArrayList<String>();
	static {
		EXEMPT_ITEMS_TOKENS.add("book");//books
		EXEMPT_ITEMS_TOKENS.add("chocolate");//food
		EXEMPT_ITEMS_TOKENS.add("pills");//medical
	}


	/**
	 * Crea l'item a partire dalla riga di testo in input
	 * @param itemLine stringa rappresentate l'ordine dell'item
	 * @return istanza di Item
	 */
	public ItemI createItem(String itemLine) {
		logger.debug("Create Item from itemLine: "+ itemLine);
		String patternQuantity="(\\d+) (.+) at ([\\d\\.,]+)";
		Pattern pattern = Pattern.compile(patternQuantity);
		Matcher matcher = pattern.matcher(itemLine);
		Integer quantity=null;
		String name=null;
		BigDecimal price=null;
		while(matcher.find()){
		 quantity = new Integer(matcher.group(1));
		 name=matcher.group(2);
		 price= new BigDecimal(matcher.group(3));
		}
		
		Set<PercentageItemTaxTypeEnum> taxValue = new HashSet<PercentageItemTaxTypeEnum>();
		taxValue.add(PercentageItemTaxTypeEnum.ALL);
		//controllo su item esenti
		for (String exemptString : EXEMPT_ITEMS_TOKENS) {
			if (name.toLowerCase().contains(exemptString)) {
				taxValue.remove(PercentageItemTaxTypeEnum.ALL);
				taxValue.add(PercentageItemTaxTypeEnum.EXEMPT);
				break;
			}else{
				taxValue.add(PercentageItemTaxTypeEnum.ALL);
			}
		}
		//controllo su item importati
		if (name.toLowerCase().contains(IMPORTED_KEYWORD)) {
			//put the keyword imported at begin 
			name=name.replace(IMPORTED_KEYWORD, "");
			name= IMPORTED_KEYWORD.concat(name);
			taxValue.add(PercentageItemTaxTypeEnum.IMPORTED);

		}
		//creazione istanza di Item
		LogMF.debug(logger," Create Item with name: {0} - quantity: {1} - price: {2}",name,quantity,LocalCurrency.getInstance(price).toString());
		return new ItemImpl(name, LocalCurrency.getInstance(price), quantity, taxValue);
	}



}
