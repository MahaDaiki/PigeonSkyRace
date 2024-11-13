package com.example.pigeon.repository;

import com.example.pigeon.entity.Pigeon;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface PigeonRepository extends MongoRepository<Pigeon, String> {
    List<Pigeon> findByEleveurId(String eleveurId);
    List<Pigeon> findAllPigeonsById(List<ObjectId> ids);
    Optional<Pigeon> findByNumeroBague(String numeroBague);
}
