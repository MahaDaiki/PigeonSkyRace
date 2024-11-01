package com.example.pigeon.repository;

import com.example.pigeon.entity.Pigeon;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PigeonRepository extends MongoRepository<Pigeon, String> {
    List<Pigeon> findByEleveurId(String eleveurId);
}