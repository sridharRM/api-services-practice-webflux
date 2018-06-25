package com.example.webfluxdemo.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.example.webfluxdemo.model.Items;

@Repository
public interface  ItemRepository extends ReactiveMongoRepository<Items, String> {

}