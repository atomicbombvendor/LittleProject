package com.company.Lambda;

/**
 * Created by eli9 on 9/25/2017.
 */
public class Baz {
    private String type;
    private Double typeValue;
    private String typ;

    public Baz(String type, Double typeValue) {
        this.type = type;
        this.typeValue = typeValue;
    }

    public Baz(String type, String typ){
        this.type = type;
        this.typ = typ;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getTypeValue() {
        return typeValue;
    }

    public void setTypeValue(Double typeValue) {
        this.typeValue = typeValue;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }
}
