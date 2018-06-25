package com.example.webfluxdemo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.webfluxdemo.ResponseMessage.Response;
import com.example.webfluxdemo.model.Person;
import com.example.webfluxdemo.repository.PersonRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin
@RestController
public class PersonController {
	
	@Autowired
	private PersonRepository personRepository;
	
	@GetMapping("/person-info")
	public Flux<Person> getPersonDetails()
	{
		return personRepository.findAll();
	}

	@PostMapping(value="/person-info")
    public  Mono<ResponseEntity<Response>> addPersonDetails(@Valid @RequestBody Person person) {

	 	return personRepository.save(person).map(addPersonDetails -> new ResponseEntity<>(new Response("Details Saved Successfully",200),HttpStatus.OK));
      
    }
	@PutMapping("/person-info/{id}")
    public Mono<ResponseEntity<Person>> updatePerson(@PathVariable(value = "id") String personId,
                                                   @Valid @RequestBody Person person) {
        return personRepository.findById(personId)
                .flatMap(existingPerson -> {
                	existingPerson.setStreet(person.getStreet());
                	existingPerson.setCity(person.getCity());
                	existingPerson.setState(person.getState());
                	existingPerson.setCountry(person.getCountry());
                	existingPerson.setZip(person.getZip());
                	existingPerson.setPhoneNo(person.getPhoneNo());
                	existingPerson.setCardType(person.getCardType());
                	existingPerson.setCardMemberName(person.getCardMemberName());
                	existingPerson.setCardNumber(person.getCardNumber());
                	existingPerson.setExpDate(person.getExpDate());
                	existingPerson.setCvv(person.getCvv());
                	existingPerson.setAddr1(person.getAddr1());
                	existingPerson.setAddr2(person.getAddr2());
                    return personRepository.save(existingPerson);
                })
                .map(updatePerson -> new ResponseEntity<>(updatePerson, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
	
}
