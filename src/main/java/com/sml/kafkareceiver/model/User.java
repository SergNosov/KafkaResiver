package com.sml.kafkareceiver.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {
    private String id;
    private String name;
    private Integer age;
}