package com.interview.taxes;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.List;

import org.apache.log4j.Logger;

public enum SalesCalculator {
	/**
	 * Istanza Singleton del Factory
	 */
	INSTANCE;
	
	private Logger logger = Logger.getLogger(SalesCalculator.class);

	/**
	 * 
	 * @param item
	 * @return
	 */
	public BigDecimal calculateTotPrice(ItemI item) {
		logger.debug("calculateTotPrice");
		BigDecimal tot = new BigDecimal(BigInteger.ZERO, 2);
		tot = new BigDecimal(item.getQuantity()).multiply(item.getPrice().getValue());
		return tot;
	}

	/**
	 * 
	 * @param item
	 * @return
	 */
	public BigDecimal calculateTax(ItemI item) {
		logger.debug("calculateTax");
		BigDecimal taxes = new BigDecimal(BigInteger.ZERO, 2);
		BigDecimal totPrice = calculateTotPrice(item);
		for (PercentageItemTaxTypeEnum singleTax : item.getApplicableTax()) {
			BigDecimal toto = totPrice.multiply(singleTax.getSalesTax());
			taxes = taxes.add(toto);
		}
		// arrotonda per eccesso
		return new BigDecimal(Math.ceil(taxes.doubleValue() * 20) / 20).setScale(2, RoundingMode.HALF_UP);

	}

	/**
	 * 
	 * @param item
	 * @return Singolo importo tassato
	 */
	public BigDecimal calculateSingleImport(ItemI item) {
		logger.debug("calculateSingleImport");
		return calculateTotPrice(item).add(calculateTax(item));
	}

	/**
	 * 
	 * @param items
	 * @return importo tassato di tutti gli item
	 */
	public BigDecimal calculateTotalImport(List<ItemI> items) {
		logger.debug("calculateTotalImport");
		BigDecimal totalImport = new BigDecimal(BigInteger.ZERO, 2);
		for (ItemI singleItem : items) {
			totalImport = totalImport.add(calculateSingleImport(singleItem));
		}
		return totalImport;
	}

	/**
	 * 
	 * @param items
	 * @return Imposta totale
	 */
	public BigDecimal calculateTaxes(List<ItemI> items) {
		logger.info("calculateTaxes");
		BigDecimal totTaxes = new BigDecimal(BigInteger.ZERO, 2);
		for (ItemI singleItem : items) {
			totTaxes = totTaxes.add(calculateTax(singleItem));
		}
		return totTaxes;
	}
}
