package com.sml.kafkareceiver.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Список передаваемых данных, указывается при статусе I,U
 */

@Data
@Entity
@Table(name = "record_data")
public class RecData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id")
    private Long id;

    @Column(nullable = false)
    private String primeID;//идентификатор (ID) еденицы EM в первоисточнике CCM

    @Column(nullable = false)
    private Integer kceh;// номер цеха

    @Column(nullable = false)
    private String kcehName;// название цеха

    @Column(nullable = false)
    private Integer unitCode;// код агрегата

    @Column(nullable = false)
    private String unitName;// название агрегата

    @Column(nullable = false)
    private Integer werksCode;// код завода

    @Column(nullable = false)
    private String werksName;// название завода

    @OneToMany(cascade = CascadeType.ALL)// todo определиться с каскадными типами
    @JoinColumn(name = "record_data_id",
                nullable = false)
    @Size(min=1,
          message = "Список спецификации не содержит данных.")
    private List<RecSpec> specifications=new ArrayList<>();

    public void addSpec(RecSpec recordSpecification) {
        if (recordSpecification != null) {
            this.specifications.add(recordSpecification);
        }
    }
}
