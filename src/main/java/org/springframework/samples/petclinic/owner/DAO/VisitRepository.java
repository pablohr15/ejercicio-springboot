package org.springframework.samples.petclinic.owner.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.owner.DTO.Visit;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface VisitRepository extends JpaRepository<Visit, Integer> {

	//@Query("SELECT visit FROM Visit visit INNER JOIN visit.bill b WHERE visit.id = :visitId AND b.id = :billId")
	//@Transactional(readOnly = true)
	//Visit findByIdAndBillId(@Param("visitId") Integer visitId, @Param("billId") Integer billId);


}
