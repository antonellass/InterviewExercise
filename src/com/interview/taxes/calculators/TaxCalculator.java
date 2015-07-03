package com.interview.taxes.calculators;

import java.math.BigDecimal;

import com.interview.taxes.ItemI;

abstract class TaxCalculator {
	 // The next element in the chain of responsibility
	@SuppressWarnings("unused")
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
	public TaxCalculator setNext(TaxCalculator cal){
		nextCalculator=cal;
		return cal;
		
	}
	public BigDecimal calculateTotPrice(ItemI item) {
		BigDecimal tot = new BigDecimal(item.getQuantity()).multiply(item.getPrice().getValue());
		return tot;
	}


	abstract protected void calculates();
}
