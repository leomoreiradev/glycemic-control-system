package com.glycemic.mapper;

import com.glycemic.api.request.MeasurementRequest;
import com.glycemic.domain.model.Measurement;
import com.glycemic.enums.GlucoseTemperature;

import java.time.LocalDateTime;

public class MeasurementMapper {

    public static Measurement mapToEntity(MeasurementRequest measurementRequest, GlucoseTemperature glucoseTemperature) {
        return Measurement.builder()
                .dateTime(LocalDateTime.now())
                .glycemicValue(measurementRequest.getGlycemicValue())
                .glucoseTemperature(glucoseTemperature)
                .fast(measurementRequest.getFast())
                .user(measurementRequest.getUser())
                .build();
    }
}
