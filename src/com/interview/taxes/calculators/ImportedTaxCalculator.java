package com.interview.taxes.calculators;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.interview.taxes.ItemI;
import com.interview.taxes.PercentageItemTaxTypeEnum;

public class ImportedTaxCalculator extends TaxCalculator {

	public ImportedTaxCalculator(ItemI item) {
		super(item);
	}

	@Override
	protected void calculates() {
		ItemI item= getItem();
		if(item.getApplicableTax().contains(PercentageItemTaxTypeEnum.IMPORTED)){
			// valore delle tasse da aggiungere (totprice *%)
			BigDecimal tax=( item.getTotPrice().multiply(PercentageItemTaxTypeEnum.IMPORTED.getSalesTax()));
			// valore arrotondato delle tasse da aggiungere (totprice *%)
			item.setTaxImport(new BigDecimal(Math.ceil(tax.doubleValue() * 20) / 20).setScale(2, RoundingMode.HALF_UP));
			//somma delle tasse sull'item
			item.setTotalTaxes(item.getTotalTaxes().add(item.getTaxImport()));
			//importo tassato tassetotali + prezzo 
			item.setSingleImport(item.getTotPrice().add(item.getTotalTaxes()));
		} if (nextCalculator!=null){
				nextCalculator.calculates();
			}
			
	}
	
	

}
