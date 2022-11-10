package org.springframework.samples.petclinic.owner.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.owner.DAO.VisitRepository;
import org.springframework.stereotype.Service;
import org.springframework.samples.petclinic.owner.DTO.Visit;

import java.util.Collection;

@Service
public class VisitService {

	@Autowired
	private VisitRepository visits;



	public Visit findById(Integer visitId){

		Visit v = visits.findById(visitId).get();
		System.out.println(v);
		return v;

	}

	public Collection<Visit> findAll(){
		return visits.findAll();
	}

}
