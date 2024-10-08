package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.projections.SellerProjections;

public class SaleSummaryDTO {
	
	private String sellerName;
	private Double total;
	
	
	public SaleSummaryDTO(String sellerName, Double total) {
		this.sellerName = sellerName;
		this.total = total;
	}
	
	public SaleSummaryDTO(SellerProjections projection) {
		this.sellerName = projection.getName();
		this.total = projection.getSomas();
	}

	public String getSellerName() {
		return sellerName;
	}

	public Double getTotal() {
		return total;
	}
	
	

}
