package org.springframework.samples.petclinic.owner.Controllers;


import org.hibernate.annotations.GeneratorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.owner.DTO.Bill;
import org.springframework.samples.petclinic.owner.DTO.Visit;
import org.springframework.samples.petclinic.owner.Services.BillService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class BillController {

	@Autowired
	private BillService bills;

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	/*@GetMapping("/bills")
	public Collection<Bill> getAllBills(){

		return bills.findAll();

	}*/

	@GetMapping("/bills")
	public Collection<Bill> getBillsByPaymentDate(@RequestParam String filter) {

		Collection<Bill> cbills = bills.findAll();

		if (filter.equals("no_pagadas")) {
			cbills.removeIf(b -> b.getVisits().size() > 0);
		}
		if(filter.equals("pagadas")){
			cbills.removeIf(b -> b.getVisits().size() == 0);
		}

		return cbills;

	}

	@GetMapping("/bills/{billId}/visit/{visitId}")
	public Visit getVisitFromBill(@PathVariable Integer billId, @PathVariable Integer visitId) {

		List<Visit> visits = null;
		visits = bills.findVisitsByBill(billId);

		boolean enc = false;
		Visit v = null;

		for(int i = 0; i < visits.size() && !enc; i++){
			if(Objects.equals(visits.get(i).getId(), visitId)){
				v = visits.get(i);
				enc = true;
			}
		}

		return v;

	}

	@PostMapping("/bills/{billId}/visit/{visitId}")
	public void setVisitId(@PathVariable Integer billId, @PathVariable Integer visitId){

		Bill bill = new Bill();
		bill = bills.findById(billId);
		bills.setIdOnVisit(bill, visitId);

	}

	@GetMapping("/visits")
	public Collection<Visit> getVisitsByPayment(@RequestParam String filter) {

		List<Visit> cvisits = new ArrayList<>();

		Collection<Bill> cbills = bills.findAll();

		if (filter.equals("pagadas")) {
			cbills.removeIf(b -> b.getPaymentDay() == null);
		}
		if(filter.equals("no_pagadas")){
			cbills.removeIf(b -> b.getPaymentDay() != null);
		}

		for(Bill b: cbills){
			if(b.getVisits().size() > 0){
				cvisits.addAll(b.getVisits());
			}

		}

		return cvisits;

	}

}
