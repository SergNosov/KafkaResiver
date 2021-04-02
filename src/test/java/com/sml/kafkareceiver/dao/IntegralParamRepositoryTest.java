package com.sml.kafkareceiver.dao;

import com.sml.kafkareceiver.model.BaseParam;
import com.sml.kafkareceiver.model.IntegralParam;
import com.sml.kafkareceiver.model.Operation;
import com.sml.kafkareceiver.model.RecData;
import com.sml.kafkareceiver.model.RecSpec;
import com.sml.kafkareceiver.repository.IntegralParamRepository;
import lombok.extern.slf4j.Slf4j;
import nlmk.l3.sup.enum_op;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import java.time.LocalDateTime;

@Slf4j
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class IntegralParamRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private IntegralParamRepository integralParamRepository;

    @Test
    void testSaveIp(){
        RecSpec recSpec = new RecSpec();
        recSpec.setSpecCode(19);
        recSpec.setSpecName("Высота местных утолщений по ширине полосы");
        recSpec.setSpecTypeCode(2);
        recSpec.setSpecTypeName("число");

        RecData recData = new RecData();
        recData.setKceh(123);
        recData.setKcehName("наименование цеха");
        recData.setPrimeID(Integer.valueOf(777).toString());
        recData.setUnitCode(555);
        recData.setUnitName("unitName");
        recData.setWerksCode(Integer.valueOf(345));
        recData.setWerksName("werksName");
        recData.addSpec(recSpec);

        IntegralParam integralParam = new IntegralParam();
        integralParam.setTs(LocalDateTime.now().toString());
        integralParam.setRecordPk(321);
        integralParam.setOperation(enum_op.I.toString());

        integralParam.setData(recData);


        integralParam = entityManager.persist(integralParam);

        IntegralParam actualIp = integralParamRepository.findById(integralParam.getId()).get();

        System.out.println("---integralParam: "+System.identityHashCode(integralParam));
        System.out.println("---actualIp: "+System.identityHashCode(actualIp));
        System.out.println("---actualIp: "+actualIp.toString());
    }

    @Test
    void testChar(){
        CharSequence charSequence = null;
//        StringBuilder sb = new StringBuilder(charSequence);
//        System.out.println("---: "+sb.toString());
        String str = String.valueOf(charSequence);

//        if (str.length()null){
//            System.out.println("str is null");
//        }

        System.out.println("---: "+str+"; length: "+str.length());
    }
}
