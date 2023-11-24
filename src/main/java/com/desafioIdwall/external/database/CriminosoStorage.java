package com.desafioIdwall.external.database;

import com.desafioIdwall.external.database.domains.Criminoso;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CriminosoStorage {

    List<Criminoso> findByNome(String nome);
    List<Criminoso> executeFindByAno(String ano);

}
