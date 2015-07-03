package com.interview.taxes.calculators;

import java.math.BigDecimal;

import com.interview.taxes.ItemI;

abstract class TaxCalculator {
	 // The next element in the chain of responsibility
	protected TaxCalculator nextCalculator;
	private ItemI item;

	public TaxCalculator(ItemI item) {
		this.setItem(item);
	}

	

	public ItemI getItem() {
		return item;
	}

	public void setItem(ItemI item) {
		this.item = item;
	}
	/**
	 * setter to next calculator
	 * @param cal
	 * @return
	 */
	public TaxCalculator setNext(TaxCalculator cal){
		nextCalculator=cal;
		return cal;
		
	}
	/**
	 * Method to calculate the price * quantity
	 * @param item
	 * @return
	 */
	public BigDecimal calculateTotPrice(ItemI item) {
		BigDecimal tot = new BigDecimal(item.getQuantity()).multiply(item.getPrice().getValue());
		return tot;
	}

/**
 * Abstract method to implement any buss logic for tax type
 */
	abstract protected void calculates();
}
