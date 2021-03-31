package com.sml.kafkareceiver.repository;

import com.sml.kafkareceiver.model.IntegralParam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IntegralParamRepository extends JpaRepository<IntegralParam,Long> {
}
