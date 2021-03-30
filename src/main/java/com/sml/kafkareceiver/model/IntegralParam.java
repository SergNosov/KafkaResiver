package com.sml.kafkareceiver.model;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * интегральные параметры еденицы продукции
 */
@Data
@Entity
@Table(name = "integral_parameters")
public class IntegralParam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id")
    private Long id;

    @Column(nullable = false)
    private String ts;//дата и время передачи

    @Column(nullable = false)
    private op op;// операция

    @Column(nullable = false)
    private Integer recordPk;//Id EM СУП

    @OneToOne(optional = true, cascade = CascadeType.ALL)
    @JoinColumn(unique = true) // todo определиться с уникальностью
    private RecData data;// может быть null
}
