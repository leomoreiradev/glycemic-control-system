package com.glycemic.domain.service;

import com.glycemic.api.request.MeasurementRequest;
import com.glycemic.domain.model.Measurement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface MeasurementServive {

    Page<Measurement> findAllMeasurePaged(Pageable pageable);

    Measurement findById(Long id);

    public Measurement save(MeasurementRequest measure);
}
