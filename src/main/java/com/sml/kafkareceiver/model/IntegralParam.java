package com.sml.kafkareceiver.model;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
public class IntegralParam extends BaseParam{

}
