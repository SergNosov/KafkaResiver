package com.sml.kafkareceiver.repository;

import com.sml.kafkareceiver.model.UnrecoverableParam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnrecoverableParamRepository extends JpaRepository<UnrecoverableParam,Long> {
}
