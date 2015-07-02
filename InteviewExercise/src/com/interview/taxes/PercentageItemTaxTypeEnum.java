package com.interview.taxes;

import java.math.BigDecimal;

/**
 * Value of tax by item type
 * @author antonella
 *
 */
public enum PercentageItemTaxTypeEnum {
	/**
	 * All type of item
	 */
	ALL(new BigDecimal("0.10")),
	/**
	 * Books,food and medical item
	 */
	EXEMPT(new BigDecimal("0.0")),
	/**
	 * Imported Item
	 */
	IMPORTED(new BigDecimal("0.05"));
	
	private BigDecimal tax;
	
	private PercentageItemTaxTypeEnum(BigDecimal tax){
		this.tax=tax;
	}
	
	public BigDecimal getSalesTax(){
		return tax;
	}
	

}
