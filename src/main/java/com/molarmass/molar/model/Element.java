package com.molarmass.molar.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Element {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long atomicNo;
    private String symbol;
    private Double mass;
    private String name;
    private Long profile;

    public Element(Long id, Long atomicNo, String symbol, Double mass, String name, Long profile){
        this.id = id;
        this.atomicNo = atomicNo;
        this.symbol = symbol;
        this.mass = mass;
        this.name = name;
        this.profile = profile;
    }

    public Element(Long atomicNo, String symbol, Double mass, String name, Long profile){
        this.atomicNo = atomicNo;
        this.symbol = symbol;
        this.mass = mass;
        this.name = name;
        this.profile = profile;
    }

    public Element(){ }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAtomicNo() {
        return atomicNo;
    }

    public void setAtomicNo(Long atomicNo) {
        this.atomicNo = atomicNo;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Double getMass() {
        return mass;
    }

    public void setMass(Double mass) {
        this.mass = mass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getProfile() {
        return profile;
    }

    public void setProfile(Long profile) {
        this.profile = profile;
    }



}
