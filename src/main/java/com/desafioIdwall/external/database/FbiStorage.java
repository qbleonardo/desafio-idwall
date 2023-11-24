package com.desafioIdwall.external.database;

import com.desafioIdwall.domains.CriminosoFbi;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FbiStorage extends MongoRepository<CriminosoFbi, UUID> {
}
