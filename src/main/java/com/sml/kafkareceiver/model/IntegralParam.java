package com.sml.kafkareceiver.model;

/**
 * интегральные параметры еденицы продукции
 */
public class IntegralParam {

    private Long id;

    private String ts;//дата и время передачи
    private op op;// операция
    private RecPk pk;
    private RecData data;// может быть null
}
