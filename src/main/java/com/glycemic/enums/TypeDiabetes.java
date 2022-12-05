package com.glycemic.enums;

public enum TypeDiabetes {
    TYPE_ONE("Diabetes type one"),
    TYPE_TWO("Diabetes type two"),
    TYPE_GESTATIONAL("Diabetes type gestational"),
    TYPE_LADA("Diabetes type LADA");

    private final String typeDiabetes;
    TypeDiabetes(String typeDiabetes) {
        this.typeDiabetes = typeDiabetes;
    }

    public String getType() {
        return typeDiabetes;
    }
}
