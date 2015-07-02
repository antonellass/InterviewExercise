package com.interview.taxes;

import java.util.Set;

public interface ItemI {

	String getName();

	Integer getQuantity();

	LocalCurrency getPrice();

	String getType();

	Set<PercentageItemTaxTypeEnum> getApplicableTax();

}
