package com.desafioIdwall.external.database;

import com.desafioIdwall.domains.CriminosoInterpol;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface InterpolStorage extends MongoRepository<CriminosoInterpol, UUID> {
}
