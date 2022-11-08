package org.springframework.samples.petclinic.vet.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.samples.petclinic.vet.DTO.Specialty;

public interface SpecialityRepository extends JpaRepository<Specialty, Integer> { }
