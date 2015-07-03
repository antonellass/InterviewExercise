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
	EXEMPT(Configurator.getInstance().getTaxExempt()),
	/**
	 * Imported Item
	 */
	IMPORTED(Configurator.getInstance().getTaxImported());
	
	private BigDecimal tax;
	
	private PercentageItemTaxTypeEnum(BigDecimal tax){
		this.tax=tax;
	}
	
	public BigDecimal getSalesTax(){
		return tax;
	}
	

}
