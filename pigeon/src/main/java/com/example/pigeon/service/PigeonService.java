package com.example.pigeon.service;

import com.example.pigeon.entity.Pigeon;
import org.bson.types.ObjectId;

import java.util.List;

public interface PigeonService {
     Pigeon addPigeon(Pigeon pigeon);
     List<Pigeon> getAllPigeons();
     List<Pigeon> getPigeonsByUserId(String eleveurId);
     List<Pigeon> getPigeonsByIds(List<String> pigeonIds);
}
