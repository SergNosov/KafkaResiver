package com.sml.kafkareceiver.dao;

import com.sml.kafkareceiver.model.IntegralParam;
import com.sml.kafkareceiver.model.Operation;
import com.sml.kafkareceiver.model.RecData;
import com.sml.kafkareceiver.repository.IntegralParamRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;

@Slf4j
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class IntegralParamRepositoryTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private IntegralParamRepository integralParamRepository;

    @Test
    void testSaveIp(){
        RecData recData = new RecData();
        recData.setKceh(123);
        recData.setKcehName("наименование цеха");
        recData.setPrimeID(Integer.valueOf(777).toString());
        recData.setUnitCode(555);
        recData.setUnitName("unitName");
        recData.setWerksCode(Integer.valueOf(345));
        recData.setWerksName("werksName");

        IntegralParam integralParam = new IntegralParam();
        integralParam.setTs(LocalDateTime.now().toString());
        integralParam.setRecordPk(321);
        integralParam.setOperation(Operation.I);

        integralParam.setData(recData);

        integralParam = integralParamRepository.save(integralParam);

        IntegralParam actualIp = integralParamRepository.findById(integralParam.getId()).get();

        System.out.println("---integralParam: "+System.identityHashCode(integralParam));
        System.out.println("---actualIp: "+System.identityHashCode(actualIp));
    }
}
