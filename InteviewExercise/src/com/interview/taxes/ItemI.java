package com.interview.taxes;

import java.math.BigDecimal;
import java.util.Set;

public interface ItemI {

	String getName();

	Integer getQuantity();

	LocalCurrency getPrice();

	BigDecimal getSingleImport();

	BigDecimal getTaxImport();

	BigDecimal getTotPrice();

	Set<PercentageItemTaxTypeEnum> getApplicableTax();

	void setSingleImport(BigDecimal singleImport);

	void setTaxImport(BigDecimal taxImport);

	void setTotalTaxes(BigDecimal totalTaxes);

	void setTotPrice(BigDecimal totPrice);

	BigDecimal getTotalTaxes();

}
