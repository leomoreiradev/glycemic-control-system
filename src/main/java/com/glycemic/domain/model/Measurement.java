package com.glycemic.domain.model;

import com.glycemic.enums.GlucoseTemperature;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Measurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    private BigDecimal glycemicValue;
    private LocalDateTime dateTime;
    private GlucoseTemperature glucoseTemperature;
    private Boolean fast;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
