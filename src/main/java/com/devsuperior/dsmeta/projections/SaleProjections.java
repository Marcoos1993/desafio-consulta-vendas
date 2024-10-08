package com.devsuperior.dsmeta.projections;

import java.time.LocalDate;

public interface SaleProjections {
	
	Long getId();
	LocalDate getDate();
	Double getAmount();
	String getSellerName();

}
