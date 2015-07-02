package com.interview.taxes;

import java.util.Set;

public class ItemImpl implements ItemI{

	private  String name;
	private  LocalCurrency price;
	private  Integer quantity;
	private  ItemTypeEnum type;
	private  Set<PercentageItemTaxTypeEnum> applicableTaxes;
	
	public ItemImpl(String name, LocalCurrency singlePrice, Integer quatity, Set<PercentageItemTaxTypeEnum> applicableTaxes) {
		this.name = name;
		this.price = singlePrice;
		this.quantity = quatity;
		this.applicableTaxes = applicableTaxes;
	}
	public ItemImpl(String name, LocalCurrency singlePrice, Integer quatity, ItemTypeEnum type,Set<PercentageItemTaxTypeEnum> applicableTaxes) {
		this.name = name;
		this.price = singlePrice;
		this.quantity = quatity;
		this.type=type;
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
	public String getType() {
		return type.toString();
	}

	@Override
	public Set<PercentageItemTaxTypeEnum> getApplicableTax() {
		return applicableTaxes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		if (type != other.type)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ItemImpl [name=" + name + ", price=" + price + ", quantity=" + quantity + "]";
	}


	
}
