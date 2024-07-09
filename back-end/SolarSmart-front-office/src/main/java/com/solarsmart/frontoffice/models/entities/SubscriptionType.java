package com.solarsmart.frontoffice.models.entities;



public enum SubscriptionType {
    MENSUEL(1),
    SEMESTRIEL(2),
    ANNUEL(3);

    private Integer value;

    SubscriptionType(int value){
        this.value = value;
    }
}
