package com.sml.kafkareceiver.service.Impl;

import com.sml.kafkareceiver.model.IntegralParam;
import com.sml.kafkareceiver.repository.IntegralParamRepository;
import com.sml.kafkareceiver.service.IntegralParamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class IntegralParamServiceImpl implements IntegralParamService {

    private final IntegralParamRepository integralParamRepository;


    @Override
    public IntegralParam save(IntegralParam integralParam) {
        return integralParamRepository.save(integralParam);
    }
}
