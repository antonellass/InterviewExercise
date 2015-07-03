package com.interview.taxes;

import java.math.BigDecimal;

import com.interview.taxes.utils.Configurator;

/**
 * Value of tax by item type
 * @author antonella
 *
 */
public enum PercentageItemTaxTypeEnum {
	/**
	 * All type of item
	 */
	ALL(Configurator.getInstance().getTaxAll()),
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
