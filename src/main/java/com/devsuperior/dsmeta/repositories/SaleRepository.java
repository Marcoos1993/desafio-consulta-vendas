package com.devsuperior.dsmeta.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.projections.SellerProjections;

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
	
	
	@Query(nativeQuery = true, value="SELECT name, SUM(amount) AS somas "
			+ "FROM tb_seller seller "
			+ "INNER JOIN tb_sales sales ON seller.id = sales.seller_id "
			+ "WHERE sales.date BETWEEN :minDate AND :maxDate "
			+ "GROUP BY seller.name;" )
	List<SellerProjections> searchBySummary(@Param("minDate") LocalDate minDate, 
					           @Param("maxDate") LocalDate maxDate);
						

   }
	
