package com.desafioIdwall.usecase.enums;

import com.desafioIdwall.exceptions.CategoriaNotFoundException;

public enum CategoriaEnums {

    colarinhoBranco("colarinhoBranco"),
    empreedimentoCriminoso("empreendimentoCrimnoso"),
    lavagemDinheiro("lavagemDinheiro"),
    fraudeBancaria("fraudeBancaria");


    public final String type;

    CategoriaEnums(String type) {
        this.type = type;
    }

    public static String changeTypeCategoria(CategoriaEnums type){
        switch(type){
            case empreedimentoCriminoso:
                return "Criminal Enterprise Investigations";
            case colarinhoBranco:
                return "White-Collar Crime";
            case lavagemDinheiro:
                return "Money Laundering";
            case fraudeBancaria:
                return "Bank Fraud";
            default:
                throw new CategoriaNotFoundException("Tipo de categoria não localizada!");
        }
    }
}
