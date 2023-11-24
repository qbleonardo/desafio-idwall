package com.desafioIdwall.usecase;

import com.desafioIdwall.external.database.CriminosoStorage;
import com.desafioIdwall.external.database.domains.Criminoso;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StorageUseCase {

    CriminosoStorage criminosoStorage;

    public StorageUseCase(CriminosoStorage criminosoStorage) {
        this.criminosoStorage = criminosoStorage;
    }

    public List<Criminoso> executeFindByName(String nome){
        return criminosoStorage.findByNome(nome);
    }

    public List<Criminoso> executeFindByAno(String ano){
        return criminosoStorage.executeFindByAno(ano);
    }
}
