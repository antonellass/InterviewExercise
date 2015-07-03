package com.interview.taxes.calculators;

import com.interview.taxes.ItemI;

public class ChainOfResponsabilityCalculator {

	TaxCalculator calculator;

	public ChainOfResponsabilityCalculator(ItemI item) {
		this.calculator = new AllTaxCalculator(item);
		ExemptTaxCalculator exemptCalculator = new ExemptTaxCalculator(item);
		ImportedTaxCalculator importedCalculator = new ImportedTaxCalculator(item);
		// set responsability
		calculator.setNext(importedCalculator);
		importedCalculator.setNext(exemptCalculator);
	}

	/**
	 * Chain start
	 * 
	 * @param item
	 */
	public void calculateInvoice(ItemI item) {
		calculator.calculates();

	}
}
