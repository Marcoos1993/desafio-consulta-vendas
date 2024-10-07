package com.devsuperior.dsmeta.repositories;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.dsmeta.entities.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {
	

	@Query("SELECT new com.devsuperior.dsmeta.dto.SaleMinDTO(obj.id, obj.date, SUM(obj.amount), obj.seller.name) "
			     + "FROM Sale obj "
			     + "WHERE obj.date BETWEEN :minDate AND :maxDate "  
			     + "AND UPPER(obj.seller.name) LIKE UPPER(CONCAT('%', :name, '%')) "
			     + "GROUP BY obj.id, obj.date, obj.seller.name")
			Page<Sale> searchBySales(LocalDate minDate, LocalDate maxDate, String name, Pageable pageable);

}


