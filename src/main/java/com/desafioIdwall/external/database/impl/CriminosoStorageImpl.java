package com.desafioIdwall.external.database.impl;

import com.desafioIdwall.exceptions.AnoNascimentNotFoundException;
import com.desafioIdwall.exceptions.NameNotFoundException;
import com.desafioIdwall.external.database.CriminosoStorage;
import com.desafioIdwall.external.database.domains.Criminoso;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CriminosoStorageImpl implements CriminosoStorage {

    MongoTemplate mongoTemplate;

    public CriminosoStorageImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<Criminoso> findByNome(String nome) {
        Query query = new Query();
        query.addCriteria(Criteria.where("nome").regex(nome));

        List<Criminoso> criminosos = mongoTemplate.find(query, Criminoso.class);

        if(criminosos.isEmpty()) {
            throw new NameNotFoundException();
        }

        return criminosos;
    }

    @Override
    public List<Criminoso> executeFindByAno(String ano) {
        Query query = new Query();
        query.addCriteria(Criteria.where("dataNascimento").regex(ano));

        List<Criminoso> criminosos = mongoTemplate.find(query, Criminoso.class);

        if(criminosos.isEmpty()) {
            throw new AnoNascimentNotFoundException();
        }

        return criminosos;
    }
}
