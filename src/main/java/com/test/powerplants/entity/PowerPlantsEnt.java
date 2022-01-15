package com.test.powerplants.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "POWER_PLANTS")
public class PowerPlantsEnt {

    @Id
    @Column(name = "SEQGEN19")
    private Long seqGen;
    @Column(name = "DATE_YEAR")
    private Long dateYear;
    @Column(name = "PSTATABB")
    private String location;
    @Column(name = "PNAME")
    private String name;
    @Column(name = "GENID")
    private String generatorId;
    @Column(name = "GENSTAT")
    private String generatorStatus;
    @Column(name = "GENNTAN")
    private Double netGeneration;
}
