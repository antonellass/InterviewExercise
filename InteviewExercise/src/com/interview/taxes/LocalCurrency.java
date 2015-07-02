package com.interview.taxes;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

public final class LocalCurrency {

	private static final Currency CURRENCY = Currency.getInstance(Locale.getDefault());
	private BigDecimal value = new BigDecimal(0);
	private static final NumberFormat FORMATTER = NumberFormat.getCurrencyInstance();

	/***
	 * Constructor creates a LocalCurrency with input value
	 * 
	 * @param valueP
	 */
	private LocalCurrency(BigDecimal valueP) {
		this.value = valueP;
	}

	/**
	 * Factory method to create a localCurerency
	 * 
	 * @param valueP
	 * @return a instance of LocalCurency
	 */
	public static LocalCurrency getInstance(BigDecimal valueP) {
		return new LocalCurrency(valueP);
	}

	/**
	 * Getter
	 * 
	 * @return value of LocalCurrency
	 */
	public BigDecimal getValue() {
		return value;
	}

	/**
	 * Setter
	 * 
	 * @param value
	 */
	public void setValue(BigDecimal value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return FORMATTER.format(this.value);
	}

	/**
	 * 
	 * @return String without symbolCurrency
	 */
	public String toStringWithouSymbolCurrency() {
		return FORMATTER.format(this.value).replace(CURRENCY.getSymbol(), "");
	}

	/**
	 * 
	 * @param valueTosum
	 * @return sum of input value
	 */
	public LocalCurrency add(BigDecimal valueTosum) {
		this.value = this.value.add(valueTosum);
		return this;
	}
}
