package com.glycemic.domain.service.impl;

import com.glycemic.api.request.MeasurementRequest;
import com.glycemic.domain.model.Measurement;
import com.glycemic.domain.repository.MeasurementRepository;
import com.glycemic.domain.service.MeasurementServive;
import com.glycemic.enums.GlucoseTemperature;
import com.glycemic.exception.ResourceNotFoundException;
import com.glycemic.mapper.MeasurementMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class MeasurementServiceImpl implements MeasurementServive {

    private final MeasurementRepository measurementRepository;

    public MeasurementServiceImpl(MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Measurement> findAllMeasurePaged(Pageable pageable) {
        return measurementRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public Measurement findById(Long id) {
       Optional<Measurement> measureFind = measurementRepository.findById(id);
        return measureFind.orElseThrow(() -> new ResourceNotFoundException("Entity not Found"));
    }

    @Transactional
    @Override
    public Measurement save(MeasurementRequest measurementRequest) {
        double glycemicValue = measurementRequest.getGlycemicValue().doubleValue();
        GlucoseTemperature glucoseTemperature;

        if(measurementRequest.getFast()) {
            glucoseTemperature =  measurementInFast(measurementRequest);
        }
        else if(glycemicValue > 140.0) {
            glucoseTemperature =  GlucoseTemperature.HIGH;
        } else if (glycemicValue < 70.0 ) {
            glucoseTemperature =  GlucoseTemperature.LOW;
        } else {
            glucoseTemperature = GlucoseTemperature.NORMAL;
        }



        return measurementRepository.save(MeasurementMapper.mapToEntity(measurementRequest, glucoseTemperature));
    }

    public GlucoseTemperature measurementInFast(MeasurementRequest measurementRequest) {
        double glycemicValue = measurementRequest.getGlycemicValue().doubleValue();
        GlucoseTemperature glucoseTemperature;
        if(glycemicValue > 100.0 && glycemicValue < 125.0) {
            glucoseTemperature =  GlucoseTemperature.HIGH;
        } else if (glycemicValue < 70.0 ) {
            glucoseTemperature =  GlucoseTemperature.LOW;
        } else {
            glucoseTemperature = GlucoseTemperature.NORMAL;
        }

        return glucoseTemperature;
    }
}
