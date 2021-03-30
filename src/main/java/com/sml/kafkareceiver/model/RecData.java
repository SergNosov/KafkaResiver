package com.sml.kafkareceiver.model;

import java.util.List;

public class RecData {

    private Long id;

    private String primeID;//идентификатор (ID) едуницы EM в первоисточнике CCM
    private Integer kceh;// номер цеха
    private String kcehName;// название цеха
    private Integer unitCode;// код агрегата
    private String unitName;// название агрегата
    private Integer werksCode;// код завода
    private String werksName;// название завода
    private List<RecSpec> specifications;
}
