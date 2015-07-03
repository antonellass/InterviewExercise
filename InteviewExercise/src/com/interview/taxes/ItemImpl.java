package com.interview.taxes;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Set;

public class ItemImpl implements ItemI {

	private String name;
	private LocalCurrency price;
	private Integer quantity;
	private Set<PercentageItemTaxTypeEnum> applicableTaxes;
	private BigDecimal singleImport = new BigDecimal(BigInteger.ZERO, 2);
	private BigDecimal taxImport = new BigDecimal(BigInteger.ZERO, 2);
	private BigDecimal totalTaxes = new BigDecimal(BigInteger.ZERO, 2);
	private BigDecimal totPrice = new BigDecimal(BigInteger.ZERO, 2);

	public ItemImpl(String name, LocalCurrency singlePrice, Integer quatity, Set<PercentageItemTaxTypeEnum> applicableTaxes) {
		this.name = name;
		this.price = singlePrice;
		this.quantity = quatity;
		this.applicableTaxes = applicableTaxes;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Integer getQuantity() {
		return quantity;
	}

	@Override
	public LocalCurrency getPrice() {
		return price;
	}

	@Override
	public Set<PercentageItemTaxTypeEnum> getApplicableTax() {
		return applicableTaxes;
	}

	@Override
	public BigDecimal getSingleImport() {
		return singleImport;
	}

	@Override
	public BigDecimal getTaxImport() {
		return taxImport;
	}

	@Override
	public void setSingleImport(BigDecimal singleImport) {
		this.singleImport = singleImport;
	}

	@Override
	public void setTaxImport(BigDecimal taxImport) {
		this.taxImport = taxImport;

	}

	@Override
	public void setTotalTaxes(BigDecimal totalTaxes) {
		this.totalTaxes = totalTaxes;
	}

	@Override
	public BigDecimal getTotalTaxes() {
		return totalTaxes;
	}

	@Override
	public BigDecimal getTotPrice() {
		return totPrice;
	}

	@Override
	public void setTotPrice(BigDecimal totPrice) {
		this.totPrice = totPrice;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemImpl other = (ItemImpl) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ItemImpl [name=" + name + ", price=" + price + ", quantity=" + quantity + "]";
	}

}
