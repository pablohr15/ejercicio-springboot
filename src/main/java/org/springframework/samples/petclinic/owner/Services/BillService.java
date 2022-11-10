package org.springframework.samples.petclinic.owner.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.owner.DAO.BillRepository;
import org.springframework.samples.petclinic.owner.DTO.Bill;
import org.springframework.samples.petclinic.owner.DTO.Visit;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Objects;

@Service
public class BillService {

	@Autowired
	private BillRepository br;
	@Autowired
	private VisitService vs;

	public List<Bill> findAll(){

		return br.findAll();

	}

	public Bill findById(Integer id){

		return br.findBillById(id);

	}



	/*public Bill findByIdAndBillId(Integer billId, Integer visitId){

		return br.findByIdAndBillId(billId, visitId);

	}*/

	public void setIdOnVisit(Bill bill, Integer visitId){

		Visit visit = vs.findById(visitId);
		boolean enc = false;

		if(visit != null){
			for(int i = 0; i < bill.getVisits().size() && !enc; i++){
				if(Objects.equals(bill.getVisits().get(i).getId(), visit.getId())){
					enc = true;
					bill.getVisits().remove(i);
					bill.getVisits().add(visit);
				}
			}
			if(!enc){
				bill.getVisits().add(visit);
			}
			br.save(bill);
		}

	}

	public List<Visit> findVisitsByBill(Integer billId){

		return br.findVisitsOfBill(billId);

	}

}
