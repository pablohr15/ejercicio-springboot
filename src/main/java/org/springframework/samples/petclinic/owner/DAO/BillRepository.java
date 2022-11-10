package org.springframework.samples.petclinic.owner.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.owner.DTO.Bill;
import org.springframework.samples.petclinic.owner.DTO.Visit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BillRepository extends JpaRepository<Bill, Integer> {


	/*@Query("SELECT bill FROM Bill bill WHERE bill.id = :billId AND bill.visit.id = :visitId")
	@Transactional(readOnly = true)
	Bill findByIdAndBillId(@Param("billId") Integer billId, @Param("visitId") Integer visitId);*/

	@Query("SELECT bill FROM Bill bill WHERE bill.id = :billId")
	@Transactional(readOnly = true)
	Bill findBillById(@Param("billId") Integer billId);

	@Query("SELECT bill.visits FROM Bill bill WHERE bill.id = :billId")
	@Transactional(readOnly = true)
	List<Visit> findVisitsOfBill(@Param("billId") Integer billId);



}
