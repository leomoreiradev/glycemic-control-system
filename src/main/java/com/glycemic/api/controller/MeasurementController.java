package com.glycemic.api.controller;

import com.glycemic.api.request.MeasurementRequest;
import com.glycemic.domain.model.Measurement;
import com.glycemic.domain.service.MeasurementServive;
import com.glycemic.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("v1/api/measurements")
public class MeasurementController {

    private final MeasurementServive measurementServive;

    public MeasurementController(MeasurementServive measurementServive) {
        this.measurementServive = measurementServive;
    }

    @GetMapping
    public ResponseEntity<Page<Measurement>> findAll(Pageable pageable){
        Page<Measurement> measurements = measurementServive.findAllMeasurePaged(pageable);
        return ResponseEntity.ok().body(measurements);

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Measurement> findById(@PathVariable Long id) {
        try{
            Measurement measurement = measurementServive.findById(id);
            return ResponseEntity.ok(measurement);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Measurement> save(@RequestBody MeasurementRequest measurementRequest) {
        Measurement measurement = measurementServive.save(measurementRequest);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(measurementRequest.getId())
                .toUri();
        return ResponseEntity.created(uri).body(measurement);
    }
}
