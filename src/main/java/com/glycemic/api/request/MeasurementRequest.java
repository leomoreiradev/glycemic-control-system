package com.glycemic.api.request;

import com.glycemic.domain.model.User;
import com.glycemic.enums.GlucoseTemperature;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Builder
public class MeasurementRequest {

    private Long id;
    private BigDecimal glycemicValue;
    private Boolean fast;
    private User user;
}
