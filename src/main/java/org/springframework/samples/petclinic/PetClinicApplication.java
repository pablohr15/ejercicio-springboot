/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.samples.petclinic;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.samples.petclinic.vet.SpecialityRepository;
import org.springframework.samples.petclinic.vet.Specialty;
import org.springframework.samples.petclinic.vet.Vet;
import org.springframework.samples.petclinic.vet.VetRepository;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * PetClinic Spring Boot Application.
 *
 * @author Dave Syer
 *
 */
@SpringBootApplication
public class PetClinicApplication {

	private final static Logger log = Logger.getLogger("log");
	public static void main(String[] args) {

		SpringApplication.run(PetClinicApplication.class, args);
	}


	@Bean
	public CommandLineRunner demoVetRepository(VetRepository vetRepository, SpecialityRepository specialityRepository) {

		return (args) -> {

			log.info("*****************************************************");

			log.info("BOOTCAMP - Spring y Spring Data - vetRepository");

			log.info("*****************************************************");



			log.info("----Creamos un objeto Vet----\n");

			Vet vet = new Vet();

			vet.setFirstName("Sergio");

			vet.setLastName("Raposo Vargas");



			log.info("----Persistimos en BBDD----\n");

			vet = vetRepository.save(vet);



			log.info("----Comprobamos que se ha creado correctamente----");

			Optional<Vet> vetAux = vetRepository.findById(vet.getId());

			log.info(vetAux.get().toString()+"\n");



			log.info("----Editamos el objeto y añadimos una Speciality----");

			Optional<Specialty> s = specialityRepository.findById(1); //El ID 1 corresponde a radiology

			vet.addSpecialty(s.get());

			vet = vetRepository.save(vet);

			log.info(vet.toString() + " Specialities: " + vet.getSpecialties()+"\n");



			log.info("----Listamos todos los veterinarios----");

			for(Vet v: vetRepository.findAll()){

				log.info("Vet: "+v.getFirstName() + " " + v.getLastName() +" Speciality: "+v.getSpecialties());

			}
			log.info("\n");

			Integer specialty_id = 1;
			List<Vet> vetsBySpecialty = vetRepository.findAllBySpecialty(specialty_id);

			log.info("----Lista de veterinarios en la especialidad " + (specialityRepository.findById(specialty_id)).get().getName() + "----");

			for (Vet v :vetsBySpecialty){
				log.info(v.toString());
			}
			log.info("\n");

			log.info("----Borramos el veterinario creado de la base de datos----\n");
			vetRepository.delete(vet);

			log.info("----Veterinario eliminado----\n");


		};

	}

}
