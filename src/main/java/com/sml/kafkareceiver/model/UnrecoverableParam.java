package com.sml.kafkareceiver.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * передача не хранимых параметров трендов
 */

@Data
@Entity
@Table(name = "unrecoverable_parameters")
@ToString(callSuper = true)
public class UnrecoverableParam extends BaseParam{
}
