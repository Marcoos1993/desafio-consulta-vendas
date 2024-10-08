package com.devsuperior.dsmeta.repositories;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.devsuperior.dsmeta.entities.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {
	
	@Query("SELECT obj "
		     + "FROM Sale obj "
		     + "WHERE obj.date BETWEEN :minDate AND :maxDate "  
		     + "AND UPPER(obj.seller.name) LIKE UPPER(CONCAT('%', :name, '%')) "
		     + "GROUP BY obj.id, obj.date, obj.seller.name")
		Page<Sale> searchBySales(@Param("minDate") LocalDate minDate, 
		                         @Param("maxDate") LocalDate maxDate, 
		                         @Param("name") String name, 
		                         Pageable pageable);
	
	
	@Query("SELECT obj "
		     + "FROM Sale obj "
		     + "WHERE obj.date BETWEEN :minDate AND :maxDate "
		     + "AND obj.amount IN (SELECT SUM(s.amount) FROM Sale s WHERE s.date BETWEEN :minDate AND :maxDate) "
		     + "GROUP BY obj.id, obj.date")
	Page<Sale> searchBySummary(@Param("minDate") LocalDate minDate, 
					           @Param("maxDate") LocalDate maxDate, 
					           Pageable pageable);
						

   }
	
