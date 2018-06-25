package com.example.webfluxdemo.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.example.webfluxdemo.model.Person;


@Repository
public interface PersonRepository extends ReactiveMongoRepository<Person, String> {

}
