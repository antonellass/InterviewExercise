package com.interview.taxes.calculators;

import java.math.BigDecimal;
import java.math.BigInteger;

import com.interview.taxes.ItemI;
import com.interview.taxes.PercentageItemTaxTypeEnum;

public class ExemptTaxCalculator extends TaxCalculator {

	public ExemptTaxCalculator(ItemI item) {
		super(item);
	}

	@Override
	protected void calculates() {
		ItemI item= getItem();
		if(item.getApplicableTax().contains(PercentageItemTaxTypeEnum.EXEMPT)){
//			// valore arrotondato delle tasse da aggiungere (totprice *%)
			item.setTaxImport(new BigDecimal(BigInteger.ZERO,2));
			//somma delle tasse sull'item
			item.setTotalTaxes((item.getTaxImport()).add(item.getTotalTaxes()));
			//importo tassato tassetotali + prezzo 
			item.setSingleImport(item.getTotPrice().add(item.getTotalTaxes()));
		} if (nextCalculator!=null){
			nextCalculator.calculates();
		}
	}

}
